package org.ppke.itk.pcbm.repository;

import org.ppke.itk.pcbm.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

//    List<Category> findAll(); (DEFAULT)

    @Query("select c from Category c where c.categoryId = ?1")
    Category findByCategoryId(Integer categoryId);

}
