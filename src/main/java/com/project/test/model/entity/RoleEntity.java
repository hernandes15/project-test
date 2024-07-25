package com.project.test.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id", length = 50)
	private int rolesId;
	@Column(name = "roles_uuid")
	private UUID rolesUuid = UUID.randomUUID();
	@Column(name = "roles_name")
	private String rolesName;
	@Column(name = "menu_name")
	private String menuName;
	private String description;
	private Long active;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	@Column(name = "created_by", updatable = false)
	private UUID createdBy;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", insertable = false)
	private Date updateDate;
	@Column(name = "updated_by")
	private UUID updatedBy;
}
