package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс данных для наследования классов модели
 */
@Data
public class StorageData {
    @NotNull(groups = {Marker.OnUpdate.class}, message = "id должен быть определен")
    protected Integer id;
}
