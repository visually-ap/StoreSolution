function isValid(regexp, value) {
    return new RegExp(regexp, "g").exec(value) != null;
}

$(document).on('input', 'input[type=number][data-maxlength]', function () {
    const maxLength = $(this).data('maxlength');
    let val = $(this).val();

    if (val.length > maxLength) {
        $(this).val(val.slice(0, maxLength));
    }
});
