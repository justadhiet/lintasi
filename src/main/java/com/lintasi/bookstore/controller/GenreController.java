package com.lintasi.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintasi.bookstore.model.Genre;
import com.lintasi.bookstore.payload.response.MessageResponse;
import com.lintasi.bookstore.service.GenreService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/genre")
public class GenreController {

	@Autowired
	GenreService genreService;
	
	@GetMapping("")
	public List<Genre> list(){
		return genreService.listAllGenre();
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<?> add(@RequestBody Genre genre) {
		
		try {
			genreService.saveGenre(genre);
			return ResponseEntity.ok(new MessageResponse("Genre inserted successfully!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Genre can't be inserted! e:" + e.getMessage()));
		}
	}
	
	@PostMapping("/edit/{id}")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<?> edit(@RequestBody Genre genre, @PathVariable Integer id) {
		
		try {
			Genre existGenre = genreService.getGenre(id);
			if(existGenre!=null) {
				existGenre.setGenreId(genre.getGenreId());
				existGenre.setName(genre.getName());
				existGenre.setPicture(genre.getPicture());
				existGenre.setStatus(genre.getStatus());
				genreService.saveGenre(existGenre);
				return ResponseEntity.ok(new MessageResponse("Genre Insert successfully "));
			}
			return ResponseEntity.badRequest().body(new MessageResponse("Genre Doesn't exist!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Genre can't be inserted! e:" + e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			genreService.deleteGenre(id);
			return ResponseEntity.ok(new MessageResponse("Genre deleted successfully!"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new MessageResponse("Error: genre can't be delete! e:" + e.getMessage()));
		}
	}
	
}
