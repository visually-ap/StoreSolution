function getParamValue(key) {
    let url = new URLSearchParams(location.search);
    return url.get(key);
}

function getChangedParamURL(key, value) {
    let url = new URLSearchParams(location.search);
    url.set(key, value);

    return location.pathname + '?' + url.toString();
}
