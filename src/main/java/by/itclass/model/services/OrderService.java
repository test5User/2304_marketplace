package by.itclass.model.services;

import by.itclass.model.dao.OrderDao;
import by.itclass.model.entities.Order;
import by.itclass.model.entities.Receipt;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Objects;

public class OrderService implements Service {
    private final OrderDao dao;

    public OrderService() {
        dao = new OrderDao();
    }


    public boolean saveOrder(HttpSession session, String address) {
        return dao.saveOrder(session, address);
    }

    public List<Order> getOrders(int userId) {
        return dao.selectOrders(userId);
    }

    public Receipt buildReceipt(String orderId) {
        return dao.buildReceipt(orderId);
    }
}
