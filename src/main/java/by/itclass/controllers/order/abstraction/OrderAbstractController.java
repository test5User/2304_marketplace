package by.itclass.controllers.order.abstraction;

import by.itclass.model.services.OrderService;
import by.itclass.model.services.ServiceFactory;
import by.itclass.model.services.ServiceType;
import jakarta.servlet.ServletException;

public class OrderAbstractController extends AbstractController {
    protected OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) ServiceFactory.getInstance(ServiceType.ORDER_SERVICE);
    }
}
