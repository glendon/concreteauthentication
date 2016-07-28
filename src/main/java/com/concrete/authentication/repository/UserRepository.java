package com.concrete.authentication.repository;

import org.springframework.data.repository.CrudRepository;

import com.concrete.authentication.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
}
