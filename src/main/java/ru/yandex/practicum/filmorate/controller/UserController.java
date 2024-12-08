package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Marker;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;

/**
 * Класс обработки http запросов о пользователях.
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Метод поиска всех пользователей
     *
     * @return - список пользователей
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> findAllUser() {
        log.info("Get all users {}.", service.findAllUsers().size());
        return service.findAllUsers();
    }

    /**
     * Метод добавления нового пользователя.
     *
     * @param user - объект для добавления
     * @return - подтверждение добавленного объекта
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addNewUser(@Validated(Marker.OnBasic.class) @RequestBody User user) {
        log.info("Creating user : {}.", user.toString());
        return service.addNewUser(user);
    }

    /**
     * Метод обновления информации о пользователе.
     * При вызове метода промзводится проверка аннотаций только для маркера OnUpdate.class.
     * Кроме id любой другой параметр может отсутствовать
     *
     * @param updUser - объект с обновленной информацией о пользователе
     * @return - подтверждение обновленного объекта
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Validated(Marker.OnUpdate.class) @RequestBody User updUser) {
        Integer id = updUser.getId();
        log.info("Updating user id={} : {}", id, updUser.toString());
        return service.updateUser(updUser);
    }

    /**
     * Удаление всех пользователей
     *
     * @return - сообщение о выполнении
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String onDelete() {
        log.info("Deleting all users.");
        return service.removeAllUsers();
    }

}
