package com.example.demo.user;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {

	Optional<User> findByEmail (String Email);
	
	Optional<User> findByName (String Nome);

    Optional<User> findByPhoneNumber(String phoneNumber);


}
