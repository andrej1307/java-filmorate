package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.yandex.practicum.filmorate.controller.OnUpdate;

/**
 * Класс данных для наследования классов модел
 */
@Data
public class StorageData {
    @NotNull(groups = {OnUpdate.class}, message = "id должен быть определен")
    Integer id;
}
