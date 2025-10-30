async function login(event) {
    event.preventDefault();

    const redirectUrl = document.getElementById('redirectUrl').value;

    const response = await fetch('/api/v1/oauth/authorize', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            email: document.getElementById('email').value,
            password: document.getElementById('password').value,
            clientType: document.getElementById('clientType').value,
            redirectUrl: document.getElementById('redirectUrl').value
        })
    });

    const result = await response.json();

    if (response.ok && result.data?.callback) {
        window.location.href = result.data.callback;
    } else {
        throwGamepleError(result.error?.code);
    }
}

(function() {
    const hidden = document.getElementById('redirectUrl');
    if (!hidden) return;
    if (hidden.value && hidden.value.trim() !== '') return;

    const params = new URLSearchParams(window.location.search);
    const redirect = params.get('redirectUrl') || '';

    if (redirect) {
        hidden.value = redirect;
    }
})();