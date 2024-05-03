package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.*;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import javax.xml.stream.events.EntityReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.itclass.constants.DbConst.*;
import static by.itclass.constants.JspConst.*;

public class OrderDao {
    public static final String INSERT_ORDER = "INSERT INTO orders (id, date, userId, address) VALUES (?, ?, ?, ?)";
    public static final String INSERT_ORDER_ITEM = "INSERT INTO orderItem (orderId, itemType, itemId, itemPrice, quantity) values (?, ?, ?, ?, ?)";
    public static final String SELECT_ORDERS_BY_USER = "SELECT id, date, address FROM orders WHERE userId = ? ORDER BY id DESC";
    public static final String SELECT_ORDER = "SELECT date, address FROM orders WHERE id = ?";
    public static final String SELECT_ITEMS = "SELECT itemType, itemId, itemPrice, quantity FROM orderItem WHERE orderId = ?";

    public boolean saveOrder(HttpSession session, String address) {
        var user = (User) session.getAttribute(USER_ATTR);
        var now = LocalDateTime.now();
        var date = now.format(DateTimeFormatter.ofPattern("y-MM-dd"));
        var time = now.format(DateTimeFormatter.ofPattern("HH-mm"));
        var orderId = String.join("-", user.getName(), date, time);
        return saveOrderToDb(orderId, date, user.getId(), address, session);
    }

    private boolean saveOrderToDb(String orderId, String date, int userId,
                                  String address, HttpSession session) {
        try (var cn = ConnectionManager.getConnection();
            var psSaveOrder = cn.prepareStatement(INSERT_ORDER);
            var psSaveItem = cn.prepareStatement(INSERT_ORDER_ITEM)) {
            cn.setAutoCommit(false);
            firstAction(orderId, date, userId, address, psSaveOrder);
            var items = (List<OrderItem>)session.getAttribute(ORDER_ITEMS_ATTR);
            items.forEach(it -> secondAction(orderId, it, psSaveItem));
            cn.commit();
            session.setAttribute(ORDER_ID_ATTR, orderId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void firstAction(String orderId, String date, int userId, String address,
                             PreparedStatement psSaveOrder) throws SQLException {
        psSaveOrder.setString(1, orderId);
        psSaveOrder.setString(2, date);
        psSaveOrder.setInt(3, userId);
        psSaveOrder.setString(4, address);
        psSaveOrder.executeUpdate();
    }

    @SneakyThrows
    private void secondAction(String orderId, OrderItem item, PreparedStatement ps) {
        ps.setString(1, orderId);
        ps.setInt(2, item.getItemType());
        ps.setInt(3, item.getItemId());
        ps.setDouble(4, item.getItemPrice());
        ps.setInt(5, item.getQuantity());
        ps.executeUpdate();
    }

    public List<Order> selectOrders(int userId) {
        var orders = new ArrayList<Order>();
        try (var cn  = ConnectionManager.getConnection();
            var ps = cn.prepareStatement(SELECT_ORDERS_BY_USER)){
            ps.setInt(1, userId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var id = rs.getString(ID_COL);
                var date = rs.getString(DATE_COL);
                var address = rs.getString(ADDRESS_COL);
                orders.add(new Order(id, date, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Receipt buildReceipt(String orderId) {
        var receipt = new Receipt();
        try (var cn = ConnectionManager.getConnection();
            var psSelectOrder = cn.prepareStatement(SELECT_ORDER);
            var psSelectItems = cn.prepareStatement(SELECT_ITEMS)){
            psSelectOrder.setString(1, orderId);
            psSelectItems.setString(1, orderId);
            var rs = psSelectOrder.executeQuery();
            if (rs.next()) {
                var date = rs.getString(DATE_COL);
                var address = rs.getString(ADDRESS_COL);
                receipt.setOrder(new Order(orderId, date, address));
                var receiptItems = getItemsForReceipt(psSelectItems, cn);
                receipt.setReceiptItems(receiptItems);
                receipt.setTotal(Math.round(getTotalAmount(receiptItems)*100)/100d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receipt;
    }

    private List<ReceiptItem> getItemsForReceipt(PreparedStatement ps, Connection cn) throws SQLException {
        var items = new ArrayList<ReceiptItem>();
        var rs = ps.executeQuery();
        while (rs.next()) {
            var itemType = rs.getInt("itemType");
            var itemId = rs.getInt("itemId");
            var itemInfo = getInfo(itemType, itemId, cn);
            var itemPrice = rs.getDouble("itemPrice");
            var quantity = rs.getInt("quantity");
            var itemAmount = Math.round(itemPrice * quantity * 100)/100d;
            items.add(new ReceiptItem(itemInfo, itemPrice, quantity, itemAmount));
        }
        return items;
    }

    private String getInfo(int itemType, int itemId, Connection cn) throws SQLException {
        try (var st = cn.createStatement()){
            var tableName = itemType == 1 ? "tv" : "laptop";
            var query = String.format("SELECT vendor, model FROM %s WHERE id = %d", tableName, itemId);
            var rs = st.executeQuery(query);
            rs.next();
            return String.join("-", rs.getString("vendor"), rs.getString("model"));
        }
    }

    private double getTotalAmount(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::getItemAmount)
                .reduce(0d, Double::sum);
    }
}
