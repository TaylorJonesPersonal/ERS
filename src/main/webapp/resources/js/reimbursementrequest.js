var controlFlag = 0;
var dropdown = document.getElementById("sidebarButton").addEventListener('click', menuControl);

var form = document.getElementById("timestampButton").addEventListener('click', systemTime);


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
	newNode.innerHTML = "<p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/reimbursementrequest.html'>Submit Reimbursement Request</a></p><p class='menuItems'><a href='#'>View Pending Requests</a></p><p class='menuItems'><a href='#'>View Resolved Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/signup.html'>Logout</a></p>";
	document.getElementById("sidebarContainer").appendChild(newNode);
};

function foldMenu() {
	var container = document.getElementById("sidebarContainer");
	container.removeChild(container.lastChild);
};

function systemTime(){
	var today = new Date();
	var date = today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate() + " " + today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
	let timestamp = document.getElementById("timestamp");
	timestamp.value = date;
}