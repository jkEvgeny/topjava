package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
@Service
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

    public Meal get(int id, int userId) {
        if (repository.get(id).getUserId() != null
                && repository.get(id).getUserId() != userId) {
            return checkNotFoundWithId(repository.get(id), id);
        } else {
            return null;
        }
    }

    public Collection<MealTo> getAll(int userId) {

        return MealsUtil.getFilteredTos(repository.getAll(userId)
                .stream()
                .filter(meal -> meal.getUserId()== userId)
                .collect(Collectors.toList()), MealsUtil.DEFAULT_CALORIES_PER_DAY, LocalTime.MIN, LocalTime.MAX);
    }

    public void update(Meal meal) {
        if (meal.getUserId() != null
            /*&& meal.getUserId() != SecurityUtil.authUserId()*/) {
            checkNotFoundWithId(repository.save(meal), meal.getId());
        }
    }
    public Collection<MealTo> getFilteredAll(int userId, LocalTime startTime, LocalTime endTime){
        return MealsUtil.getFilteredTos(repository.getAll(userId)
                .stream()
                .filter(meal -> meal.getUserId()== userId)
                .collect(Collectors.toList()), MealsUtil.DEFAULT_CALORIES_PER_DAY, startTime, endTime);
    }
}