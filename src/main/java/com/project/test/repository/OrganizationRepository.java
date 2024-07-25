package com.project.test.repository;


import com.project.test.model.entity.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<OrgEntity, Long> {
    @Query("delete from OrgEntity e where e.orgId not in (:orgId)")
    public OrgEntity findByUserId(Long id);
}
