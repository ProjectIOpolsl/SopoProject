package com.grupa1.SopoProject.repositories;

import com.grupa1.SopoProject.database.Neighbourhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Michal on 22.12.2018
 */

@Repository
public interface NeighbourhoodRepository extends JpaRepository<Neighbourhood, Long> {
}
