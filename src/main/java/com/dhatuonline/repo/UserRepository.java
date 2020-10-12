package com.dhatuonline.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhatuonline.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);

	User findByUserNameAndEmail(String userName, String email);

}
