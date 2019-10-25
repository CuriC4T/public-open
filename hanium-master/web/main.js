
var express = require("express");
var app = express();
var ejs = require("ejs");

var session = require("express-session");
var cookieParser = require("cookie-parser");
var bodyParser = require("body-parser");
var urlencodedParser = bodyParser.urlencoded({extended : false});

var mysql = require("mysql");

var passport = require('passport'); //passport module add
var LocalStrategy = require('passport-local').Strategy;
var flash = require('connect-flash');//사용자에게 알림을 보냄

//html 파일을 만들어 놓을 폴더 지정
app.set("views", __dirname + "/views");
//랜더링에 사용할 모듈 지정
app.set("view engine", "ejs");
//동적 웹 페이지
app.engine("ejs", ejs.renderFile);

app.use(require('body-parser').urlencoded({ extended: true }));
app.use(require('morgan')('combined'));//morgan은 로깅할 때 씀
//쿠키 사용
app.use(cookieParser());
//세션 사용
app.use(session({
	secret : "abcdefg",
	resave : false,
	saveUninitialized : false
}));

app.use(express.static("public"));
app.use(flash());

var mysql_dbc = require('./router/commons/db_con')();
var connection = mysql_dbc.init();
mysql_dbc.test_open(connection);

var passport = require('./libs/passport')(app,connection);

var controller_dashboard = require("./router/controller_dashboard")(app);
var controller_mail = require("./router/controller_mail");
var controller_auth = require("./router/controller_auth")(passport,connection);

app.use('/', controller_dashboard);
app.use('/auth', controller_auth);

app.use(function (req, res, next) {
	  res.status(404);
	  res.render("./error/404-page.ejs");
});

app.use(function (err, req, res, next) {
	  res.status(500);
	  res.render("./error/blank-page.ejs");
});

var server = app.listen(2000, function(){
	console.log("서버 가동");
});