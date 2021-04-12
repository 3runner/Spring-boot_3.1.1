package name.russkikh.service;

import name.russkikh.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    void save(User user);

    User getOne(long id);

    void deleteById(long id);

    Optional<User> findById(long id);

    Optional<User> findUserByName(String username);
}