function openModal(element, callback) {
    if ($(element).is(':visible')) {
        return;
    }

    $('body').css('overflow-y', 'hidden');
    $(element).css({"display": "block"});

    callback();
}

function closeModal(element, callback) {
    if ($(element).is(':hidden')) {
        return;
    }

    $('body').css('overflow-y', 'scroll');
    $(element).css({"display": "none"});

    callback();
}

