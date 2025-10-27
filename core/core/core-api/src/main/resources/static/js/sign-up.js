document.addEventListener('DOMContentLoaded', () => {
    const select = document.getElementById('countryId');
    if (!select) return;

    fetch('/api/v1/branch/countries')
        .then(res => res.json())
        .then(result => {
            if (result.result === 'SUCCESS') {
                select.innerHTML = ''; // 기존 Loading 제거
                result.data.forEach(c => {
                    const option = document.createElement('option');
                    option.value = c.id;
                    option.textContent = `${c.name} (${c.nativeName}) ${c.phoneCode}`;
                    select.appendChild(option);
                });
            }
        })
        .catch(err => console.error(err));
});

async function signup(event) {
    event.preventDefault();

    const payload = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        passwordCheck: document.getElementById('passwordCheck').value,
        countryId: document.getElementById('countryId').value,
        nickName: document.getElementById('nickName').value
    };

    try {
        const response = await fetch('/api/v1/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        const result = await response.json();
        if (response.ok && result.data) {
            window.location.href = '/widget/v1/login';
        } else {
            throwGamepleError(result.error?.code);
        }
    } catch (e) {
        throwGamepleError('E500');
    }
}
