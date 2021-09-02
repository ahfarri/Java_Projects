package com.farris.lookify.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farris.lookify.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
	
	List<Song> findAllByOrderByRatingDesc();
	
	List<Song> findByArtistContaining(String artist);
}
