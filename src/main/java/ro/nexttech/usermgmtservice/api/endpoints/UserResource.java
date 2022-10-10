package ro.nexttech.usermgmtservice.api.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.nexttech.usermgmtservice.api.common.UserExceptionBody;
import ro.nexttech.usermgmtservice.api.common.UserNotFoundException;
import ro.nexttech.usermgmtservice.api.models.UserDTO;
import ro.nexttech.usermgmtservice.api.models.UserRequestModel;
import ro.nexttech.usermgmtservice.services.UserService;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Slf4j
@Component
@Path(ResourcesPath.USERS)
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Tag(name = "resources:UserResource")
@AllArgsConstructor
public class UserResource {
    private final UserService userService;

    @GET
    @Operation(description = "Retrieve list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDTO.class))),
    })
    public Response getUsers() {
        log.info("Getting all users");

        return Response.ok().entity(userService.getUsers()).build();
    }

    @GET
    @Path("/{userId}")
    @Operation(description = "Retrieve user with given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "OK", content = @Content(schema = @Schema(implementation = UserExceptionBody.class))),
    })
    public Response getUserById(@PathParam("userId") Integer userId) {
        log.info("Getting user with id {}", userId);

        try {
            return Response.ok().entity(userService.getUserById(userId)).build();
        } catch (UserNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    new UserExceptionBody(exception.getMessage(), new Timestamp(System.currentTimeMillis()))).build();
        }
    }

    @GET
    @Path("/details")
    @Operation(description = "Retrieve user with given properties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "OK", content = @Content(schema = @Schema(implementation = UserExceptionBody.class))),
    })
    public Response getUser(@BeanParam UserRequestModel userRequest) {
        log.info("Getting user with details {}", userRequest);

        try {
            return Response.ok().entity(userService.getUser(userRequest)).build();
        } catch (UserNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    new UserExceptionBody(exception.getMessage(), new Timestamp(System.currentTimeMillis()))).build();
        }
    }

    @POST
    @Operation(description = "Saves a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDTO.class))),
    })
    public Response saveUser(UserDTO user) {
        log.info("Saving user {}", user);

        return Response.ok().entity(userService.saveUser(user)).build();
    }

    @PUT
    @Path("/{userId}")
    @Operation(description = "Updates a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "OK", content = @Content(schema = @Schema(implementation = UserExceptionBody.class)))
    })
    public Response updateUser(@PathParam("userId") Integer userId, UserDTO user) {
        log.info("Updating user {}", userId);

        try {
            return Response.ok().entity(userService.updateUser(userId, user)).build();
        } catch (UserNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    new UserExceptionBody(exception.getMessage(), new Timestamp(System.currentTimeMillis()))).build();
        }
    }

    @DELETE
    @Path("/{userId}")
    @Operation(description = "Deletes a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDTO.class))),
    })
    public Response deleteUserById(@PathParam("userId") Integer userId) {
        log.info("Deleting user {}", userId);

        try {
            userService.deleteUserById(userId);
            return Response.ok().entity("User deleted successfully!").build();
        } catch (UserNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    new UserExceptionBody(exception.getMessage(), new Timestamp(System.currentTimeMillis()))).build();
        }
    }
}
