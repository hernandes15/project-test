package com.project.test.service.impl;



import com.project.test.config.security.JwtTokenProvider;
import com.project.test.enums.Message;
import com.project.test.model.dto.GlobalResponse;
import com.project.test.model.entity.RoleEntity;
import com.project.test.repository.RolesRepository;
import com.project.test.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepository repo;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public ResponseEntity<GlobalResponse> findAll() {
		GlobalResponse response = null;
		try {
			List<RoleEntity> roleEntity = repo.findAllRoles();
			
			if(roleEntity.size() > 0) {
				response = new GlobalResponse(200, Message.SUCCESS.getValue(), roleEntity);
			}else {
				response = new GlobalResponse(404, Message.NOTFOUND.getValue(), roleEntity);
			}
			return new ResponseEntity<>(response, response.getErrorCode() == 200 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Error find all roles in " + this.getClass().getName() + " : " , e);
			response = new GlobalResponse(500, Message.ERROR.getValue(), null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GlobalResponse> findByUuid(UUID uuid) {
		GlobalResponse response = null;
		try {
			RoleEntity roleEntity = repo.findByRolesUuid(uuid);
			
			if(!Objects.isNull(roleEntity)) {
				response = new GlobalResponse(200, Message.SUCCESS.getValue(), roleEntity);
			}else {
				response = new GlobalResponse(404, Message.NOTFOUND.getValue(), roleEntity);
			}
			return new ResponseEntity<>(response, response.getErrorCode() == 200 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Error find roles by uuid in " + this.getClass().getName() + " : " , e);
			response = new GlobalResponse(500, Message.ERROR.getValue(), null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GlobalResponse> save(RoleEntity role, String token) {
		GlobalResponse response = null;
		try {
			role.setCreatedBy(tokenProvider.getUserUuidFromJWT(token));
			RoleEntity roleEntity = repo.save(role);
			response = new GlobalResponse(200, Message.CREATED.getValue(), roleEntity.getRolesUuid());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error create role in " + this.getClass().getName() + " : " , e);
			response = new GlobalResponse(500, Message.ERROR.getValue(), null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GlobalResponse> update(RoleEntity role, String token) {
		GlobalResponse response = null;
		try {
			role.setUpdatedBy(tokenProvider.getUserUuidFromJWT(token));
			RoleEntity roleEntity = repo.save(role);
			response = new GlobalResponse(200, Message.UPDATED.getValue(), roleEntity.getRolesUuid());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error update role in " + this.getClass().getName() + " : " , e);
			response = new GlobalResponse(500, Message.ERROR.getValue(), null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
