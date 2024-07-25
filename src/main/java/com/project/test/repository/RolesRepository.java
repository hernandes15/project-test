package com.project.test.repository;


import com.project.test.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface RolesRepository extends JpaRepository<RoleEntity, Long>{
	@Query("select e from RoleEntity e where e.rolesName not in ('SUPERADMIN')")
	List<RoleEntity> findAllRoles();
	@Query(value = "select user_id from roles r, user_roles ur where r.roles_id = ur.roles_id and r.roles_name = 'SUPERADMIN'", nativeQuery = true)
	public List<Integer> findUserIdByRolesName();
	public RoleEntity findByRolesUuid(UUID uuid);
}
