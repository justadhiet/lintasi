package com.lintasi.bookstore.service;

import javax.transaction.Transactional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.model.PricingId;
import com.lintasi.bookstore.model.Pricing;
import com.lintasi.bookstore.repository.PricingRepository;

@Service
@Transactional
public class PricingService {

	@Autowired
	private PricingRepository pricingRepository;
	
	public List<Pricing> listAllPrice(){
		return pricingRepository.findAll();
	}
	
	public List<Pricing> getPricingByBook(Integer bookid){
		return pricingRepository.findByBookId(bookid);
	}
	
	public void savePricing(Pricing price) {
		pricingRepository.save(price);
	}
	
	public Pricing getPricing(int bookid, long price) {
		 return pricingRepository.findById(new PricingId(bookid,price)).get();
	}
	
	public void deletePricing(int bookid, long price) {
		pricingRepository.deleteById(new PricingId(bookid,price));
	}
	
	
}
