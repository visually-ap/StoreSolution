function initVestGaugeResultSize() {
    $('#vestSizeGaugeBackLength').val('');
    $('#vestSizeGaugeChest').val('');
    $('#vestSizeGaugeWaist').val('');

    $('#vestSizeResultBackLength').val('');
    $('#vestSizeResultChest').val('');
    $('#vestSizeResultWaist').val('');
}

function setReadonlyVestSizeEditInput(state) {
    $('.vestSizeEditInput').prop('readonly', state);
    setReadonlySelect($('.vestSizeEditSelect'), state);
}

function setVestGaugeSize(data) {
    $('#vestSizeGaugeBackLength').val(data.data01.toFixed(1));
    $('#vestSizeGaugeChest').val(data.data02.toFixed(1));
    $('#vestSizeGaugeWaist').val(data.data03.toFixed(1));
}

function setVestResultSize() {
    setVestBackLength();
    setVestChest();
    setVestWaist();
}

function setVestBackLength() {
    let vestSizeGaugeBackLength = Number($('#vestSizeGaugeBackLength').val());
    let vestSizeEditBackLength = Number($('#vestSizeEditBackLength').val());
    let vestBackNecklineEditValue = Number($('#vestSizeBackNeckline option:selected').val());

    // 수정량 세팅
    $('#vestSizeEditBackLength').val(getDecimalPoint(vestSizeEditBackLength, 1));
    setSign($('#vestSizeEditBackLength'));

    // 완성값 세팅 - 뒷중심줄임때문에
    let result = Number(vestSizeGaugeBackLength) + Number(vestSizeEditBackLength) + Number(vestBackNecklineEditValue);
    $('#vestSizeResultBackLength').val(getDecimalPoint(result, 1));
}

function setVestChest() {
    let vestSizeGaugeChest = Number($('#vestSizeGaugeChest').val());
    let vestSizeEditChest = Number($('#vestSizeEditChest').val());
    let vestSizeChestLine = Number($('#vestSizeChestLine option:selected').val());

    // 상동 수정량 부호 설정
    $('#vestSizeEditChest').val(getDecimalPoint(vestSizeEditChest, 1));
    setSign($('#vestSizeEditChest'));

    // 우찌 포함 완성값 계산
    vestSizeEditChest = vestSizeGaugeChest + vestSizeEditChest + vestSizeChestLine;
    $('#vestSizeResultChest').val(getDecimalPoint(vestSizeEditChest, 1));
}

function setVestWaist() {
    let vestSizeGaugeWaist = Number($('#vestSizeGaugeWaist').val());
    let vestSizeEditWaist = Number($('#vestSizeEditWaist').val());
    let vestSizeChestLine = Number($('#vestSizeChestLine option:selected').val());

    // 상동 수정량 부호 설정
    $('#vestSizeEditWaist').val(getDecimalPoint(vestSizeEditWaist, 1));
    setSign($('#vestSizeEditWaist'));

    // 우찌 포함 완성값 계산
    vestSizeEditWaist = vestSizeGaugeWaist + vestSizeEditWaist + vestSizeChestLine;
    $('#vestSizeResultWaist').val(getDecimalPoint(vestSizeEditWaist, 1));
}

$(document).on('blur', '#vestSizeEditBackLength', function () {
    const inputValue = $(this).val();
    if (isEmpty(inputValue) || Number(inputValue) == 0) {
        $(this).val('');
    } else {
        if (!isValid(`^[+-]?[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
            alert(`부호를 포함한 2자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 20.1, -20)`);
            $(this).val('');
            $(this).focus();
            return;
        }
    }

    // 뒷기장 수정량, 완성 계산
    setVestBackLength();
});

$(document).on('blur', '#vestSizeEditChest', function () {
    const inputValue = $(this).val();
    if (isEmpty(inputValue) || Number(inputValue) == 0) {
        $(this).val('');
    } else {
        if (!isValid(`^[+-]?[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
            alert(`부호를 포함한 2자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 20.1, -20)`);
            $(this).val('');
            $(this).focus();
            return;
        }
    }

    // 상동 수정량, 완성 계산
    setVestChest();
});

$(document).on('blur', '#vestSizeEditWaist', function () {
    const inputValue = $(this).val();
    if (isEmpty(inputValue) || Number(inputValue) == 0) {
        $(this).val('');
    } else {
        if (!isValid(`^[+-]?[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
            alert(`부호를 포함한 2자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 20.1, -20)`);
            $(this).val('');
            $(this).focus();
            return;
        }
    }

    // 중동 수정량, 완성 계산
    setVestWaist();
});

// 뒷고대 변경 시
$(document).on('change', '#vestSizeBackNeckline', function() {
    setVestBackLength();
});

// 우찌 변경 시
$(document).on('change', '#vestSizeChestLine', function() {
    setVestChest();
    setVestWaist();
});
