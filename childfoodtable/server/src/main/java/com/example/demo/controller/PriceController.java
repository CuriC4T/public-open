package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.Param;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.domain.PriceData;
import com.example.demo.domain.PriceDataList;

@RestController //@Controller + @ResponseBody 의 축약형
//@RequestMapping(value = "hello",method = RequestMethod.GET)
public class PriceController {
	private DatabaseConnection db;
	public PriceController(){
		db = new DatabaseConnection();

	}
	
	@GetMapping("/evewa/price") //@RequestMapping(method = RequestMethod.GET) 의 축약형

	public PriceDataList get(
			@RequestParam("startdate") String startdate,
			@RequestParam("enddate") String enddate,
			//value="enddate", required=false
			@RequestParam("type") String type
			//@RequestParam("point") Map<String, String> point, //같은 이름의 쿼리 여러개 받기
			//@RequestParam MultiValueMap<String, String> multiMap //한번에 받기
			)
	
	{
		 System.out.println("hello");

		
		return db.getPriceOfPeriod(type,startdate,enddate);
		
		
		
		//return new PriceData(id, startdate,enddate,type);
	}
	
	@PostMapping("hanium/post")
	public String post(
			@RequestBody String param
			) 
	{
		 String start = param;	
		 System.out.println(start);
		 return start;
		
	}
	
}
