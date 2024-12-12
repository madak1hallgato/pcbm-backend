package org.ppke.itk.pcbm.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ppke.itk.pcbm.domain.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentDto {

    private Integer componentId;
    private String name;
    private String categoryName;
    private Double price;

    public static ComponentDto fromComponent(Component component) {
        return new ComponentDto(
                component.getComponentId(),
                component.getName(),
                component.getCategory().getName(),
                component.getPrice()
        );
    }

}
