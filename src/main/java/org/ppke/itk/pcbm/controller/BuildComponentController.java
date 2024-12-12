package org.ppke.itk.pcbm.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.pcbm.controller.dto.BuildComponentDto;
import org.ppke.itk.pcbm.domain.Build;
import org.ppke.itk.pcbm.domain.BuildComponent;
import org.ppke.itk.pcbm.domain.Component;
import org.ppke.itk.pcbm.repository.BuildComponentRepository;
import org.ppke.itk.pcbm.repository.BuildRepository;
import org.ppke.itk.pcbm.repository.ComponentRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Tag(name = "BuildComponent")
@RestController
@RequestMapping("/builds_components")
@RequiredArgsConstructor
public class BuildComponentController {

    private final BuildComponentRepository buildComponentRepository;

    @Operation(summary = "Retrieve all build-component relationships", description = "Fetches all the build-component relationships, including details about builds and their associated components.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of build-component relationships.")
    @GetMapping()
    public ResponseEntity<List<BuildComponentDto>> getBuildsComponents() {
        List<BuildComponentDto> buildsComponents = this.buildComponentRepository.findAll()
                .stream()
                .map(BuildComponentDto::fromBuildComponents)
                .toList();
        return ResponseEntity.ok(buildsComponents);
    }

    @Operation(summary = "Retrieve components for a specific build", description = "Fetches the components associated with a specific build, identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of components for the specified build."),
            @ApiResponse(responseCode = "404", description = "Build not found.")
    })
    @GetMapping("/build/{buildId}")
    public ResponseEntity<List<BuildComponentDto>> getBuildsByUserId(@PathVariable Integer buildId) {
        List<BuildComponentDto> buildsComponents = this.buildComponentRepository.findByBuild_BuildId(buildId)
                .stream()
                .map(BuildComponentDto::fromBuildComponents)
                .toList();
        if (buildsComponents.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(buildsComponents);
        }
    }

    private final BuildRepository buildRepository;
    private final ComponentRepository componentRepository;

    @Operation(summary = "Created or update build-component",
            description = "If the combination of buildId and componentCategory exists, update the component. Otherwise, add a new build-component.")
    @ApiResponse(responseCode = "201", description = "Build-component successfully created or updated.")
    @PostMapping()
    public ResponseEntity<BuildComponentDto> addOrUpdateBuildComponent(@RequestBody BuildComponentDto buildComponentDto) {

        Optional<Build> buildOptional = buildRepository.findById(buildComponentDto.getBuildId());
        if (buildOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Build build = buildOptional.get();

        Optional<Component> componentOptional = componentRepository.findByName(buildComponentDto.getComponentName());
        if (componentOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Component component = componentOptional.get();

        Optional<BuildComponent> existingBuildComponent = buildComponentRepository.findByBuildIdAndCategory(
                buildComponentDto.getBuildId(),
                buildComponentDto.getComponentCategory()
        );

        BuildComponent buildComponent;
        if (existingBuildComponent.isPresent()) {
            buildComponent = existingBuildComponent.get();
            buildComponent.setComponent(component);
        } else {
            buildComponent = new BuildComponent();
            buildComponent.setBuild(build);
            buildComponent.setComponent(component);
        }

        BuildComponent savedBuildComponent = buildComponentRepository.save(buildComponent);
        return ResponseEntity.status(HttpStatus.CREATED).body(BuildComponentDto.fromBuildComponents(savedBuildComponent));
    }

    @Operation(summary = "Delete a build-component", description = "Deletes the  build-component identified by the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Build-component successfully deleted."),
            @ApiResponse(responseCode = "404", description = "Build-component not found."),
    })
    @DeleteMapping("/id/{buildComponentId}")
    public ResponseEntity<Void> deleteBuildComponent(@PathVariable Integer buildComponentId) {
        if (this.buildComponentRepository.existsById(buildComponentId)) {
            this.buildComponentRepository.deleteByBuildComponentId(buildComponentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete build-components", description = "Deletes build-components identified by the given Build ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Build-components successfully deleted."),
    })
    @DeleteMapping("/build/{buildId}")
    public ResponseEntity<Void> deleteBuildComponentsById(@PathVariable Integer buildId) {
        this.buildComponentRepository.deleteByBuildId(buildId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Export components of a build to PDF", description = "Generates and returns a PDF file listing all components associated with the specified build ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PDF successfully generated."),
            @ApiResponse(responseCode = "404", description = "No components found for the specified build ID."),
    })
    @GetMapping("/build/{buildId}/export")
    public ResponseEntity<byte[]> exportBuildComponentsToPdf(@PathVariable Integer buildId) {
        List<BuildComponentDto> buildComponents = buildComponentRepository.findByBuild_BuildId(buildId)
                .stream()
                .map(BuildComponentDto::fromBuildComponents)
                .toList();

        if (buildComponents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Components for Build ID: " + buildId, titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.addCell("Component Category");
            table.addCell("Component Name");
            table.addCell("Build Component");

            for (BuildComponentDto dto : buildComponents) {
                table.addCell(dto.getComponentCategory());
                table.addCell(dto.getComponentName());
                table.addCell(String.valueOf(dto.getComponentCategory()));
            }

            document.add(table);
            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=build_components_" + buildId + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
