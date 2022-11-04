package com.prueba_tecnica.backend.repository;


import com.prueba_tecnica.backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    final String usersQuery = "select * from fn_allusers()";
    @Query(value = usersQuery, nativeQuery = true)
    List<Map<String,?>> getAllUsers();

    @Query("Select u From users u Where u.login = ?1")
    Optional<User> findUserByLogin(String login);

    @Query("Select u From users u Where u.login = ?1 and u.id <> ?2")
    Optional<User> existsAnotherLogin(String login, Long id);

}
