package ru.yandex.practicum.filmorate.storage.genre;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.mapper.GenreRowMapper;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDbStorage implements GenreStorage {

    private static final String SQL_GET_ALL_GENRES = "SELECT * FROM genres";
    private static final String SQL_GET_GENRE = "SELECT * FROM genres WHERE id = :id";

    private final NamedParameterJdbcTemplate jdbc;
    private final GenreRowMapper mapper;

    public GenreDbStorage(NamedParameterJdbcTemplate jdbc, GenreRowMapper mapper) {
        this.jdbc = jdbc;
        this.mapper = mapper;
    }

    /**
     * Чтение всех жанров в справочнике
     *
     * @return
     */
    @Override
    public Collection<Genre> getAllGenres() {
        try {
            return jdbc.query(SQL_GET_ALL_GENRES, mapper);
        } catch (EmptyResultDataAccessException ignored) {
            return List.of();
        }
    }

    /**
     * чтение жанра по идентификатору
     *
     * @param id - идентификатор жанра
     * @return - объект Optional
     */
    @Override
    public Optional<Genre> getGenre(Integer id) {
        try {
            Genre genre = jdbc.queryForObject(SQL_GET_GENRE,
                    new MapSqlParameterSource()
                            .addValue("id", id),
                    mapper);
            return Optional.ofNullable(genre);
        } catch (EmptyResultDataAccessException ignored) {
            return Optional.empty();
        }
    }
}
