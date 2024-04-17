package by.itclass.controllers.stock;

import by.itclass.controllers.AbstractController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.AppConst.TV_STOCK;
import static by.itclass.constants.JspConst.HOME_JSP;
import static by.itclass.constants.JspConst.TVS_ATTR;

@WebServlet(TV_STOCK)
public class TvStockController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var tvs = tvService.getAllTv();
        req.setAttribute(TVS_ATTR, tvs);
        forward(req, resp, HOME_JSP);
    }
}
