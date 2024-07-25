package com.project.test.service;


import com.project.test.model.dto.GlobalResponse;
import com.project.test.model.entity.RoleEntity;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface RolesService {
	ResponseEntity<GlobalResponse> findAll();
	ResponseEntity<GlobalResponse> findByUuid(UUID uuid);
	ResponseEntity<GlobalResponse> save(RoleEntity role, String token);
	ResponseEntity<GlobalResponse> update(RoleEntity role, String token);
}
