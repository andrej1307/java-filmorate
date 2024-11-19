package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.AbstractController;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тестирование ограничений на значения полей класса User.
 */
class UserTest {
    final AbstractController<User> users = new AbstractController<>();

    /**
     * Тестирование Email пользователя
     */
    @Test
    void testEmail() {
        User user = new User(null,
                "user1234",
                "Testing user",
                LocalDate.now().minusYears(32));
        assertThrows(ConstraintViolationException.class,
                () -> {
                    users.validate(user);
                },
                "Использование пользователя без Email должно приводить к исключению.");

        user.setEmail("user.domain@");
        assertThrows(ConstraintViolationException.class,
                () -> {
                    users.validate(user);
                },
                "Некорректный формат Email должен приводить к исключению.");
    }

    /**
     * Тестирование Login
     * логин не может быть пустым и содержать пробелы.
     */
    @Test
    void testLogin() {
        User user = new User("user@domain",
                "",
                "Testing user",
                LocalDate.now().minusYears(32));
        assertThrows(ConstraintViolationException.class,
                () -> {
                    users.validate(user);
                },
                "Пустой логин пользователя должен приводить к исключению.");

        user.setLogin("user 1234");
        assertThrows(ConstraintViolationException.class,
                () -> {
                    users.validate(user);
                },
                "Пробелы в логине пользователя должны приводить к исключению.");
    }

    @Test
    void testBirthday() {
        User user = new User("user@domain",
                "user1234",
                "Testing user",
                LocalDate.now().plusDays(1));
        assertThrows(ConstraintViolationException.class,
                () -> {
                    users.validate(user);
                },
                "Дата рождения пользователя в будущем должна приводить к исключению.");
    }
}
