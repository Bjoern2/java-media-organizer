package com.github.bjoern2.photo.organizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.bjoern2.photo.organizer.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	@Query("FROM Photo WHERE hash1 = ?1 OR hash2 = ?1 OR hash3 = ?1 OR hash4 = ?1")
	List<Photo> findByHash(String hash);
	
}
