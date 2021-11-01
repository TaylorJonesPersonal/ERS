
// counters for correct and incorrect message control flow
var counterIncorrect = 0;
var counterCorrect = 0;

// event listener for signup form
var form = document.getElementById("signUpForm").addEventListener('submit', signup);

// event listener for manaager? checkbox
var Question = document.getElementById("manager").addEventListener('click', managerQuestion);

var mq = document.getElementById("managerQuestion");

function managerQuestion() {
	if (document.getElementById("manager").checked == false) {
		document.getElementById("submitbtn").style.visibility = "visible";
		counterIncorrect = 0;
		counterCorrect = 0;
	}
	mQuestion = document.getElementById("manager").checked;
	if (mQuestion == true) {
		console.log("in the if block");
		var newNode = document.createElement("div");
		newNode.id = "isManagerDiv";
		newNode.innerHTML = "<i>&nbsp;&nbsp;&nbsp; I attest, under penalty of perjury, that I am a manager of the Expense Reimbursement System, or an authorized representative thereof.</i><br><span>&nbsp;&nbsp;&nbsp;&nbsp;Manager Key Code: <input type='text' ID='managerConfirm' autocomplete='off' /></span>";
		mq.appendChild(newNode);
		document.getElementById("managerConfirm").addEventListener('input', checkManagerKeyCode);
		console.log("at the end of the if block.");
	} else {
		mq.removeChild(mq.lastChild);
	}
}

function checkManagerKeyCode() {
	if (counterIncorrect >= 1 && document.getElementById("managerConfirm").value == "98011965101101989") {
		document.getElementById("incorrectCodeSpan").remove();
	}
	if (counterCorrect >= 1 && document.getElementById("managerConfirm").value != "98011965101101989") {
		document.getElementById("correctCodeSpan").remove();
	}
	if (document.getElementById("manager").checked == true && document.getElementById("managerConfirm").value == "98011965101101989") {
		let newNode = document.createElement("span");
		newNode.id = "correctCodeSpan";
		newNode.style.color = "green";
		newNode.innerHTML = "<i>&nbsp;&nbsp;&nbsp;&nbsp;Correct Manager Code";
		document.getElementById("isManagerDiv").appendChild(newNode);
		document.getElementById("submitbtn").style.visibility = "visible";
		counterCorrect++;
		counterIncorrect = 0;
	} else if (document.getElementById("manager").checked == true && document.getElementById("managerConfirm").value != "98011965101101989") {
		if (counterIncorrect == 0){
			let newNode = document.createElement("span");
			newNode.id = "incorrectCodeSpan";
			newNode.style.color = "red";
			newNode.innerHTML = "<i>&nbsp;&nbsp;&nbsp;&nbsp;Incorrect Manager Code";
			document.getElementById("isManagerDiv").appendChild(newNode);
			counterIncorrect++;
			counterCorrect = 0;
		}
		document.getElementById("submitbtn").style.visibility = "hidden";
	} else {
		return;
	}
}


async function signup(e) {
	e.preventDefault();
	
	var roleId;
	
	if(document.getElementById("manager").checked == true && document.getElementById("managerConfirm").value == "98011965101101989"){
		roleId = 2;
		isManager = true;
	} else {
		roleId = 1;
	}

	let firstname = document.getElementById("firstname").value;
	let lastname = document.getElementById("lastname").value;
	let email = document.getElementById("email").value;
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;

	let user = {
		firstname,
		lastname,
		email,
		username,
		password,
		roleId
	};

	try {
		let req = await fetch('http://localhost:8080/ERS/api/signup', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
		retrievalCode();
	} catch (e) {
		console.log(e);
		return;
	}

}

async function postRetrievalCode(e) {
	e.preventDefault();

	let newRetrievalCode = document.getElementById("retrievalCodeInput").value;
	let newUsername = document.getElementById("username").value;

	let post = {
		code: newRetrievalCode,
		username: newUsername
	};



	try {
		let req = await fetch('http://localhost:8080/ERS/api/invitecode', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(post)
		});
		let res = await req.json();
		location.href = "../html/login.html";
	} catch (e) {
		alert("Incorrect invite code. Please verify and try again.");
		return;
	}

}


function retrievalCode(email) {
	var newNode = document.createElement("div");
	newNode.style.backgroundColor = "#2D87D4";
	newNode.style.display = "inline-block";
	newNode.style.float = "left";
	newNode.style.marginLeft = "50px";
	newNode.style.marginTop = "20px";
	newNode.style.padding = "10px";
	newNode.style.paddingTop = "2px";
	newNode.style.borderStyle = "solid";
	newNode.style.borderRadius = "4%";
	newNode.style.width = "300px";
	newNode.innerHTML = "<p>A retrieval code has been sent to your email.</p><p>Please check your inbox and spam folder and enter it here: <form ID = 'retrievalCodeForm'><input type='text' ID = 'retrievalCodeInput' style='border-style:solid;' /><input type='submit' value='Verify' /></form>";
	document.getElementById("signUpDiv").appendChild(newNode);
	let form2 = document.getElementById("retrievalCodeForm").addEventListener('submit', postRetrievalCode);
}

