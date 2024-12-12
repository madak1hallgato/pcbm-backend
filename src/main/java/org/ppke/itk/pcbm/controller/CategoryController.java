package org.ppke.itk.pcbm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ppke.itk.pcbm.domain.Category;
import org.ppke.itk.pcbm.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Category")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Operation(summary = "Retrieve all category", description = "Fetches a list of all categories in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of category.")
    @GetMapping()
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Retrieve a specific category", description = "Fetches the details of a category by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the category."),
            @ApiResponse(responseCode = "404", description = "Category not found."),
    })
    @GetMapping("/id/{categoryId}")
    public ResponseEntity<Category> getCategoryByCategoryId(@PathVariable Integer categoryId) {
        Optional<Category> category = Optional.ofNullable(this.categoryRepository.findByCategoryId(categoryId));
        return category
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
