package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Marker;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

/**
 * Класс обработки http запросов к информации о фильмах.
 */
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }

    /**
     * Метод поиска всех фильмов
     *
     * @return - список фильмов
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Film> findAllFilms() {
        log.info("Get all films {}.", service.findAllFilms().size());
        return service.findAllFilms();
    }

    /**
     * Метод поиска фильма по идентификатору
     * @param id - идентификатор
     * @return - найденный фильм
     */
    @GetMapping("/{id}")
    public Film findFilm(@PathVariable Integer id) {
        log.info("Get film id={}.", id);
        return service.getFilmById(id);
    }

    /**
     * Метод добавления нового фильма.
     *
     * @param film - объект для добавления
     * @return - подтверждение добавленного объекта
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film addNewFilm(@Validated(Marker.OnBasic.class) @RequestBody Film film) {
        log.info("Creating film: {}.", film.toString());
        return service.addNewFilm(film);
    }

    /**
     * Метод обновления информации о фильме.
     * При вызове метода промзводится проверка аннотаций только для маркера OnUpdate.class.
     * Кроме id любой другой параметр может отсутствовать
     *
     * @param updFilm - объект с обновленной информацией о фильме
     * @return - подтверждение обновленного объекта
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Film updateFilm(@Validated(Marker.OnUpdate.class) @RequestBody Film updFilm) {
        Integer id = updFilm.getId();
        log.info("Updating film id={} : {}", id, updFilm.toString());
        return service.updateFilm(updFilm);
    }

    /**
     * Удаление всех фильмов
     *
     * @return - сообщение о выполнении
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String onDelete() {
        log.info("Deleting all films.");
        return service.onDelete();
    }

}
