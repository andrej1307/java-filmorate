package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.Storages;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;

/**
 * Класс реализации запросов к информации о фильмах
 */
@Slf4j
@Service
public class FilmService {

    @Autowired
    FilmStorage films = Storages.getFilmStorage();

    /**
     * Метод поиска всех фильмов
     *
     * @return - список фильмов
     */
    public Collection<Film> findAllFilms() {
        log.debug("Service: Get all films {}.", films.findAllFilms().size());
        return films.findAllFilms();
    }

    /**
     * Метод поиска фильма по идентификатору
     * @param id - идентификатор
     * @return - найденный фильм
     */
    public Film getFilmById(Integer id) {
        log.debug("Service: Get film id={}.", id);
        return films.getFilmById(id);
    }

    /**
     * Метод добавления нового фильма.
     *
     * @param film - объект для добавления
     * @return - подтверждение добавленного объекта
     */
    public Film addNewFilm(Film film) {
        log.debug("Service: Creating film: {}.", film.toString());
        return films.addNewFilm(film);
    }

    /**
     * Метод обновления информации о фильме.
     *
     * @param updFilm - объект с обновленной информацией о фильме
     * @return - подтверждение обновленного объекта
     */
    public Film updateFilm(Film updFilm) {
        Integer id = updFilm.getId();
        Film film = new Film(films.getFilmById(id));

        // Обновляем информаию во временном объекте
        if (updFilm.getName() != null) {
            film.setName(updFilm.getName());
        }
        if (updFilm.getDescription() != null) {
            film.setDescription(updFilm.getDescription());
        }
        if (updFilm.getReleaseDate() != null) {
            film.setReleaseDate(updFilm.getReleaseDate());
        }
        if (updFilm.getDuration() > 0) {
            film.setDuration(updFilm.getDuration());
        }

        log.debug("Service: Updating film id={} : {}", id, film.toString());
        return films.updateFilm(film);
    }

    /**
     * Удаление всех фильмов
     *
     * @return - сообщение о выполнении
     */
    public String onDelete() {
        log.debug("Service: Deleting all films.");
        films.removeAllFilms();
        return "All films deleted.";
    }

}
