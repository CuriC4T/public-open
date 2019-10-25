var alertRedInput = "#8C1010";
var defaultInput = "rgba(10, 180, 180, 1)";

document.getElementById("signupForm").style.display = "none";

function userNameValidation(usernameInput) {
	var username = document.getElementById("nickname");
	var issueArr = [];
	if (/[-!@#$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test(usernameInput)) {
		issueArr.push("No special characters!");
	}
	if (issueArr.length > 0) {
		username.setCustomValidity(issueArr);
		username.style.borderColor = alertRedInput;
	} else {
		username.setCustomValidity("");
		username.style.borderColor = defaultInput;
	}
}

function passwordValidation(passwordInput) {
	var password = document.getElementById("password");
	var issueArr = [];
	if (!/^.{7,15}$/.test(passwordInput)) {
		issueArr.push("Password must be between 7-15 characters.");
	}
	if (!/\d/.test(passwordInput)) {
		issueArr.push("Must contain at least one number.");
	}
	if (!/[a-z]/.test(passwordInput)) {
		issueArr.push("Must contain a lowercase letter.");
	}
	if (!/[A-Z]/.test(passwordInput)) {
		issueArr.push("Must contain an uppercase letter.");
	}
	if (issueArr.length > 0) {
		password.setCustomValidity(issueArr.join("\n"));
		password.style.borderColor = alertRedInput;
	} else {
		password.setCustomValidity("");
		password.style.borderColor = defaultInput;
	}
}

function setToSignIn() {
	document.getElementById("signinForm").style.display = "block";
	document.getElementById("signupForm").style.display = "none";
	var signTitle = document.getElementById("signTitle");
	signTitle.innerHTML = "Sign In";
}
function setToSignUp() {
	document.getElementById("signinForm").style.display = "none";
	document.getElementById("signupForm").style.display = "block";
	var signTitle = document.getElementById("signTitle");
	signTitle.innerHTML = "Sign Up";
}