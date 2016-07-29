package com.concrete.authentication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.concrete.authentication.domain.User;

public interface UserRepository extends CrudRepository<User, String> {

	List<User> findByEmailAndPassword(String email, String password);

	List<User>  findByEmail(String email);
}
