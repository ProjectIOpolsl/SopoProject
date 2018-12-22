package com.grupa1.SopoProject.repositories;

import com.grupa1.SopoProject.database.Privilige;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Michal on 22.12.2018
 */
@Repository
public interface PriviligeRepository extends JpaRepository<Privilige, Long> {
   /* @Query("SELECT p from Privilige WHERE p.")
    List<Privilige> getPriviligesByUserId(Long userId);*/
}
