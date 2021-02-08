package ru.javawebinar.topjava.web;

import com.sun.tools.javac.util.Log;
import jdk.internal.logger.LoggerWrapper;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        request.getRequestDispatcher("/users.jsp").forward(request, response);
        response.sendRedirect("users.jsp");
    }
}
