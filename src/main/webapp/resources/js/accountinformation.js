window.onload = function() {
	getSessionInfo();
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
	newNode.innerHTML = "<p class='menuItems'><a href='#'>Submit Reimbursement Request</a></p><p class='menuItems'><a href='#'>View Pending Requests</a></p><p class='menuItems'><a href='#'>View Resolved Requests</a></p><p class='menuItems'><a href='#'>Logout</a></p>";
	document.getElementById("sidebarContainer").appendChild(newNode);
};

function foldMenu() {
	var container = document.getElementById("sidebarContainer");
	container.removeChild(container.lastChild);
};

async function getSessionInfo(e) {
	try {
		let response = await fetch('http://localhost:8080/ERS/api/largersessioninfo');
		let sessionInfo = await response.json();
		
		// employee name
		var newNode = document.createElement("span");
		newNode.innerHTML = sessionInfo.firstname + " " + sessionInfo.lastname;
		document.getElementById("employeeName").appendChild(newNode);
		
		// employee username
		newNode = document.createElement("span");
		newNode.innerHTML = sessionInfo.username;
		document.getElementById("employeeUsername").appendChild(newNode);
		
		// employee password
		newNode = document.createElement("span");
		console.log(sessionInfo.roleID);
		newNode.innerHTML = sessionInfo.password;
		document.getElementById("employeePassword").appendChild(newNode);
		
		// employee role
		newNode = document.createElement("span");
		if(sessionInfo.roleID == "0"){
			newNode.innerHTML = "Employee";
			document.getElementById("employeeRole").appendChild(newNode);
		} else if(sessionInfo.roleID == "1"){
			newNode.innerHTML = "Manager";
			document.getElementById("employeeRole").appendChild(newNode);
		}
	} catch (e) {
		console.log(e);
		return;
	}
}
