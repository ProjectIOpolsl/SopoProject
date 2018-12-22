package com.grupa1.SopoProject.database;

import javax.persistence.*;

/**
 * @author Michal on 22.12.2018
 */

@Entity
public class AuditData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public AuditData() {
    }
}
