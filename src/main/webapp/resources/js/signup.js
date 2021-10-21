let form = document.getElementById("signUpForm").addEventListener('submit', signup);

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
		location.href="../html/login.html";
	} catch(e){
		alert('Signup error! Please try again.')
		return;
	}
	
}