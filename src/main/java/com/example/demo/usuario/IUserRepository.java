package com.example.demo.usuario;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {

	Optional<User> findByEmail (String Email);
	
	Optional<User> findByName (String Nome);

    Optional<User> findByPhoneNumber(String phoneNumber);


}
