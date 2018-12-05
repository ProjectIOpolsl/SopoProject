package com.grupa1.SopoProject.repositories;

import com.grupa1.SopoProject.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Michal on 05.12.2018
 */
interface UserRepository extends JpaRepository<Long,User> {
}
