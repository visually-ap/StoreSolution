function getCookie(name) {
    let value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value ? value[2] : null;
}

function setCookie(key, value) {
    document.cookie = `${key}=${value}; path=/;`;
}
