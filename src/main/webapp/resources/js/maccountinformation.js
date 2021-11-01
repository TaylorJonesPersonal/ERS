window.onload = function() {
	checkSession();
	getSessionInfo();
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
}
var controlFlag = 0;
var dropdown = document.getElementById("sidebarButton").addEventListener('click', menuControl);

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
	newNode.innerHTML = "<p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/mpendingrequests.html'>View/Approve Pending Requests</a></p><p class='menuItems'><a href='http://localhost:8080/EPS/resources/html/mresolvedrequests.html'>View All Resolved Requests</a></p><p class='menuItems'><a href='http://localhost:8080/EPS/resources/html/viewspecific.html'>View Specific Employee Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/mviewallemployees.html'>View Employee Informatiopn</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/signup.html' onClick ='logout()'>Logout</a></p>";
	document.getElementById("sidebarContainer").appendChild(newNode);
};

function foldMenu() {
	var container = document.getElementById("sidebarContainer");
	container.removeChild(container.lastChild);
};

function disappear() {
	var newNode = document.createElement("div");
	newNode.innerHTML = "<div ID = 'statusContainerDiv'><span>Updated</span></div>";
	newNode.style.marginLeft = "-100px";
	document.getElementById("statusContainer").appendChild(newNode);	
}

function removeElement(){
	var node = document.getElementById("statusContainer");
	node.removeChild(node.lastChild);
}

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
		newNode.innerHTML = sessionInfo.password;
		document.getElementById("employeePassword").appendChild(newNode);
		
		// employee role
		newNode = document.createElement("span");
		if(sessionInfo.roleID == "1"){
			newNode.innerHTML = "Employee";
			document.getElementById("employeeRole").appendChild(newNode);
		} else if(sessionInfo.roleID == "2"){
			newNode.innerHTML = "Manager";
			document.getElementById("employeeRole").appendChild(newNode);
		}
	} catch (e) {
		console.log(e);
		return;
	}
};


// update USERNAME
async function updateUsername(){
	
	// step 1 to comply with UserDao: the username
	let username = document.getElementById("employeeUsername").innerText;
	
	// step 2 to comply with UserDao: the change
	let change = document.getElementById("usernameInput").value;
	
	let user = {
		nameOfField: "username",
		username,
		change
	}
	
	
	try{
		let request = await fetch('http://localhost:8080/ERS/api/update', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
				body: JSON.stringify(user)
		});
		let response = await request.text();
		document.getElementById("employeeUsername").innerText= response;
		document.getElementById("usernameInput").value = "";
		disappear();
		setTimeout(removeElement, 2000);
		
	}catch(e){
		console.log(e);
		return;
	}

}

async function updatePassword(){
	
	// step 1 to comply with UserDao: the username
	let username = document.getElementById("employeeUsername").innerText;
	
	// step 2 to comply with UserDao: the change
	let change = document.getElementById("passwordInput").value;
	
	let user = {
		nameOfField: "password",
		username,
		change
	}
	
	
	try{
		let request = await fetch('http://localhost:8080/ERS/api/update', {
			method: 'POST',
			headers: {
				'Content-Type': 'applicaton/json'
			},
				body: JSON.stringify(user)
		});
		let response = await request.text();
		document.getElementById("employeePassword").innerText= response;
		document.getElementById("passwordInput").value = "";
		disappear();
		setTimeout(removeElement, 2000);
		
	}catch(e){
		console.log(e);
		return;
	}

}
