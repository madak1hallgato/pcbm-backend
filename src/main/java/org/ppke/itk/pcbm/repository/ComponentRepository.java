package org.ppke.itk.pcbm.repository;

import jakarta.transaction.Transactional;
import org.ppke.itk.pcbm.domain.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

//    List<Component> findAll(); (DEFAULT)

    @Query("SELECT c FROM Component c WHERE c.category.categoryId = ?1")
    List<Component> findByCategory_CategoryId(Integer categoryId);

    @Query("SELECT c FROM Component c WHERE c.componentId = ?1")
    Component findByComponentId(Integer componentId);

    @Query("SELECT c FROM Component c WHERE c.name = ?1")
    Optional<Component> findByName(String name);

//    <S extends Component> S save(S entity); (DEFAULT)

    @Transactional
    @Modifying
    @Query("DELETE FROM Component c WHERE c.componentId = ?1")
    void deleteByComponentId(Integer componentId);

}
