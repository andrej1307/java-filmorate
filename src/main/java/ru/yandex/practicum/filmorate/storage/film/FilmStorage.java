package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {
    // добавление нового фильма
    Film addNewFilm(Film newFilm);

    // чтение фильма по идентификатору
    Film getFilmById(Integer id);

    // чтение всех фильмов
    Collection<Film> findAllFilms();

    // изменение сведений о фильме
    Film updateFilm(Film updFilm);

    Integer addNewLike(Integer FilmId, Integer friendId);

    void removeAllFilms();
}
