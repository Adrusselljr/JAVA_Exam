package com.russell.beltexam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.russell.beltexam.models.Idea;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
	
	

}
