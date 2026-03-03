package ru.kata.spring.boot_security.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public List<Role> getRolesByIds(List<Long> ids) {
		// Вызываем метод в DAO, который делает SELECT ... WHERE id IN :ids
		return roleDao.getRolesByIds(ids);
	}

	@Override
	public Optional<Role> getRoleById(Long id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}
}
