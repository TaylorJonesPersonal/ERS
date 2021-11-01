window.onload = function() {
	checkSession();
	getReimbursement();
}

var submitFormControlFlag = 0;
var controlFlag = 0;
var dropdown = document.getElementById("sidebarButton").addEventListener('click', menuControl);
var reim = document.getElementById("requestSelector").addEventListener('change', getReimbursement);

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
	newNode.innerHTML = "<p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/reimbursementrequest.html'>Submit Reimbursement Request</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/reimbursementview.html'>View Reimbursement Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/accountinformation.html'>View Account Information</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/signup.html' onClick = 'logout()'>Logout</a></p>";
	document.getElementById("sidebarContainer").appendChild(newNode);
};

function foldMenu() {
	var container = document.getElementById("sidebarContainer");
	container.removeChild(container.lastChild);
};



async function getReimbursement() { 
	if (document.getElementById("requestSelector").value == "pending") {
		var table = document.getElementById("tableContainer");
		while(table.firstChild){
		table.removeChild(table.firstChild);
		}
		try {
			let request = await fetch('http://localhost:8080/ERS/api/getpending');
			let response = await request.json();
			for (let i = 0; i < response.length; i++) {
				let counter = i + 1;
				table = document.getElementById("tableContainer");
				var newNode = document.createElement("table");
				newNode.style.borderColor= "#2D87D4";
				newNode.style.width = "500px";
				newNode.innerHTML = "<tr><th colspan=2 style = 'color:#2D87D4'>Reimbursement Request # " + counter + " </th><tr><td>Submitted Date: </td><td> " + response[i].submittedDate + " </td></tr><tr><td>Amount: </td><td> $" + response[i].amount + " </td></tr><tr><td>Description: </td><td> " + response[i].description + " </td></tr><tr><td>Status: </td><td> " + response[i].status + " </td></tr>";
				table.appendChild(newNode);
			}
		} catch (e) {
			console.log(e);
		}
	} else if (document.getElementById("requestSelector").value == "approved") {
		var table = document.getElementById("tableContainer");
		while(table.firstChild){
		table.removeChild(table.firstChild);
		}
		try {
			let request = await fetch('http://localhost:8080/ERS/api/getapproved');
			let response = await request.json();
			for (let i = 0; i < response.length; i++) {
				let counter = i + 1;
				table = document.getElementById("tableContainer");
				var newNode = document.createElement("table");
				newNode.style.borderColor= "#2D87D4";
				newNode.style.width = "500px";
				newNode.innerHTML = "<tr><th colspan=2>Reimbursement Request # " + counter + " </th><tr><td>Submitted Date: </td><td> " + response[i].submittedDate + " </td></tr><tr><td>Amount: </td><td> $" + response[i].amount + " </td></tr><tr><td>Description: </td><td> " + response[i].description + " </td></tr><tr><td>Status: </td><td> " + response[i].status + " </td></tr>";
				table.appendChild(newNode);
			}
		} catch (e) {
			console.log(e);
		}
	} else if (document.getElementById("requestSelector").value = "denied") {
		var table = document.getElementById("tableContainer");
		while(table.firstChild){
		table.removeChild(table.firstChild);
		}
		try {
			let request = await fetch('http://localhost:8080/ERS/api/getdenied');
			let response = await request.json();
			for (let i = 0; i < response.length; i++) {
				let counter = i + 1;
				table = document.getElementById("tableContainer");
				var newNode = document.createElement("table");
				newNode.style.borderColor= "#2D87D4";
				newNode.style.width = "500px";
				newNode.innerHTML = "<tr><th colspan=2>Reimbursement Request # " + counter + " </th><tr><td>Submitted Date: </td><td> " + response[i].submittedDate + " </td></tr><tr><td>Amount: </td><td> $" + response[i].amount + " </td></tr><tr><td>Description: </td><td> " + response[i].description + " </td></tr><tr><td>Status: </td><td> " + response[i].status + " </td></tr>";
				table.appendChild(newNode);
			}
		} catch (e) {
			console.log(e);
		}
	} else{
		console.log("input wasn't recongnized.");
	}
}