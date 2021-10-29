let form = document.getElementById("signUpForm").addEventListener('submit', signup);
var Question = document.getElementById("manager").addEventListener('click', managerQuestion);

function managerQuestion(){
	var mq = document.getElementById("managerQuestion");
	mQuestion = document.getElementById("manager").checked;
	if(mQuestion == true){
		console.log("in the if block");
		let newNode = document.createElement("div");
		newNode.innerHTML = "<i>&nbsp;&nbsp;&nbsp; I swear, under penalty of perjury, that I am a manager of the Expense Reimbursement System, or an authorized representative thereof.</i>";
		mq.appendChild(newNode);
		console.log("at the end of the if block.");
	}else{
		mq.removeChild(mq.lastChild);
	}
}


async function signup(e){
	e.preventDefault();
	
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
		password
	};
	
	try{
		let req = await fetch('http://localhost:8080/ERS/api/signup', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
		retrievalCode();
	} catch(e){
		console.log(e);
		return;
	}
	
}

async function postRetrievalCode(e){
	e.preventDefault();
	
	let newRetrievalCode = document.getElementById("retrievalCodeInput").value;
	let newUsername = document.getElementById("username").value;
	
	let post = {
		code : newRetrievalCode,
		username : newUsername
	};
	
	
	
	try{
		let req = await fetch('http://localhost:8080/ERS/api/invitecode', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(post)
		});
		let res = await req.json();
		location.href = "../html/login.html";
		} catch(e){
			alert("Incorrect invite code. Please verify and try again.");
			return;
		}
	
}


function retrievalCode(email){
	var newNode = document.createElement("div");
	newNode.style.backgroundColor="#2D87D4";
	newNode.style.display= "inline-block";
	newNode.style.float= "left";
	newNode.style.marginLeft ="50px";
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

