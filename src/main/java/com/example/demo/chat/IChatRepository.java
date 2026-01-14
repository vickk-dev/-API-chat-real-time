package com.example.demo.chat;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IChatRepository extends MongoRepository<Chat, String>  {

    Optional<Chat> findByDisplayName(String displayName, Limit limit);
	
	Optional<Chat> findByParticipants(List<String> participants);
	
	Optional<Chat> findByCreateAt(Instant createData);


}
