package com.farris.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farris.lookify.models.Song;
import com.farris.lookify.services.SongService;

@Controller
public class HomeController {
	
	private final SongService songService;

	public HomeController(SongService songService) {
		this.songService = songService;
	}
	
	@GetMapping("/")
		public String index() {
			return "index.jsp";
	}
	
	@GetMapping("/dashboard")
	public String home(Model model) {
		List<Song> songs =songService.allSongs();
		model.addAttribute("songs", songs);
		return "dashboard.jsp";
	}
	
	@GetMapping("/songs/new")
	public String songNew(@ModelAttribute ("song") Song song) {
		return "newSong.jsp";
	}
	
	@PostMapping("/create/new")
	public String songCreate(@Valid @ModelAttribute ("song") Song song, BindingResult result) {
		if (result.hasErrors()) {
            return "redirect:/songs/new";
        } else {
        songService.createSong(song);
		return "redirect:/songs/new";}
	}
	
	@GetMapping("/search/topTen")
	public String topSongs(Model model) {
		List<Song> top = songService.allSongsRating();
		model.addAttribute("topten", top);
		return "topten.jsp";
	}
	
	@GetMapping("/search/{artist}")
	public String searchPage(@RequestParam("artist") String artist, Model model) {
		List<Song> search = songService.allSongsArtist(artist);
		model.addAttribute("artist", artist);
		model.addAttribute("search", search);
		return "search.jsp";
	}
		
	@PostMapping("/search")
	public String search(@RequestParam("artist") String artist, Model model) {
		List<Song> search = songService.allSongsArtist(artist);
		model.addAttribute("artist", artist);
		model.addAttribute("search", search);
		return "search.jsp";
	}
	
	@GetMapping("/songs/{id}")
	public String viewSong(@PathVariable Long id, Model model) {
		Song song = songService.findSongbyId(id);
		model.addAttribute("song", song);
		return "showsong.jsp";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteSong(@PathVariable Long id) {
		Song song = songService.findSongbyId(id);
		songService.deleteSong(song);
		return "redirect:/dashboard";
	}
}
