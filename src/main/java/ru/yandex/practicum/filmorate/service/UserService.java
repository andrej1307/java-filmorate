package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.Storages;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;

/**
 * Класс реализации запросов к информации о пользователях
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    UserStorage users = Storages.getUerStorage();

    /**
     * Метод поиска всех пользователей
     *
     * @return - список пользователей
     */
    public Collection<User> findAllUsers() {
        log.debug("Get all users {}.", users.findAllUsers().size());
        return users.findAllUsers();
    }

    /**
     * Метод добавления нового пользователя.
     *
     * @param user - объект для добавления
     * @return - подтверждение добавленного объекта
     */
    public User addNewUser(User user) {
        // "имя для отображения может быть пустым
        // — в таком случае будет использован логин" (ТЗ-№10)
        if (user.getName() == null | user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        log.debug("Creating user : {}.", user.toString());
        return users.addNewUser(user);
    }

    /**
     * Метод чтения информации о пользователе по заданному идентификатору
     *
     * @param id - идентификатор пользователя
     * @return - найденный объект
     */
    public User getUserById(Integer id) {
        return users.getUserById(id);
    }

    /**
     * Метод обновления информации о пользователе.
     * При вызове метода промзводится проверка аннотаций только для маркера OnUpdate.class.
     * Кроме id любой другой параметр может отсутствовать
     *
     * @param updUser - объект с обновленной информацией о пользователе
     * @return - обновленный объект
     */
    public User updateUser(User updUser) {
        Integer id = updUser.getId();
        User user = new User(users.getUserById(id));

        // Обновляем информаию во временном объекте
        if (updUser.getEmail() != null) {
            user.setEmail(updUser.getEmail());
        }
        if (updUser.getLogin() != null) {
            user.setLogin(updUser.getLogin());
        }
        if (updUser.getName() != null) {
            user.setName(updUser.getName());
        }
        if (updUser.getBirthday() != null) {
            user.setBirthday(updUser.getBirthday());
        }

        log.debug("Updating user id={} : {}", id, user.toString());
        return users.updateUser(user);
    }

    /**
     * Удаление всех пользователей
     *
     * @return - сообщение о выполнении
     */
    public String removeAllUsers() {
        log.debug("Deleting all users.");
        users.removeAllUsers();
        return "All users deleted.";
    }
}
