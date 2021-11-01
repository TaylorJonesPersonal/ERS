window.onload = function() {
	checkSession();
}

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

var controlFlag = 0;
var dropdown = document.getElementById("sidebarButton").addEventListener('click', menuControl);
var getSpecific = document.getElementById("employeeSearchButton").addEventListener('click', getPendingSpecificUser)


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
	newNode.innerHTML = "<p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/mpendingrequests.html'>View/Approve Pending Requests</a></p><p class = 'menuItems'><a href='http://localhost:8080/ERS/resources/html/mresolvedrequests.html'>View All Resolved Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/mviewallemployees.html'>View Employee Information</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/maccountinformation.html'>View/Update My Account Information</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/signup.html' onClick='logout()'>Logout</p>";
	document.getElementById("sidebarContainer").appendChild(newNode);
};

function foldMenu() {
	var container = document.getElementById("sidebarContainer");
	container.removeChild(container.lastChild);
};

async function getPendingSpecificUser() {

	let username = document.getElementById("employeeSearchUsername").value;

	let u = {
		username
	}
	console.log(u);
	
	try{
		let request = await fetch('http://localhost:8080/ERS/api/mgetspecific', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
				body: JSON.stringify(u)
		});
		let response = await request.json();
		console.log(response);


		for (let i = 0; i < response.length; i++) {
			let counter = i + 1;
			table = document.getElementById("tableContainer");
			var newNode = document.createElement("table");
			newNode.style.borderColor = "#2D87D4";
			newNode.style.width = "500px";
			newNode.innerHTML = "<tr><th colspan=2 style = 'color:#2D87D4'>Reimbursement Request # " + counter + " </th></tr><tr><td>Name of requester: </td><td> " + response[i].authorFirstName + " " + response[i].authorLastName + "</td><tr><td>Submitted Date: </td><td ID = 'date" + [i] + "'> " + response[i].submittedDate + " </td></tr><tr><td>Amount: </td><td> $" + response[i].amount + " </td></tr><tr><td>Description: </td><td> " + response[i].description + " </td></tr><tr><td>Status: </td><td> " + response[i].status + " </td><tr><td>Actions: </td><td><button class='approve' onClick = 'approve(" + i + ")'>Approve</button><button class = 'deny' onClick='deny(" + i + ")'>Deny</button></td></tr>";
			table.appendChild(newNode);
		}
		
	} catch (e) {
		console.log(e);
	}

}

async function approve(i) {
	let uniqueDate = document.getElementById("date" + i + "").innerText;

	let date = {
		uniqueDate
	}
	try{
	let request = await fetch('http://localhost:8080/ERS/api/approvereim', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(date)
	});
	let response = await request.text();
	location.href = "../html/viewspecific.html";
	}catch(e){
		console.log(e);
	}
}

async function deny(i){
	console.log("running deny js function.");
	let uniqueDate = document.getElementById("date" + i + "").innerText;
	
	let date = {
		uniqueDate
	}
	
	try{
		let request = await fetch('http://localhost:8080/ERS/api/denyreim', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
				},
			body: JSON.stringify(date)
		});
		let response = await request.text();
		location.href = "../html/viewspecific.html"
	}catch(e){
		console.log(e);
	}
}