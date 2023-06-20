package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.Signup;

@Repository
public interface CustomerRepository extends JpaRepository<Signup, Long> {

	Optional<Signup> findByUsername(String username);

}
