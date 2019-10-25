/**
 * controller_dashboard.js
 */
// 파라미터 get, post 관리
// post는 body-parser 모듈 사용

var express = require('express');
var router = express.Router();
var bodyParser = require("body-parser");
var urlencodedParser = bodyParser.urlencoded({extended : false});
var mysql = require("mysql2");
var MailListener = require("mail-listener2-updated");
var nodemailer = require("nodemailer");
var emailDate = new Date();
var screenshot = require("screenshot-desktop")
var mail_data=[];
var idx=[];
var moment = require('moment');
require('moment-timezone'); 
moment.tz.setDefault("Asia/Seoul");

var conn_info = {
		host : "localhost",
		port : 3306,
		user : "root",
		password : "1234",
		database : "nanbada",
		multipleStatements: true
};


module.exports = function(app){
	
	/* 메인페이지 */
	router.get("/", function(req, res){
		if(req.user){
			res.redirect("/index");
		}else{
			res.redirect("/auth");
			// res.redirect("/index");

		}
	});
		
	router.get("/index", function(req, res){
		res.render("./dashboard/index.ejs", req);
		// console.log(req.user.email, req.user.idx, req.user.nickname);
	});
	

	
	router.post("/post", urlencodedParser, function(req, res){
		console.log('post position!');
		var user;
		
		var conn = mysql.createConnection(conn_info);
		conn.connect();
		// 디비 연결 conn_info 확인
		
		
		var selectsql = "select * from location_iot where name=?";
		var insertsql = "insert into customer_loc (name,area,time) values(?,?,?)";
		var updatesql = "update customers set location = ? where name =?";
		// 쿼리문 선언
	    
		
	    var position = req.body.position;
	    var input_data = [position];
	    // post 값 가져와서 파라미터로 쓰기

	    if((!req.body.user.length)||(!req.body.position.length)){
	    	//req가 없을 경우
			res.send("no req data error!");
			conn.end();
		}else{
			user=req.body.user;
			//req로부터 사용자 가져옴
			conn.query(selectsql, input_data, function (error, results, fields) {
			    if (error) {
			        console.log("sql error!");
			        res.send("sql error!");
			        conn.end();
			        
			    }else if(!results.length){
			        console.log("no data error!");
			        res.send("no data error!");
			        conn.end();
			    }else if (!results[0].area) {
			    	console.log("no row error!");
			    	res.send("no row error!");
			    	conn.end();
			    }
			    else{
			    	
			    	var area=results[0].area;
			    	var date = moment().format('YYYY-MM-DD HH:mm:ss');
					console.log("user: "+user+" area: "+area+" date: "+date);
					var input_data2=[user,area,date];
					console.log(date);

					conn.query(insertsql, input_data2, function (error, results, fields) {
						if (error) {
					        console.log(error);
					        res.send("sql error!!");
					        conn.end();
					    }else{
							console.log(user);
							var input_data3=[area,user];
							conn.query(updatesql, input_data3, function (error, results, fields) {
								if (error) {
							        console.log(error);
							        res.send("sql error!!!");
							        conn.end();
							    }else{
									console.log(results);
									res.send("success");
									conn.end();
							    }

							});

					    }

					});
					
					
					
			    }
			    
			    
			});
			
			
		}
	    //res.send("success");
	    
		//conn.end();
		
	
	});
	router.post("/post2", urlencodedParser, function(req, res){
		console.log("ddd");
		var insertsql ="insert into customer_loc (name,area,time) values(?,?,?)";

	});

	return router;
};
