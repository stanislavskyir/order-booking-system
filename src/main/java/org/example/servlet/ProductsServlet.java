package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.UserDto;
import org.example.entity.Role;
import org.example.service.ProductService;
import org.example.utils.JspHelper;
import org.example.utils.UrlPath;

import java.io.IOException;

@WebServlet(UrlPath.PRODUCTS)
public class ProductsServlet extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var products = productService.findAll();
        req.setAttribute("products", products);
        UserDto user = (UserDto) req.getSession().getAttribute("user");

        String jspPath = (user.getRole() == Role.ADMIN)
                ? JspHelper.getPath("admin-products")
                : JspHelper.getPath("products");

        req.getRequestDispatcher(jspPath).forward(req, resp);

    }
}

