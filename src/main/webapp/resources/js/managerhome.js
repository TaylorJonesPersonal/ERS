window.onload = function() {
	checkSession();
	getFirstName();
}

async function checkSession() {
	try{
		let request = await fetch('http://localhost:8080/ERS/api/checksession');
		let response = await request.text();
		console.log(response);
		if(response == "0"){
			location.href = "../html/signup.html"
		}
	}catch(e){
		console.log(e);
	}
	return true;
}

async function logout() {
	try{
		let request = await fetch('http://localhost:8080/ERS/api/logout');
		let response = await request.text();
		console.log(response);
	} catch(e){
		console.log(e);
	}
}

var controlFlag = 0;
var dropdown = document.getElementById("sidebarButton").addEventListener('click', menuControl);


function menuControl() {
	console.log(controlFlag);
	if (controlFlag == 0) {
		addMenu();
		controlFlag += 1;
	} else if (controlFlag == 1) {
		foldMenu();
		controlFlag -= 1;
	}
}

function addMenu() {
	var newNode = document.createElement("div");
	newNode.innerHTML = "<p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/mpendingrequests.html'>View/Approve Pending Requests</a></p><p class = 'menuItems'><a href='http://localhost:8080/ERS/resources/html/mresolvedrequests.html'>View All Resolved Requests</a></p><p class = 'menuItems'><a href='http://localhost:8080/ERS/resources/html/viewspecific.html'>View Specific Employee Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/mviewallemployees.html'>View Employee Information</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/maccountinformation.html'>View/Update My Account Information</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/signup.html' onClick='logout()'>Logout</p>";
	document.getElementById("sidebarContainer").appendChild(newNode);
};

function foldMenu() {
	var container = document.getElementById("sidebarContainer");
	container.removeChild(container.lastChild);
};

async function getFirstName(e) {
	try {
		let response = await fetch('http://localhost:8080/ERS/api/sessioninfo');
		let sessionInfo = await response.text();
		var newNode = document.createElement("span");
		newNode.innerHTML = "<u>" + sessionInfo + "</u>";
		document.getElementById("blank").appendChild(newNode);
	} catch (e) {
		console.log(e);
		return;
	}
}
