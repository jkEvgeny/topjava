package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Meal get(int id);

    // ORDERED dateTime desc
    Collection<Meal> getAll();

    Map<Integer, Meal> repository = new ConcurrentHashMap<>();
}
