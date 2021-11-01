window.onload = function() {
	checkSession();
	loadAllEmployees();
	}
	
var controlFlag = 0;
var dropdown = document.getElementById("sidebarButton").addEventListener('click', menuControl);

async function checkSession() {
	try {
		let request = await fetch('http://localhost:8080/ERS/api/checksession');
		let response = await request.text();
		console.log(response);
		if (response == "0") {
			location.href = "../html/signup.html"
		}
	} catch (e) {
		console.log(e);
	}
	return true;
}

async function logout() {
	try {
		let request = await fetch('http://localhost:8080/ERS/api/logout');
		let response = await request.text();
		console.log(response);
	} catch (e) {
		console.log(e);
	}
}


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
	newNode.innerHTML = "<p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/mpendingrequests.html'>View/Approve Pending Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/mresolvedrequests.html'>View all Resolved Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/viewspecific.html'>View Specific Employee Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/maccountinformation.html'>View/Update My Account Information</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/signup.html' onClick='logout()'>Logout</p>";
	document.getElementById("sidebarContainer").appendChild(newNode);
};

function foldMenu() {
	var container = document.getElementById("sidebarContainer");
	container.removeChild(container.lastChild);
};

async function loadAllEmployees() {
	console.log("running loadPending");
	var table = document.getElementById("tableContainer");
	while (table.firstChild) {
		table.removeChild(table.firstChild);
	}
	try {
		let request = await fetch('http://localhost:8080/ERS/api/mgetemployees');
		let response = await request.json();
		for (let i = 0; i < response.length; i++) {
			let counter = i + 1;
			table = document.getElementById("tableContainer");
			var newNode = document.createElement("table");
			newNode.style.borderColor = "#2D87D4";
			newNode.style.width = "500px";
			newNode.innerHTML = "<tr><th colspan=2 style = 'color:#2D87D4'>Employee # " + counter + " </th></tr><tr><td>Employee Name: </td><td> " + response[i].firstname + " " + response[i].lastname + "</td><tr><td>Email: </td><td> " + response[i].email + " </td></tr><tr><td>Username: </td><td> " + response[i].email + " </td></tr><tr><td>Role: </td><td> " + response[i].role + " </td></tr>";
			table.appendChild(newNode);
		}
	} catch (e) {
		console.log(e);
	}
};