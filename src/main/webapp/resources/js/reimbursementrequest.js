// check login
window.onload = function(){
	checkSession();
}


var submitFormControlFlag = 0;
var controlFlag = 0;
var dropdown = document.getElementById("sidebarButton").addEventListener('click', menuControl);

var stamp = document.getElementById("timestampButton").addEventListener('click', systemTime);
var form = document.getElementById("reimbursementForm").addEventListener('submit', formInfo);

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
	newNode.innerHTML = "<p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/reimbursementrequest.html'>Submit Reimbursement Request</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/reimbursementview.html'>View Reimbursement Requests</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/accountinformation.html'>View Account Information</a></p><p class='menuItems'><a href='http://localhost:8080/ERS/resources/html/signup.html' onClick='logout()'>Logout</a></p>";
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
	timestamp.readOnly = true;
	addSubmitButton();
}

function addSubmitButton(){
	if(submitFormControlFlag == 0)
	{
	let newNode = document.createElement("span");
	newNode.innerHTML = "<input type='submit', value='submit' />";
	document.getElementById("reimbursementForm").appendChild(newNode);
	submitFormControlFlag++;
	}
}

async function formInfo(e){
	e.preventDefault();
	if(document.getElementById("cents").value == ".0" || document.getElementById("cents").value == ".00"){
	amount = document.getElementById("dollars").value + "." + document.getElementById("cents").value;
	} else if (document.getElementById("cents").value == "00" || document.getElementById("cents").value == "0"){
	amount = document.getElementById("dollars").value + "." + document.getElementById("cents").value;
	}
	
	let type = document.getElementById("type").value;
	let time = document.getElementById("timestamp").value;
	let description = document.getElementById("description").value;
	
	let reimbursement = {
		amount,
		type,
		time,
		description
	}
	
	try{
		let request = await fetch('http://localhost:8080/ERS/api/newreimbursement', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
				body: JSON.stringify(reimbursement)
		});
			console.log("Should be redirecting now.");
			location.href = "../html/reimbursementview.html"
	}catch(e){
		console.log(e);
	}
	
	}