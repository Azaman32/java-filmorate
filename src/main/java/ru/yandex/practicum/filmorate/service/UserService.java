package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.UserIdGenerator;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserStorage userStorage;
    private final UserIdGenerator userIdGenerator = new UserIdGenerator();

    @Autowired
    public UserService(UserStorage inMemoryUserStorage) {
        this.userStorage = inMemoryUserStorage;
    }

    public void addFriend(int userId, int userFriendId) {
        userExist(userId);
        userExist(userFriendId);
        userStorage.get(userId).setFriends(userFriendId);
        userStorage.get(userFriendId).setFriends(userId);
    }

    public void deleteFriend(int userId, int friendId) {
        userExist(userId);
        userExist(friendId);
        userStorage.get(userId).getFriends().remove(friendId);
    }

    public List<User> commonFriends(int userId, int userFriendId) {
        userExist(userId);
        userExist(userFriendId);
        ArrayList<User> commonFriends = new ArrayList<>();
        for (int friends : userStorage.get(userId).getFriends()) {
            for (int userFriend : userStorage.get(userFriendId).getFriends()) {
                if (friends == userFriend) {
                    commonFriends.add(userStorage.get(friends));
                }
            }
        }
        return commonFriends;
    }

    public User saveUser(User user) {
        user.setId(userIdGenerator.generate());
        userStorage.save(user);
        return user;
    }

    public User getUser(int id) {
        userExist(id);
        return userStorage.get(id);
    }

    public void updateUser(User user) {
        userExist(user.getId());
        userStorage.save(user);
    }

    public List<User> getAllUsers() {
        return userStorage.getAll();
    }

    public List<User> getFriends(int userId) {
        userExist(userId);
        ArrayList<User> userFriends = new ArrayList<>();
        for (int friends : userStorage.get(userId).getFriends()) {
            userFriends.add(userStorage.get(friends));
        }
        return userFriends;
    }

    public void userExist(int userId) {
        if (userStorage.get(userId) == null) {
            throw new UserNotFoundException("User does not exist");
        }
    }
}
