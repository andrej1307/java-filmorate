package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {
    // добавление нового пользователя
    User addNewUser(User newUser);

    // чтение пользователя по идентификатору
    User getUserById(Integer id);

    // чтение всех пользователей
    Collection<User> findAllUsers();

    User updateUser(User updUser);

    Integer addNewFriend(Integer userId, Integer friendId);

    void removeFriend(Integer userId, Integer friendId);

    void removeAllUsers();
}
