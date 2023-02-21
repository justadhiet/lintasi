package com.lintasi.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.model.Genre;
import com.lintasi.bookstore.repository.GenreRepository;

@Service
@Transactional
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;
	
	public List<Genre> listAllGenre(){
		return genreRepository.findAll();
	}
	
	public void saveGenre(Genre genre) {
		genreRepository.save(genre);
	}
	
	public Genre getGenre(int genreId) {
		 return genreRepository.findById(genreId).get();
	}
	
	public void deleteGenre(int genreId) {
		genreRepository.deleteById(genreId);
	}
}
