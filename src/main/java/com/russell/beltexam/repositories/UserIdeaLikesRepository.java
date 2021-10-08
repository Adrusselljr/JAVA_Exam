package com.russell.beltexam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.russell.beltexam.models.UserIdeaLikes;

@Repository
public interface UserIdeaLikesRepository extends CrudRepository<UserIdeaLikes, Long> {
	
	

}
