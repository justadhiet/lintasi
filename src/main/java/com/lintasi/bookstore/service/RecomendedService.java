package com.lintasi.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.model.Recomended;
import com.lintasi.bookstore.model.RecomendedId;
import com.lintasi.bookstore.repository.RecomendedRepository;

@Service
@Transactional
public class RecomendedService {
	
	@Autowired
	private RecomendedRepository recomendedRepository;
	
	public List<Recomended> listAllRecomended(){
		return recomendedRepository.findAll();
	}
	
	public List<Recomended> getRecomendedByBook(Integer bookid){
		return recomendedRepository.findByBookId(bookid);
	}
	
	public List<Recomended> getRecomendedByUser(Integer userid){
		return recomendedRepository.findByUserId(userid);
	}
	
	public void saveRecomended(Recomended recomended) {
		recomendedRepository.save(recomended);
	}
	
	public Recomended getRecomended(int bookid, int userId) {
		 return recomendedRepository.findById(new RecomendedId(bookid,userId)).get();
	}
	
	public void deleteRecomended(int bookid, int userId) {
		recomendedRepository.deleteById(new RecomendedId(bookid,userId));
	}
	
}
