module.exports = function(app,connection) {
	var passport = require('passport')
	,LocalStrategy = require('passport-local').Strategy;

	

	app.use(passport.initialize());
	app.use(passport.session());

	/* 로그인 성공시 사용자 정보를 Session에 저장한다 */
	passport.serializeUser(function(user, done) {
		console.log("serializeUser", user);

		done(null, user);
	});

	/* 인증 후, 페이지 접근시 마다 사용자 정보를 Session에서 읽어옴. */
	passport.deserializeUser(function(user, done) {
		console.log("deserializeUser", user);
		done(null, user);
	});

	/* 로그인 유저 판단 로직 */
	var isAuthenticated = function(req, res, next) {
		if (req.isAuthenticated()) {
			return next();
		}
		res.redirect('./login');
	};

	passport.use(new LocalStrategy({
		usernameField : 'email',
		passwordField : 'password',
		session : true,
		passReqToCallback : true,
		failureFlash : true
	// 인증을 수행하는 인증 함수로 HTTP request를 그대로 전달할지 여부를 결정한다
	}, function(req, email, password, done) {
		connection.query('select * from `admin_list` where `email` = ?', email,
				function(err, result) {
					if (err) {
						console.log('err :' + err);
						return done(null, false, {
							message : 'Incorrect login'
						});
					} else {
						if (result.length === 0) {
							console.log('해당 유저가 없습니다');
							return done(null, false, {
								message : 'Incorrect login'
							});
						} else {
							if (result[0].password !== password) {
								console.log(result[0].password);

								console.log('패스워드가 일치하지 않습니다');
								return done(null, false, {
									message : 'Incorrect login'
								});
							} else {
								console.log('로그인 성공');
								return done(null, {
									'email' : result[0].email,
									'nickname' : result[0].nickname,
									'idx' : result[0].idx
								});
							}
						}
					}
				});
	}));

	return passport;
};