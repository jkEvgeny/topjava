package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id) {
        if (repository.get(id).getUserId() != null
                /*&& repository.get(id).getUserId() != SecurityUtil.authUserId()*/) {
            checkNotFoundWithId(repository.delete(id), id);
        }
    }

    public Meal get(int id) {
        if (repository.get(id).getUserId() != null
                /*&& repository.get(id).getUserId() != SecurityUtil.authUserId()*/) {
            return checkNotFoundWithId(repository.get(id), id);
        } else {
            return null;
        }
    }

    public Collection<Meal> getAll() {
        repository.getAll()
                .stream()
                .map(meal -> ((repository.get(meal.getId()).getUserId() == null) /*&&
                        (repository.get(meal.getId()).getUserId() != SecurityUtil.authUserId())*/) ?
                        repository.delete(meal.getId()) : "")
                .collect(Collectors.toList());

        return repository.getAll();
    }

    public void update(Meal meal) {
        if (meal.getUserId() != null
                /*&& meal.getUserId() != SecurityUtil.authUserId()*/) {
            checkNotFoundWithId(repository.save(meal), meal.getId());
        }
    }
    public Collection<Meal> getFilteredAll(LocalTime startTime, LocalTime endTime){
        return repository.getAll()
                .stream()
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(),startTime, endTime))
                .collect(Collectors.toList());
    }
}