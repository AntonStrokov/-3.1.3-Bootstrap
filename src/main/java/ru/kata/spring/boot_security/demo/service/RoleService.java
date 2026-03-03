package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
	List<Role> getAllRoles();
	List<Role> getRolesByIds(List<Long> ids); // Для массовой загрузки (требование ментора)
	Optional<Role> getRoleById(Long id);      // Для одиночного поиска
	Optional<Role> getRoleByName(String name);
}
