package by.itclass.controllers.order;

import by.itclass.controllers.order.abstraction.AbstractController;
import by.itclass.controllers.order.abstraction.OrderAbstractController;
import by.itclass.model.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.AppConst.ORDERS_HISTORY_CONTROLLER;
import static by.itclass.constants.JspConst.*;

@WebServlet(ORDERS_HISTORY_CONTROLLER)
public class OrdersHistoryController extends OrderAbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var useId = ((User)session.getAttribute(USER_ATTR)).getId();
        var orders = orderService.getOrders(useId);
        req.setAttribute(ORDERS_ATTR, orders);
        forward(req, resp, ORDERS_JSP);
    }
}
