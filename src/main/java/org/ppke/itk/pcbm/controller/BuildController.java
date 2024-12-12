package org.ppke.itk.pcbm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.pcbm.controller.dto.BuildDto;
import org.ppke.itk.pcbm.domain.Build;
import org.ppke.itk.pcbm.repository.BuildRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Tag(name = "Build")
@RestController
@RequestMapping("/builds")
@RequiredArgsConstructor
public class BuildController {

    private final BuildRepository buildRepository;

    @Operation(summary = "Retrieve all builds", description = "Fetches a list of all builds in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of builds.")
    @GetMapping()
    public ResponseEntity<List<BuildDto>> getBuilds() {
        List<BuildDto> builds = this.buildRepository.findAll()
                .stream()
                .map(BuildDto::fromBuild)
                .sorted(Comparator.comparing(BuildDto::getName))
                .toList();
        return ResponseEntity.ok(builds);
    }

    @Operation(summary = "Retrieve builds by user", description = "Fetches builds belonging to a specific user based on user ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved builds for the specified user.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BuildDto>> getBuildsByUserId(@PathVariable Integer userId) {
        List<BuildDto> builds = this.buildRepository.findByUser_UserId(userId)
                .stream()
                .map(BuildDto::fromBuild)
                .toList();
        return ResponseEntity.ok(builds);
    }

    @Operation(summary = "Retrieve a specific build", description = "Fetches the details of a build by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the build."),
            @ApiResponse(responseCode = "404", description = "Build not found."),
    })
    @GetMapping("/id/{buildId}")
    public ResponseEntity<BuildDto> getBuildByBuildId(@PathVariable Integer buildId) {
        Optional<Build> build = Optional.ofNullable(this.buildRepository.findByBuildId(buildId));
        return build
                .map(b -> ResponseEntity.ok(BuildDto.fromBuild(b)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create or update a build", description = "Creates a new build or updates an existing one if it already exists.")
    @ApiResponse(responseCode = "201", description = "Build successfully created or updated.")
    @PostMapping()
    public ResponseEntity<BuildDto> saveBuild(@RequestBody Build build) {
        Build savedBuild = this.buildRepository.save(build);
        return ResponseEntity.status(HttpStatus.CREATED).body(BuildDto.fromBuild(savedBuild));
    }

    @Operation(summary = "Delete a build", description = "Deletes the build identified by the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Build successfully deleted."),
            @ApiResponse(responseCode = "404", description = "Build not found."),
    })
    @DeleteMapping("/id/{buildId}")
    public ResponseEntity<Void> deleteBuild(@PathVariable Integer buildId) {
        if (this.buildRepository.existsById(buildId)) {
            this.buildRepository.deleteByBuildId(buildId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
