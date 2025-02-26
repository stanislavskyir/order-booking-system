package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Product;
import org.example.exception.ValidationException;
import org.example.service.ProductService;
import org.example.utils.JspHelper;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin-products")
public class AdminProductsServlet extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("admin-products")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BigDecimal price = new BigDecimal(req.getParameter("price"));
        boolean isAvailable = req.getParameter("isAvailable") != null;

        var product = Product.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .price(price)
                .isAvailable(isAvailable)
                .build();

        try {
            productService.create(product);
            System.out.println("Product created");
            resp.sendRedirect("/admin-products");
        }catch (ValidationException e){
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
