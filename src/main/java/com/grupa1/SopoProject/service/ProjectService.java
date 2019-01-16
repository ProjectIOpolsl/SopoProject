package com.grupa1.SopoProject.service;

import com.grupa1.SopoProject.database.Project;
import com.grupa1.SopoProject.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michal on 16.01.2019
 */

@Component
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

}
