package com.project.test.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "organization")
public class OrgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id", length = 50)
    private int orgId;
    @Column(name = "fullname")
    private String name;
    private String jabatan;
    @Column(name = "user_id")
    private Long userId;
}
