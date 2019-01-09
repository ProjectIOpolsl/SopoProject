package com.grupa1.SopoProject.resource;

import com.grupa1.SopoProject.database.*;
import com.grupa1.SopoProject.dto.*;
import com.grupa1.SopoProject.repositories.*;
import com.grupa1.SopoProject.security.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectCommentRepository projectCommentRepository;

    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;

    @Autowired
    private AccountRepository accountRepository;

    @ApiOperation(value = "Create project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Project created", response = LoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class)
    })
    @PostMapping(value = "/createProject", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createProject(@RequestBody WSProject wsProject){
        try{
            wsProject.validateData();
        } catch(ValidationException ex){
            return new ResponseEntity<>(new WSError(ex.getMessage(),"/projectManagement/createProject"),HttpStatus.BAD_REQUEST);
        }
        Neighbourhood neighbourhood = neighbourhoodRepository.findByName(wsProject.getNeighbourhood());
        if(neighbourhood == null){
            return new ResponseEntity<>(new WSError("Invalid neighbourhood parameter","/projectManagement/createProject"),HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findUserByIdentifierNo("11111");
        Project project = new Project(wsProject.getProjectName(),user,wsProject.getBudget(),
                neighbourhood,wsProject.getDescription(),null);
        projectRepository.save(project);
        return new ResponseEntity<>(new WSProject(project.getProjectName(), project.getBudget(),
                project.getNeighbourhood().getNeighbourhoodName(),
                project.getDescription()),HttpStatus.OK);

    }

    @ApiOperation(value = "Delete project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Project deleted", response = LoginResponse.class),
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
            @ApiResponse(code = 200, message = "Projects acquired", response = LoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class)
    })
    @GetMapping(value = "/getAllProjects", produces = "application/json")
    public ResponseEntity<?> getAllProjects(){
        List<Project> listOfProjects = projectRepository.findAll();
        List<WSProject> listOfWSProjects = new ArrayList<>();
        listOfProjects.stream().forEach( p -> {
            listOfWSProjects.add(new WSProject(p.getProjectName(),p.getBudget(),p.getNeighbourhood().getNeighbourhoodName(),
            p.getDescription()));
        });
        WSListOfProjects wsListOfProjects = new WSListOfProjects(listOfWSProjects);
        return new ResponseEntity<>(wsListOfProjects,HttpStatus.OK);
    }

    @ApiOperation(value = "Add comment from project")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comment added succesfully", response = LoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class),
            @ApiResponse(code = 500, message = "Internal server error. Issue not know, contact with backend", response = WSError.class)
    })
    @PostMapping(value = "/addCommentToProject", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addCommentToProject(@RequestBody WSComment wsComment){
        try{
        Project project = projectRepository.getOne(wsComment.getProjectId());
        ProjectComment projectComment = new ProjectComment(wsComment.getComment());
        projectComment.addCommentToProject(project);
        project.addCommentToProject(projectComment);
        projectRepository.save(project);
        projectCommentRepository.save(projectComment);
        } catch( Exception ex){
            return new ResponseEntity<>(new WSError(ex.getMessage(),"/projectManagement/addCommentToProject"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Remove comment to project // NOT VALID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Comment added succesfully", response = LoginResponse.class),
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
            @ApiResponse(code = 200, message = "Vote completed", response = LoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid token access or access denied", response = WSError.class),
            @ApiResponse(code = 403, message = "Forbidden access. Your account has been blocked", response = WSError.class),
            @ApiResponse(code = 409, message = "User have already voted for this project", response = WSError.class)
    })
    @GetMapping(value = "/voteForProject/project/{projectId}", produces = "application/json")
    public ResponseEntity<?> voteForProject(@RequestHeader("Authorization") String encoding,
                                            @PathVariable("projectId") Long projectId){
        if(projectId==null){
            return new ResponseEntity<>(new WSError("ProjectId not passed","/projectManagement/voteForProject/project/???"),HttpStatus.BAD_REQUEST);
        }
        encoding = encoding.substring(7,encoding.length()); // Remove 'Bearer' keyword
        String email = tokenUtil.getEmailFromToken(encoding);
        Project project = projectRepository.searchById(projectId);
        Account account = accountRepository.findByEmail(email);
        if(projectId != null){
            try {
                project.vote(account.getUser());
            } catch (UserAlreadyVotedException ex) {
                return new ResponseEntity<>(new WSError(ex.getMessage(),"/projectManagement/voteForProject/project/"+projectId),HttpStatus.CONFLICT);
            }
        }
        projectRepository.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
