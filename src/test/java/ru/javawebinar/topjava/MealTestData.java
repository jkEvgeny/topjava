package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int START_MEAL_SEQ = 200000;
    public static final int MEAL0_ID = START_MEAL_SEQ;
    public static final int MEAL1_ID = START_MEAL_SEQ + 1;
    public static final int MEAL2_ID = START_MEAL_SEQ + 2;
    public static final int MEAL3_ID = START_MEAL_SEQ + 3;
    public static final int MEAL4_ID = START_MEAL_SEQ + 4;
    public static final int MEAL5_ID = START_MEAL_SEQ + 5;
    public static final int MEAL6_ID = START_MEAL_SEQ + 6;
    public static final int MEAL7_ID = START_MEAL_SEQ + 7;
    public static final int MEAL8_ID = START_MEAL_SEQ + 8;

    public static final int NOT_FOUND = 10;

    public static final Meal meal0 = new Meal(MEAL0_ID, of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal meal1 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal meal2 = new Meal(MEAL2_ID, of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal meal3 = new Meal(MEAL3_ID, of(2020, Month.JANUARY, 31, 10, 0), "Еда на граничное значение", 100);
    public static final Meal meal4 = new Meal(MEAL4_ID, of(2020, Month.JANUARY, 31, 13, 0), "Завтрак", 1000);
    public static final Meal meal5 = new Meal(MEAL5_ID, of(2020, Month.JANUARY, 31, 20, 0), "Обед", 500);
    public static final Meal meal6 = new Meal(MEAL6_ID, of(2020, Month.JANUARY, 31, 14, 0), "Ужин", 410);
    public static final Meal meal7 = new Meal(MEAL7_ID, of(2020, Month.JANUARY, 31, 14, 0), "Админ ланч", 510);
    public static final Meal meal8 = new Meal(MEAL8_ID, of(2020, Month.JANUARY, 31, 20, 0), "Админ ужин", 1500);

    public static final List<Meal> MEALS = List.of(meal0, meal1, meal2, meal3, meal4, meal5, meal6, meal7, meal8);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "description", 300);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL0_ID, meal0.getDateTime(), "Обновленный", 500);
        return updated;
    }

    public static Meal getCreated() {
        return new Meal(null, of(2020, Month.JANUARY, 31, 18, 0), "Созданный", 300);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user_id");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user_id").isEqualTo(expected);
    }
}
