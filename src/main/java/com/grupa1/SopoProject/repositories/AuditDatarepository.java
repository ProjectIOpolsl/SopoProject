package com.grupa1.SopoProject.repositories;

import com.grupa1.SopoProject.database.AuditData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Michal on 22.12.2018
 */

@Repository
public interface AuditDatarepository extends JpaRepository<AuditData, Long> {
}
