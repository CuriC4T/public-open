package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.domain.FoodDataList;
import com.example.demo.domain.PriceDataList;

@RestController
public class FoodController {
	private DatabaseConnection db;
	public FoodController(){
		db = new DatabaseConnection();

	}
	
	@GetMapping("/evewa/food") //@RequestMapping(method = RequestMethod.GET) 의 축약형

	public FoodDataList get(
			@RequestParam("startdate") String startdate
			
			
			//@RequestParam("point") Map<String, String> point, //같은 이름의 쿼리 여러개 받기
			//@RequestParam MultiValueMap<String, String> multiMap //한번에 받기
			)
	
	{

		
		return db.getFoodOfPeriod(startdate);
		
		
		
		//return new PriceData(id, startdate,enddate,type);
	}
	
	
}
