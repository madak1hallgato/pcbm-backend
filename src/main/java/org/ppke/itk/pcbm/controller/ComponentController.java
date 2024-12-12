package org.ppke.itk.pcbm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.pcbm.controller.dto.ComponentDto;
import org.ppke.itk.pcbm.domain.Component;
import org.ppke.itk.pcbm.repository.ComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Tag(name = "Component")
@RestController
@RequestMapping("/components")
@RequiredArgsConstructor
public class ComponentController {

    private final ComponentRepository componentRepository;

    @Operation(summary = "Retrieve all components", description = "Fetches a list of all components in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of components.")
    @GetMapping()
    public ResponseEntity<List<ComponentDto>> getComponents() {
        List<ComponentDto> components = this.componentRepository.findAll()
                .stream()
                .map(ComponentDto::fromComponent)
                .sorted(Comparator.comparing(ComponentDto::getName))
                .toList();
        return ResponseEntity.ok(components);
    }

    @Operation(summary = "Retrieve components by category", description = "Fetches components belonging to a specific category based on category ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved components for the specified category.")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ComponentDto>> getComponentsByCategoryId(@PathVariable Integer categoryId) {
        List<ComponentDto> components = this.componentRepository.findByCategory_CategoryId(categoryId)
                .stream()
                .map(ComponentDto::fromComponent)
                .sorted(Comparator.comparing(ComponentDto::getName))
                .toList();
        return ResponseEntity.ok(components);
    }

    @Operation(summary = "Retrieve a specific component", description = "Fetches the details of a component by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the component."),
            @ApiResponse(responseCode = "404", description = "Component not found."),
    })
    @GetMapping("/id/{componentId}")
    public ResponseEntity<ComponentDto> getComponentByComponentId(@PathVariable Integer componentId) {
        Optional<Component> component = Optional.ofNullable(this.componentRepository.findByComponentId(componentId));
        return component
                .map(c -> ResponseEntity.ok(ComponentDto.fromComponent(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Retrieve a specific component", description = "Fetches the details of a component by its unique Name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the component."),
            @ApiResponse(responseCode = "404", description = "Component not found."),
    })
    @GetMapping("/name/{componentName}")
    public ResponseEntity<ComponentDto> getComponentByName(@PathVariable String componentName) {
        Optional<Component> component = this.componentRepository.findByName(componentName);
        return component
                .map(c -> ResponseEntity.ok(ComponentDto.fromComponent(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create or update a component", description = "Creates a new component or updates an existing one if it already exists.")
    @ApiResponse(responseCode = "201", description = "Component successfully created or updated.")
    @PostMapping()
    public ResponseEntity<ComponentDto> saveComponent(@RequestBody Component component) {
        Component savedComponent = this.componentRepository.save(component);
        return ResponseEntity.status(HttpStatus.CREATED).body(ComponentDto.fromComponent(savedComponent));
    }

    @Operation(summary = "Delete a component", description = "Deletes the component identified by the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Component successfully deleted."),
            @ApiResponse(responseCode = "404", description = "Component not found."),
    })
    @DeleteMapping("/id/{componentId}")
    public ResponseEntity<Void> deleteComponent(@PathVariable Integer componentId) {
        if (this.componentRepository.existsById(componentId)) {
            this.componentRepository.deleteByComponentId(componentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
