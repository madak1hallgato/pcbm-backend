package org.ppke.itk.pcbm.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ppke.itk.pcbm.domain.Build;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildDto {

    private Integer buildId;
    private String name;
    private String userUsername;

    public static BuildDto fromBuild(Build build) {
        return new BuildDto(
                build.getBuildId(),
                build.getName(),
                build.getUser().getUsername()
        );
    }

}
