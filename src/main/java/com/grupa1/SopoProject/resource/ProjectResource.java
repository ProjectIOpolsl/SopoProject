package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.database.*;
import com.grupa1.SopoProject.dto.*;
import com.grupa1.SopoProject.forApiDocs.WSCreateNewProject;
import com.grupa1.SopoProject.handlers.InvalidProjectExcpetion;
import com.grupa1.SopoProject.handlers.UserAlreadyVotedException;
import com.grupa1.SopoProject.handlers.ValidationException;
import com.grupa1.SopoProject.repositories.*;
import com.grupa1.SopoProject.security.TokenUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal on 22.12.2018
 */

@Api(value = "Projects resource", description = "Endpoint for managing projects",
        consumes = "application/json", produces = "application/json",tags = {"Projects Resource"})
@RestController
@RequestMapping("/projectManagement")
public class ProjectResource {

    final static Logger logger = Logger.getLogger(ProjectResource.class);

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;

    @Autowired
    private AccountRepository accountRepository;

    @ApiOperation(value = "Create project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Project created", response = WSLoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class)
    })
    @PostMapping(value = "/createProject", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createProject(@ApiParam(hidden = true) @RequestHeader("Authorization") String encoding,
                                           @RequestBody WSCreateNewProject wsProject){
        String email = tokenUtil.getEmailFromToken(encoding);
        try{
            wsProject.validateData();
        } catch(InvalidProjectExcpetion ex){
            logger.info("Create project data validation failed: " + wsProject.getProjectName()+"\n"+ex.getMessage());
            return new ResponseEntity<>(new WSError(ex.getMessage(),"/projectManagement/createProject"),HttpStatus.BAD_REQUEST);
        } catch (ValidationException ex){
            logger.info("Create project data validation failed: " + wsProject.getProjectName());
            return new ResponseEntity<>(new WSError(ex.getMessage(),"/projectManagement/createProject"),HttpStatus.BAD_REQUEST);
        }
        Neighbourhood neighbourhood = neighbourhoodRepository.findByName(wsProject.getNeighbourhood());
        if(neighbourhood == null){
            logger.info("No such neighbourhood in database: " + wsProject.getNeighbourhood());
            return new ResponseEntity<>(new WSError("Invalid neighbourhood parameter","/projectManagement/createProject"),HttpStatus.BAD_REQUEST);
        }
        Account account = accountRepository.findByEmail(email);
        Project project = new Project(wsProject.getProjectName(),account.getUser(),wsProject.getBudget(),
                neighbourhood,wsProject.getDescription(),null,wsProject.getAddress());
        project = projectRepository.save(project);
        return new ResponseEntity<>(new WSProject(project.getId(),project.getProjectName(), project.getBudget(),
                project.getNeighbourhood().getNeighbourhoodName(),
                project.getDescription(),project.getAddress(),project.getVoteAmount()),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete project // NOT VALID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Project deleted", response = WSLoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class)
    })
    @PostMapping(value = "/deleteProject", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> deleteProject(@RequestBody WSProject wsProject){
        //TODO postponed implementation
        return null;
    }

    @ApiOperation(value = "Get all projects")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Projects acquired", response = WSListOfProjects.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class)
    })
    @GetMapping(value = "/getAllProjects", produces = "application/json")
    public ResponseEntity<?> getAllProjects(){
        List<Project> listOfProjects = projectRepository.findAll();
        List<WSProject> listOfWSProjects = new ArrayList<>();
        listOfProjects.stream().forEach( p -> {
            listOfWSProjects.add(new WSProject(p.getId(),p.getProjectName(),p.getBudget(),p.getNeighbourhood().getNeighbourhoodName(),
            p.getDescription(),p.getAddress(),p.getVoteAmount()));
            });
        WSListOfProjects wsListOfProjects = new WSListOfProjects(listOfWSProjects);
        return new ResponseEntity<>(wsListOfProjects,HttpStatus.OK);
    }

    @ApiOperation(value = "Add comment from project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comment added succesfully"),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class),
            @ApiResponse(code = 500, message = "Internal server error. Issue not know, contact with backend", response = WSError.class)
    })
    @PostMapping(value = "/addCommentToProject", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addCommentToProject(@ApiParam(hidden = true) @RequestHeader("Authorization") String encoding,
                                                 @RequestBody WSComment wsComment){
        String email = tokenUtil.getEmailFromToken(encoding);
        try{
        Project project = projectRepository.searchById(wsComment.getProjectId());
        if(project == null){
            logger.info("No such project found: " +wsComment.getProjectId());
            return new ResponseEntity<>(new WSError("No such project found "+wsComment.getProjectId(),"/projectManagement/addCommentToProject"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ProjectComment projectComment = new ProjectComment(wsComment.getComment(),email);
        projectComment.addCommentToProject(project);
        project.addCommentToProject(projectComment);
        projectRepository.save(project);
        } catch( Exception ex){
            logger.info("Failed to add comment to project number: " +wsComment.getProjectId());
            return new ResponseEntity<>(new WSError(ex.getMessage(),"/projectManagement/addCommentToProject"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Remove comment to project // NOT VALID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comment added succesfully", response = WSLoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class)
    })
    @PostMapping(value = "/removeCommentToProject", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> removeCommentFromProject(){
        //Todo implement later
        return null;
    }

    @ApiOperation(value = "Vote for project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vote completed", response = WSProject.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class),
            @ApiResponse(code = 409, message = "User have already voted for this project", response = WSError.class),
            @ApiResponse(code = 500, message = "Internal server error. Issue not know, contact with backend", response = WSError.class)
    })
    @GetMapping(value = "/voteForProject/project/{projectId}", produces = "application/json")
    public ResponseEntity<?> voteForProject(@ApiParam(hidden = true) @RequestHeader("Authorization") String encoding,
                                            @PathVariable("projectId") Long projectId){
        String email = tokenUtil.getEmailFromToken(encoding);
        Project project = projectRepository.searchById(projectId);
        Account account = accountRepository.findByEmail(email);
        if(project == null){
            return new ResponseEntity<>(new WSError("Project not found in database!","/projectManagement/voteForProject/project/"+projectId),HttpStatus.CONFLICT);
        }
        if(projectId != null){
            try {
                project.vote(account.getUser());
                projectRepository.save(project);
            } catch (UserAlreadyVotedException ex) {
                logger.info("User have already voted for project: "  +project.getProjectName());
                return new ResponseEntity<>(new WSError(ex.getMessage(),"/projectManagement/voteForProject/project/"+projectId),HttpStatus.CONFLICT);
            } catch (Exception ex){
                return new ResponseEntity<>(new WSError(ex.getMessage(),"/projectManagement/voteForProject/project/"+projectId),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(new WSProject(project.getId(),project.getProjectName(),project.getBudget(),
                project.getNeighbourhood().getNeighbourhoodName(),project.getDescription(),project.getAddress(),project.getVoteAmount()),HttpStatus.OK);
    }

    @ApiOperation(value = "Get comments from project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comments pulled", response = WSListOfComments.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class),
            @ApiResponse(code = 500, message = "Internal server error. Issue not know, contact with backend", response = WSError.class)
    })
    @GetMapping(value = "/getComments/project/{projectId}", produces = "application/json")
    public ResponseEntity<?> getComments(@PathVariable("projectId") Long projectId){
        Project project = projectRepository.searchById(projectId);
        List<ProjectComment> projectCommentList;
        WSListOfComments listOfComments = new WSListOfComments();
        if(project!=null){
            projectCommentList = project.getProjectComments() != null ? project.getProjectComments() : new ArrayList<>();
            projectCommentList.stream().forEach( p -> listOfComments.addToList(new WSCommentResponse(p.getComment(),project.getId(),p.getEmial())));
        }
        return new ResponseEntity<>(listOfComments,HttpStatus.OK);
    }
}
