//package org.ppke.itk.pcbm.repository.mocks;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.ppke.itk.pcbm.domain.Component;
//import org.ppke.itk.pcbm.repository.ComponentRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Slf4j
//@Repository
//@RequiredArgsConstructor
//public class MockComponentRepository implements ComponentRepository {
//
//    private final static List<Component> COMPONENTS = Stream.of(
//            new Component( 0, "Intel Core i9-13900K", 0, 599.99),           // CPU
//            new Component( 1, "AMD Ryzen 9 7900X", 0, 449.99),              // CPU
//            new Component( 2, "NVIDIA RTX 4090", 1, 1599.99),               // GPU
//            new Component( 3, "AMD Radeon RX 7900 XTX", 1, 999.99),         // GPU
//            new Component( 4, "Corsair Vengeance DDR5 32GB", 2, 199.99),    // RAM
//            new Component( 5, "G.Skill Trident Z RGB 16GB", 2, 99.99)       // RAM
//    ).collect(Collectors.toList());
//
//    // = READ =========================================================================================================
//
//    @Override
//    public List<Component> findAll() {
//        log.info("Fetching all components.");
//        return COMPONENTS;
//    }
//
//    @Override
//    public List<Component> findAllByCategoryId(Integer categoryId) {
//        if (categoryId == null || categoryId < 0) {
//            throw new IllegalArgumentException("Category ID must be a non-negative integer.");
//        }
//        log.info("Fetching components in category ID {}", categoryId);
//        List<Component> filteredComponents = COMPONENTS.stream()
//                .filter(component -> component.getCategoryId().equals(categoryId))
//                .sorted(Comparator.comparing(Component::getName))
//                .toList();
//        if (filteredComponents.isEmpty()) {
//            log.warn("No components found for category ID {}", categoryId);
//        }
//        return filteredComponents;
//    }
//
//    @Override
//    public Component findByComponentId(Integer componentId) {
//        if (componentId == null || componentId < 0) {
//            throw new IllegalArgumentException("Component ID must be a non-negative integer.");
//        }
//        log.info("Fetching component with ID {}", componentId);
//        return COMPONENTS.stream()
//                .filter(component -> component.getComponentId().equals(componentId))
//                .findFirst()
//                .orElseThrow(() -> new NoSuchElementException("Component with ID " + componentId + " not found."));
//    }
//
//    // = CREATE & UPDATE ==============================================================================================
//
//    @Override
//    public Component saveComponent(Component component) {
//        Integer componentId = component.getComponentId();
//        String name = component.getName();
//        Integer categoryId = component.getCategoryId();
//        Double price = component.getPrice();
//        if (name == null || name.isBlank()) {
//            throw new IllegalArgumentException("Component name cannot be null or blank.");
//        }
//        if (categoryId == null || categoryId < 0) {
//            throw new IllegalArgumentException("Category ID must be a non-negative integer.");
//        }
//        if (price == null || price < 0) {
//            throw new IllegalArgumentException("Price must be a non-negative value.");
//        }
//        if (componentId != null) {
//            log.info("Updating component with ID {}: name={}, categoryId={}, price={}", componentId, name, categoryId, price);
//            Optional<Component> existingComponentOpt = COMPONENTS.stream()
//                    .filter(comp -> comp.getComponentId().equals(componentId))
//                    .findFirst();
//            if (existingComponentOpt.isPresent()) {
//                Component existingComponent = existingComponentOpt.get();
//                existingComponent.setName(name);
//                existingComponent.setCategoryId(categoryId);
//                existingComponent.setPrice(price);
//                return existingComponent;
//            } else {
//                throw new NoSuchElementException("Component with ID " + componentId + " not found.");
//            }
//        } else {
//            int newComponentId = COMPONENTS.stream()
//                    .mapToInt(Component::getComponentId)
//                    .max()
//                    .orElse(0) + 1;
//            log.info("Creating component with ID {}: name={}, categoryId={}, price={}", newComponentId, name, categoryId, price);
//            component.setComponentId(newComponentId);
//            COMPONENTS.add(component);
//            return component;
//        }
//    }
//
//    // = DELETE  ======================================================================================================
//
//    @Override
//    public void deleteComponent(Integer componentId) {
//        if (componentId == null || componentId < 0) {
//            throw new IllegalArgumentException("Component ID must be a non-negative integer.");
//        }
//        log.info("Attempting to delete component with ID {}", componentId);
//        boolean removed = COMPONENTS.removeIf(component -> component.getComponentId().equals(componentId));
//        if (removed) {
//            log.info("Deleted component with ID {}", componentId);
//        } else {
//            throw new NoSuchElementException("Component with ID " + componentId + " not found");
//        }
//    }
//
//}
