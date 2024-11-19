package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.AbstractController;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тестирование ограничений на значения полей класса Film
 */
class FilmTest {
    final AbstractController<Film> films = new AbstractController<>();

    /**
     * Проверка непустого названия фильма.
     */
    @Test
    void testName() {
        Film film = new Film("",
                "Testing film.name",
                LocalDate.now().minusYears(10),
                60);

        assertThrows(ConstraintViolationException.class,
                () -> {
                    films.validate(film);
                },
                "Использование фильма с пустым именем должно приводить к исключению.");
    }

    /**
     * Проверка допусимого размера описания.
     */
    @Test
    void testDescription() {
        Film film = new Film("Film",
                "12345678901234567890123456789012345678901234567890"
                        + "12345678901234567890123456789012345678901234567890"
                        + "12345678901234567890123456789012345678901234567890"
                        + "12345678901234567890123456789012345678901234567890"
                        + "12345678901234567890123456789012345678901234567890",
                LocalDate.now().minusYears(10),
                60);

        assertThrows(ConstraintViolationException.class,
                () -> {
                    films.validate(film);
                },
                "Описание фильма длиннее 200 символов должно приводить к исключению.");
    }

    /**
     * Проверка допустимой даты выпуска фильма
     */
    @Test
    void testReleaseDate() {
        Film film = new Film("Film",
                "Testing film.releaseDate",
                LocalDate.now().plusDays(10),
                60);
        assertThrows(ConstraintViolationException.class,
                () -> {
                    films.validate(film);
                },
                "Фильм с датой выхода в будущем должен приводить к исключению.");

        film.setReleaseDate(LocalDate.of(1895, 12, 27));
        assertThrows(ConstraintViolationException.class,
                () -> {
                    films.validate(film);
                },
                "Фильм с датой выхода ранее 28 декабря 1895 должен приводить к исключению.");
    }

    /**
     * Проверка допустимой длительности фильма
     */
    @Test
    void testDuration() {
        Film film = new Film("Film",
                "Testing film.releaseDate",
                LocalDate.now().minusYears(10),
                0);
        assertThrows(ConstraintViolationException.class,
                () -> {
                    films.validate(film);
                },
                "Фильм с неположительной длительностью должен приводить к исключению.");
    }

}