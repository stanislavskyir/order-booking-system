package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.CreateUserDto;
import org.example.entity.Role;
import org.example.exception.ValidationException;
import org.example.service.UserService;
import org.example.utils.JspHelper;
import org.example.utils.UrlPath;

import java.io.IOException;

@WebServlet(UrlPath.REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .password(req.getParameter("pwd"))
                .role(req.getParameter("role"))
                .build();

        try{
            userService.create(userDto);
            resp.sendRedirect(JspHelper.getPath("login"));
        }catch (ValidationException e){
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
