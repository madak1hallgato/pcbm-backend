package org.ppke.itk.pcbm.repository;

import jakarta.transaction.Transactional;
import org.ppke.itk.pcbm.domain.BuildComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BuildComponentRepository extends JpaRepository<BuildComponent, Integer> {

//    List<BuildComponent> findAll(); (DEFAULT)

    @Query("SELECT bc FROM BuildComponent bc WHERE bc.build.buildId = ?1")
    List<BuildComponent> findByBuild_BuildId(Integer buildId);

//    <S extends BuildComponent> S save(S entity); (DEFAULT)

    @Query("SELECT bc FROM BuildComponent bc WHERE bc.build.buildId = ?1 AND bc.component.category.name = ?2")
    Optional<BuildComponent> findByBuildIdAndCategory(Integer buildId, String componentCategory);

    @Transactional
    @Modifying
    @Query("DELETE FROM BuildComponent bc WHERE bc.buildComponentId = ?1")
    void deleteByBuildComponentId(Integer buildComponentId);

    @Transactional
    @Modifying
    @Query("DELETE FROM BuildComponent bc WHERE bc.build.buildId = ?1")
    void deleteByBuildId(Integer buildId);

}
