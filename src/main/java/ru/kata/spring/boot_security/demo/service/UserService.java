package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

	void addUser(User user, List<Long> roleIds);

	void updateUser(User user, List<Long> roleIds);

	void removeUser(Long id);

	User getUserById(Long id);

	List<User> getAllUsers();
}