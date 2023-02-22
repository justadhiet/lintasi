package com.lintasi.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintasi.bookstore.service.RecomendedService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/recomend")
public class RecomendController {
	
	@Autowired
	RecomendedService recomendedService;
	
	

}
