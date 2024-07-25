package com.project.test.controller;


import com.project.test.model.dto.GlobalResponse;
import com.project.test.model.entity.RoleEntity;
import com.project.test.service.RolesService;
import com.project.test.util.BaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/roles" , produces = MediaType.APPLICATION_JSON_VALUE)
public class RolesController {
	
	@Autowired
	private RolesService service;
	
	@Autowired
	private BaseHelper helper;
	
	@GetMapping("/find")
    public ResponseEntity<GlobalResponse> findAllRoles() throws Exception {
        return service.findAll();
    }
	
	@GetMapping("/find/{uuid}")
    public ResponseEntity<GlobalResponse> findRoles(@PathVariable UUID uuid) throws Exception {
        return service.findByUuid(uuid);
    }
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalResponse> saveRoles(HttpServletRequest request, @RequestBody RoleEntity param) throws Exception {
        return service.save(param, helper.getJwtFromRequest(request));
    }
	
	@PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalResponse> editRoles(HttpServletRequest request, @RequestBody RoleEntity param) throws Exception {
        return service.update(param, helper.getJwtFromRequest(request));
    }
}
