package com.farris.lookify.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farris.lookify.models.Song;
import com.farris.lookify.repositories.SongRepository;

@Service
public class SongService {

	private final SongRepository songRepository;
    
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;}
	
	public List<Song> allSongs() {
        return (List<Song>) songRepository.findAll();
    }
    
    public List<Song> allSongsRating() {
        return songRepository.findAllByOrderByRatingDesc();
    }
    
    public List<Song> allSongsArtist(String artist) {
        return songRepository.findByArtistContaining(artist);
    }
    
    public Song createSong(Song s) {
        return songRepository.save(s);
    }
    
    public Song findSongbyId(Long id) {
        return this.songRepository.findById(id).orElse(null);
    }
    
    public void deleteSong(Song s) {
        songRepository.delete(s);
    }
}
