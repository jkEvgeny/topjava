package ru.javawebinar.topjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));

            InMemoryUserRepository userRepository = new InMemoryUserRepository();
            Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

            User us = new User(null, "Misha", "2@2.ru", "222", Role.USER);
            User us1 = new User(null, "Oleg", "1@1.ru", "111", Role.USER);
            User us2 = new User(null, "Alex", "3@3.ru", "333", Role.USER);
            User us3 = new User(null, "Alex", "4@4.ru", "333", Role.USER);


            Meal m1 = new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, 1);
            Meal m2 = new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, 1);
            Meal m3 = new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, 2);
            Meal m4 = new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, 2);
            Meal m5 = new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, 2);
            Meal m6 = new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, 3);
            Meal m7 = new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, 3);

            MealRepository mealRepository = new InMemoryMealRepository();
            mealRepository.save(m1);
            mealRepository.save(m2);
            mealRepository.save(m3);
            mealRepository.save(m4);
            mealRepository.save(m5);
            mealRepository.save(m6);
            mealRepository.save(m7);
            //mealRepository.getAll(3);
            log.info(String.valueOf(mealRepository.getAll(1)));


            userRepository.save(us1);
            userRepository.save(us);
            userRepository.save(us2);
            userRepository.save(us3);

            log.info(String.valueOf(userRepository.getByEmail("2@2.ru")));

            System.out.println();
        }
    }
}
