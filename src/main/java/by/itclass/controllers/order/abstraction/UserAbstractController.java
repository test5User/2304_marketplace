package by.itclass.controllers.order.abstraction;

import by.itclass.model.services.ServiceFactory;
import by.itclass.model.services.ServiceType;
import by.itclass.model.services.UserService;
import jakarta.servlet.ServletException;

public class UserAbstractController extends AbstractController{
    protected UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) ServiceFactory.getInstance(ServiceType.USER_SERVICE);
    }
}
