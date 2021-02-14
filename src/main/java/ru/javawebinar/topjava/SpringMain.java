package ru.javawebinar.topjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.web.user.AdminRestController;

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

            userRepository.save(us1);
            userRepository.save(us);
            userRepository.save(us2);
            userRepository.save(us3);

            log.info(String.valueOf(userRepository.getByEmail("2@2.ru")));

            System.out.println();
        }
    }
}
