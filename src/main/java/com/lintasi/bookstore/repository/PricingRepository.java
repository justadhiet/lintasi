package com.lintasi.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lintasi.bookstore.model.PricingId;
import com.lintasi.bookstore.model.Pricing;

public interface PricingRepository extends JpaRepository<Pricing, PricingId>{
	
	List<Pricing> findByBookId(Integer bookid);

}
