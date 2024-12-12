package org.ppke.itk.pcbm.repository;

import jakarta.transaction.Transactional;
import org.ppke.itk.pcbm.domain.Build;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuildRepository extends JpaRepository<Build, Integer> {

//    List<Build> findAll(); (DEFAULT)

    @Query("SELECT b FROM Build b WHERE b.user.userId = ?1")
    List<Build> findByUser_UserId(Integer userId);

    @Query("SELECT b FROM Build b WHERE b.buildId = ?1")
    Build findByBuildId(Integer buildId);

//    <S extends Build> S save(S entity); (DEFAULT)

    @Transactional
    @Modifying
    @Query("DELETE FROM Build b WHERE b.buildId = ?1")
    void deleteByBuildId(Integer buildId);

}
