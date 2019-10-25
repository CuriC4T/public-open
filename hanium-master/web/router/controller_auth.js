/**
 * controller_auth.js
 */

var express = require('express');
var router = express.Router();

var bodyParser = require("body-parser");
var urlencodedParser = bodyParser.urlencoded({
	extended : false
});

/* 웹페이지 조작 */
module.exports = function(passport, connection) {

router.get('/', function(req, res) {

		res.redirect("/auth/login");
	});
	// login get 해오면
	router.get('/login', function(req, res) {
		console.log("login get");
		res.render('./login/login');
	});
	// login post에 대해서
	router.post('/login', passport.authenticate('local', {// 인증 방식 :local(사용자
															// 설정)
		failureRedirect : '/auth'// 인증 실패 할 시 login 페이지로
	}), function(req, res) {
		res.redirect('/index');
	});

	// signup get 해오면
	router.get('/signup', function(req, res) {
		res.redirect('/login');// signup에 직접적인 접근은 login으로 리다이렉트

	});

	// signup post에 대해서
	router.post("/signup", urlencodedParser, function(req, res) {
		var users = {
			"email" : req.body.email,
			"password" : req.body.password,
			"nickname" : req.body.nickname
		};
		
		connection.query('INSERT INTO admin_list SET ?', users, function(error,
				results, fields) {
			if (error) {
				console.log(error);
				res.redirect('/login');

			} else {
				res.redirect('/');
			}
		});

	});

	router.get('/logout', function(req, res) {
		req.logout();
		// req.session.destroy(function(err){
		// res.redirect('/');
		// });
		req.session.save(function() {
			res.redirect('/');
		});// 세션 상태를 저장하고 리다이렉트(세션이 없어진 상태)

	});
	// app.get('/temp', function(req, res) {
	// var c=req.flash('error');
	// console.log(c);
	// res.send(c);
	// });

	router.get('/profile', require('connect-ensure-login').ensureLoggedIn(),
			function(req, res) {
				res.render('./login/profile', {
					user : req.user
				});
			});
	return router;

};