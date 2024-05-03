package by.itclass.controllers.stock;

import by.itclass.controllers.order.abstraction.AbstractController;
import by.itclass.model.services.LaptopService;
import by.itclass.model.services.ServiceFactory;
import by.itclass.model.services.ServiceType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.AppConst.LAPTOP_STOCK;
import static by.itclass.constants.JspConst.*;

@WebServlet(LAPTOP_STOCK)
public class LaptopStockController extends AbstractController {
    private LaptopService laptopService;

    @Override
    public void init() throws ServletException {
        laptopService = (LaptopService) ServiceFactory.getInstance(ServiceType.LAPTOP_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var laptops = laptopService.getLaptops(req.getParameterMap());
        req.setAttribute(LAPTOPS_ATTR, laptops);
        forward(req, resp, LAPTOP_PAGE_JSP);
    }
}
