package by.itclass.controllers.order;

import by.itclass.controllers.AbstractController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.AppConst.PRINT_ORDER_CONTROLLER;
import static by.itclass.constants.JspConst.*;

@WebServlet(PRINT_ORDER_CONTROLLER)
public class PrintOrderController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var orderId = req.getParameter(ORDER_ID_ATTR);
        var receipt = orderService.buildReceipt(orderId);
        req.setAttribute(RECEIPT_ATTR, receipt);
        forward(req, resp, RECEIPT_JSP);
    }
}
