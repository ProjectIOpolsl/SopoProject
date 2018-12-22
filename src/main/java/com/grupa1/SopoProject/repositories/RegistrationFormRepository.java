package com.grupa1.SopoProject.repositories;

import com.grupa1.SopoProject.database.Neighbourhood;
import com.grupa1.SopoProject.database.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import static org.hibernate.loader.Loader.SELECT;

/**
 * @author Michal on 22.12.2018
 */
@Repository
public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Long> {

    @Query("SELECT n from Neighbourhood n WHERE neighbourhoodName = ?1 ")
    Neighbourhood findNeighbourhoodByName(String neighbourhood);
}
