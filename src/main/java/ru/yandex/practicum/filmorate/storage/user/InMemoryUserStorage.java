package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryAbstractStorage;

import java.util.Collection;

@Component
public class InMemoryUserStorage extends InMemoryAbstractStorage<User> implements UserStorage {

    @Override
    public User addNewUser(User newUser) {
        return super.addNew(newUser);
    }

    @Override
    public User getUserById(Integer id) {
        return super.getElement(id);
    }

    @Override
    public Collection<User> findAllUsers() {
        return super.findAll();
    }

    @Override
    public User updateUser(User updUser) {
        return super.update(updUser);
    }

    @Override
    public Integer addNewFriend(Integer Integer, Integer friendId) {
        return 0;
    }

    @Override
    public void removeFriend(Integer userId, Integer friendId) {
    }

    @Override
    public void removeAllUsers() {
        super.clear();
    }
}
