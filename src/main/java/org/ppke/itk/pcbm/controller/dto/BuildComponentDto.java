package org.ppke.itk.pcbm.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ppke.itk.pcbm.domain.BuildComponent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildComponentDto {

    private Integer buildComponentId;
    private Integer buildId;
    private String componentCategory;
    private String componentName;

    public static BuildComponentDto fromBuildComponents(BuildComponent buildComponent) {
        return new BuildComponentDto(
                buildComponent.getBuildComponentId(),
                buildComponent.getBuild().getBuildId(),
                buildComponent.getComponent().getCategory().getName(),
                buildComponent.getComponent().getName()
        );
    }

}
