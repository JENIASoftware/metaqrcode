// GOBAL variables
var SERVER = 'www.metaqrcode.com'; // server to connect to
var PORT = 443; // port to connect to
var email = "aaa@jenia.it"; // email of the user 
var password = "aaaaaa"; // password of the user
var nickName = "nickName1";  // nickname of the user
var sessionToken = null; // after logging in you will have back a sessionToken to use in subsequent requests
var registrationPrepareSuccess=false; // global registration prepare status
var registrationConfirmSuccess=false; // global registration confirm status
var loginSuccess=false; // global login status

// please note that this automatic registration procedure only works if server is started with 
// metaqrcode.server.core.fakeRegistrationConfirmationCode=true

// check if registration process is already done
// if not, lets do it
// in every case do the login
function verifyRegistrationAndLogin() {
	if (!checkRegistrationExists()) {
		registrationPrepare();
		if (registrationPrepareSuccess) {
			registrationConfirm();
		}
	}
	login();
}
// check if registration process is already done
// if not, lets do it
// does not do the login!
function verifyRegistration() {
	if (!checkRegistrationExists()) {
		registrationPrepare();
		if (registrationPrepareSuccess) {
			registrationConfirm();
		}
	}
}
// check if the email is already registered
function checkRegistrationExists() {
	var requestRegistrationExists = new Object();
	requestRegistrationExists.email=email;
	var ret = false;
	$.ajax({
		  type: "POST",
		  url: (PORT==443?"https":"http")+"://"+SERVER+(PORT==443?"":":"+PORT)+"/api/rest/json/registration/exists",
		  data: JSON.stringify(requestRegistrationExists),
		  dataType: "json",
		  async: false,
		  contentType: "application/json; charset=utf-8",
		  error: function(jqXHR, textStatus, errorThrown) {
			dalert.alert('Generic http error on request : ' + jqXHR.status + ", returnCode = " + jqXHR.responseJSON.returnCode + ", reason = " + jqXHR.responseJSON.reason,'Error');
			ret = false;
		  },
		  success: function(data, textStatus, jqXHR) {
			if (data.returnCode >= 0) {
				ret = data.exists;
			} else {
				dalert.alert("returncode : " + data.returnCode + "; reason : " + data.reason,'Success');
				ret = false;
			}
		  } 
		});
	return ret;
}
// registration prepare.
// send user data to the server. The server will send a registration confirmation mail with a registrationConfirmationCode to use in registration confirm
// please note that email will be sent only if server is started with 
// metaqrcode.server.core.sendMail=true
function registrationPrepare() {
	var requestRegistrationPrepare = new Object();
	requestRegistrationPrepare.email=email;
	requestRegistrationPrepare.password=password;
	requestRegistrationPrepare.nickName=nickName;
	requestRegistrationPrepare.firstName="firstName";
	requestRegistrationPrepare.lastName="lastName";
	requestRegistrationPrepare.address="address";
	requestRegistrationPrepare.city="city";
	requestRegistrationPrepare.zipCode="zipCode";
	requestRegistrationPrepare.preferredLanguage="it";
	$.ajax({
		  type: "POST",
		  url: (PORT==443?"https":"http")+"://"+SERVER+(PORT==443?"":":"+PORT)+"/api/rest/json/registration/prepare",
		  data: JSON.stringify(requestRegistrationPrepare),
		  dataType: "json",
		  async: false,
		  contentType: "application/json; charset=utf-8",
		  error: function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.responseJSON.returnCode != -120045) {
				dalert.alert('Generic http error on request : ' + jqXHR.status + ", returnCode = " + jqXHR.responseJSON.returnCode + ", reason = " + jqXHR.responseJSON.reason,'Error');
			} else {
				registrationPrepareSuccess=true;
			}
		  },
		  success: function(data, textStatus, jqXHR) {
			if (data.returnCode >= 0) {
				registrationPrepareSuccess=true;
			} else {
				if (data.returnCode != -120045) {
					dalert.alert("returncode : " + data.returnCode + "; reason : " + data.reason,'Success');
				} else {
					registrationPrepareSuccess=true;
				}
			}
		  } 
		});
	return false;
}
// confirm the registration 
// please note that the registration code is setted to "000000" because we suppose that server is starter with 
// metaqrcode.server.core.fakeRegistrationConfirmationCode=true
function registrationConfirm() {
	var requestRegistrationConfirm = new Object();
	requestRegistrationConfirm.email=email;
	requestRegistrationConfirm.registrationConfirmationCode="000000";
	$.ajax({
		  type: "POST",
		  url: (PORT==443?"https":"http")+"://"+SERVER+(PORT==443?"":":"+PORT)+"/api/rest/json/registration/confirm",
		  data: JSON.stringify(requestRegistrationConfirm),
		  dataType: "json",
		  async: false,
		  contentType: "application/json; charset=utf-8",
		  error: function(jqXHR, textStatus, errorThrown) {
				dalert.alert('Generic http error on request : ' + jqXHR.status + ", returnCode = " + jqXHR.responseJSON.returnCode + ", reason = " + jqXHR.responseJSON.reason,'Error');
		  },
		  success: function(data, textStatus, jqXHR) {
			if (data.returnCode >= 0) {
				registrationConfirmSuccess=true;
			} else {
				dalert.alert("returncode : " + data.returnCode + "; reason : " + data.reason,'Success');
			}
		  } 
		});
	return false;
}
// do login to have back a valid sessionToken
function login() {
	var requestLogin = new Object();
	requestLogin.email=email;
	requestLogin.password=password;
	$.ajax({
		  type: "POST",
		  url: (PORT==443?"https":"http")+"://"+SERVER+(PORT==443?"":":"+PORT)+"/api/rest/json/login/login",
		  data: JSON.stringify(requestLogin),
		  dataType: "json",
		  async: false,
		  contentType: "application/json; charset=utf-8",
		  error: function(jqXHR, textStatus, errorThrown) {
				dalert.alert('Generic http error on request : ' + jqXHR.status + ", returnCode = " + jqXHR.responseJSON.returnCode + ", reason = " + jqXHR.responseJSON.reason,'Error');
		  },
		  success: function(data, textStatus, jqXHR) {
			if (data.returnCode >= 0) {
				sessionToken=data.sessionToken;
				loginSuccess=true;
			} else {
				dalert.alert("returncode : " + data.returnCode + "; reason : " + data.reason,'Success');
			}
		  } 
		});
	return false;
}

