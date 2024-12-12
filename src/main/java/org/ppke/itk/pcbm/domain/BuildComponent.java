package org.ppke.itk.pcbm.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "builds_components")
public class BuildComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer buildComponentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build_id", nullable = false)
    private Build build;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    private Component component;

}
