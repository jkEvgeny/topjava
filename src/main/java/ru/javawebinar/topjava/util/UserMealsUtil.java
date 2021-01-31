package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> correctMeals = new ArrayList(meals.size());
        HashMap<LocalDate, Integer> map = new HashMap<>();
        for (UserMeal cal : meals
        ) {
            int currentCalories = map.getOrDefault(cal.getDateTime().toLocalDate(), 0) + cal.getCalories();
            map.put(cal.getDateTime().toLocalDate(), currentCalories); // date & count of calories
        }
        for (UserMeal current : meals) {

            TimeUtil tu = new TimeUtil();

            if (tu.isBetweenHalfOpen(current.getDateTime().toLocalTime(), startTime, endTime)) {
                UserMealWithExcess um = new UserMealWithExcess(current.getDateTime(), current.getDescription(), current.getCalories(),
                        map.get(current.getDateTime().toLocalDate()) > caloriesPerDay);
                correctMeals.add(um);
            }
        }
        return correctMeals;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        HashMap<LocalDate, Integer> dateCalories = new HashMap<>();
        meals.stream()
                .map(q -> dateCalories.put(q.getDateTime().toLocalDate(), dateCalories.getOrDefault(q.getDateTime().toLocalDate(), 0)
                        + q.getCalories()))
                .collect(Collectors.toList());


        List<UserMealWithExcess> list = new ArrayList(meals.size());

        meals.stream()
                .filter(p -> TimeUtil.isBetweenHalfOpen(p.getDateTime().toLocalTime(), startTime, endTime))
                .map(q -> {
                    UserMealWithExcess um = new UserMealWithExcess(q.getDateTime(), q.getDescription(), q.getCalories(), dateCalories.get(q.getDateTime().toLocalDate()) > caloriesPerDay);
                    return list.add(um);
                })
                .collect(Collectors.toList());

        return list;
    }


}
