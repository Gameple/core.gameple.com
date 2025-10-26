async function login(event) {
    event.preventDefault();

    const response = await fetch('/api/v1/user/authenticate', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        })
    });

    if (response.ok) {
        alert("로그인 성공!");
    } else {
        alert("로그인 실패");
    }
}