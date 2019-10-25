/*var bodyParser = require('body-parser');                                                                     
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended : true}));

var x;
var y;

app.post('/receive_location', function(req,res){
	  console.log("email :", req.body.start);
	  x = 안드로이드 X 값
	  y = 안드로이드 y 값
});*/

var mycanvas = document.getElementById("canvas");
var ctx = mycanvas.getContext("2d");

ctx.beginPath();
//ctx.arc(100, 160, 7, 0, 2 * Math.PI); // 10, 16
//ctx.arc(300, 160, 7, 0, 2 * Math.PI); // 30, 16
//ctx.arc(500, 160, 7, 0, 2 * Math.PI); // 50, 16
ctx.arc(700, 160, 7, 0, 2 * Math.PI); // 70, 16
ctx.fillStyle = "red";
ctx.fill();
ctx.stroke();