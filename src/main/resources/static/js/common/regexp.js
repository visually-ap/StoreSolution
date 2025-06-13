function isValid(regexp, value) {
    return new RegExp(regexp, "g").exec(value) != null;
}
