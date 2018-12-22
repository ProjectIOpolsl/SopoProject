package com.grupa1.SopoProject.repositories;

import com.grupa1.SopoProject.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Michal on 05.12.2018
 */
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u from User u WHERE u.identifierNo = ?1")
    User findUserByIdentifierNo(String identifierNo);
}
