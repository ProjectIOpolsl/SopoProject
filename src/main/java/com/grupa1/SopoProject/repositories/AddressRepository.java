package com.grupa1.SopoProject.repositories;

import com.grupa1.SopoProject.database.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Michal on 22.12.2018
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a from Address a WHERE a.id=?1")
    Address getAddressById(Long id);
}
