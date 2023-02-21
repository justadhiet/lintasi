package com.lintasi.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintasi.bookstore.model.Pricing;
import com.lintasi.bookstore.payload.response.MessageResponse;
import com.lintasi.bookstore.service.PricingService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/price")
public class PricingController {

	@Autowired
	PricingService pricingService;
	
	@GetMapping("/{id}")
	public List<Pricing> getPriceByBookId(@PathVariable Integer id){
		return pricingService.getPricingByBook(id);
	}
	
	@GetMapping("/{id}/active")
	public Pricing getActivePrice(@PathVariable int id){
		List<Pricing> pricing = pricingService.getPricingActive(id);
		if(pricing.size()>0) {
			return pricing.get(0);
		}
		return new Pricing();
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<?> add(@RequestBody Pricing price) {
		List<Pricing> pricing = pricingService.getPricingActive(price.getBookId());
		if(pricing.size()>0) {
			pricingService.deactivatePricing(pricing.get(0));
		}
		pricingService.savePricing(price);
		return ResponseEntity.ok(new MessageResponse("Price inserted successfully!"));
	}
}
