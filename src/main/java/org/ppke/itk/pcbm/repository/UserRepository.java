package org.ppke.itk.pcbm.repository;

import org.ppke.itk.pcbm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    //    List<User> findAll(); (DEFAULT)

    @Query("select u from User u where u.userId = ?1")
    User findByUserId(Integer userId);

}
