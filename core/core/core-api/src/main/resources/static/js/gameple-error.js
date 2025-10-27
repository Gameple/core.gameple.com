const GAMEPLE_ERROR = {
    E400: 'E400',
    E402: 'E402',
    E500: 'E500',
    E1000: 'E1000',
    E1001: 'E1001',
    E1002: 'E1002',
    E2001: 'E2001',
    COMMON_ERROR: 'E500'
};

function throwGamepleError(errorCode) {
    const code = errorCode || GAMEPLE_ERROR.COMMON_ERROR;

    let message = '';
    switch (code) {
        case GAMEPLE_ERROR.E400:
            message = 'INVALID_REQUEST';
            break;
        case GAMEPLE_ERROR.E402:
            message = 'NOT_FOUND_DATA';
            break;
        case GAMEPLE_ERROR.E1000:
            message = 'EMAIL_ALREADY_EXIST';
            break;
        case GAMEPLE_ERROR.E1001:
            message = 'NICKNAME_ALREADY_EXIST';
            break;
        case GAMEPLE_ERROR.E1002:
            message = 'USER_PASSWORD_MISMATCH';
            break;
        case GAMEPLE_ERROR.E2001:
            message = 'COUNTRY_NOT_EXIST';
            break;
        case GAMEPLE_ERROR.E500:
        default:
            message = 'DEFAULT_ERROR';
            break;
    }

    const error = { code, message };
    document.dispatchEvent(new CustomEvent('gameple_error', { detail: { error } }));
}

document.addEventListener('gameple_error', (e) => {
    const error = e.detail.error;
    alert(`Error: ${error.code}\nMessage: ${error.message}`);
});
