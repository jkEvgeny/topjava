package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> meals = MealsUtil.filteredByStreams(
                MealsUtil.meals,
                LocalTime.of(0, 0),
                LocalTime.of(23, 59, 59), 2000);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}

