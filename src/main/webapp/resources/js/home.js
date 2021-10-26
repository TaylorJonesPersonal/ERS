window.onload = function() {
	getFirstName();
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
	newNode.innerHTML = "<p class='menuItems'><a href='#'>Submit Reimbursement Request</a></p><p class='menuItems'><a href='#'>View Pending Requests</a></p><p class='menuItems'><a href='#'>View Resolved Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/accountinformation.html'>View Account Information</a></p><p class='menuItems'><a href='#'>Logout</a></p>";
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
