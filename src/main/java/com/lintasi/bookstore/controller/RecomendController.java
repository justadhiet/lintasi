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

import com.lintasi.bookstore.model.Recomended;
import com.lintasi.bookstore.payload.response.MessageResponse;
import com.lintasi.bookstore.service.RecomendedService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/recomend")
public class RecomendController {
	
	@Autowired
	RecomendedService recomendedService;
	
	@GetMapping("/{id}")
	public List<Recomended> getRecomendByUser(@PathVariable Integer id){
		return recomendedService.getRecomendedByUser(id);
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<?> add(@RequestBody Recomended recomend) {
		try {
			recomendedService.saveRecomended(recomend);
			return ResponseEntity.ok(new MessageResponse("Recomend inserted successfully!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Recomend can't be update! e:" + e.getMessage()));
		}
		
	}
	
	@DeleteMapping("/{bookid}/{userid}")
	public ResponseEntity<?> delete(@PathVariable Integer bookid, @PathVariable Integer userid) {
		try {
			recomendedService.deleteRecomended(bookid, userid);
			return ResponseEntity.ok(new MessageResponse("Recomend delete successfully!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Recomend can't be deleted! e:" + e.getMessage()));
		}
	}

}
