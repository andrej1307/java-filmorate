package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryAbstractStorage;

import java.util.Collection;

@Component
public class InMemoryFilmStorage extends InMemoryAbstractStorage<Film> implements FilmStorage {

    @Override
    public Film addNewFilm(Film newFilm) {
        return super.addNew(newFilm);
    }

    @Override
    public Film getFilmById(Integer id) {
        return super.getElement(id);
    }

    @Override
    public Collection<Film> findAllFilms() {
        return super.findAll();
    }

    @Override
    public Film updateFilm(Film updFilm) {
        return super.update(updFilm);
    }

    @Override
    public Integer addNewLike(Integer FilmId, Integer friendId) {
        return 0;
    }

    @Override
    public void removeAllFilms() {
        super.clear();
    }
}
