function setReadonlySelect($obj, state) {
    if (state) {
        $obj.addClass('select_readonly');
    } else {
        $obj.removeClass('select_readonly');
    }
}

function calcRealSizeToCenti($obj) {
    let inputValue = $obj.val();

    let target = $obj.parent().next().find('.realCenti');
    if (isEmpty(inputValue)) {
        $obj.val('');
        target.val('');
        return;
    }
    if (Number(inputValue) == 0) {
        $obj.val('');
        target.val('');
        return;
    }

    if (!isValid(`^[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
        alert(`3자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 200.1, 200)`);
        $obj.val('');
        target.val('');
        $obj.focus();
        return;
    }

    $obj.val(parseFloat(inputValue).toFixed(1));

    let result = Number(inputValue) * 2.54;
    if (result > 999.9) {
        alert(`입력하기에 너무 큰 수 입니다.`);
        $obj.val('');
        target.val('');
        $obj.focus();
        return;
    }
    target.val(result.toFixed(1));
}

function calcRealSizeToInch($obj) {
    let inputValue = $obj.val();

    let target = $obj.parent().prev().find('.realInch');
    if (isEmpty(inputValue)) {
        $obj.val('');
        target.val('');
        return;
    }
    if (Number(inputValue) == 0) {
        $obj.val('');
        target.val('');
        return;
    }

    if (!isValid(`^[0-9]{1,3}(?:\\.[0-9]{1})?$`, inputValue)) {
        alert(`3자리 이내의 숫자 또는 소수점 1자리를 포함한 숫자로 입력해 주세요.\n(입력 예시 : 200.1, 200)`);
        $obj.val('');
        target.val('');
        $obj.focus();
        return;
    }

    $obj.val(parseFloat(inputValue).toFixed(1));

    let result = Number(inputValue) / 2.54;
    target.val(result.toFixed(1));
}

function setSizeEdit($obj) {
    $obj.val(getDecimalPoint($obj.val(), 1));
    setSign($obj);

    // 완성값 계산
    setResultSize($obj);
}

function setResultSize($obj) {
    let gaugeValue = $obj.parent().prev().find('input').val();
    let editValue = $obj.val();
    let result = Number(gaugeValue) + Number(editValue);

    $obj.parent().next().find('input').val(getDecimalPoint(result, 1));
}

function getDecimalPoint(value, digit) {
    return parseFloat(value).toFixed(digit)
}

function setSign($obj) {
    $obj.removeClass('positive_number_color');
    $obj.removeClass('negative_number_color');
    if (!isEmpty($obj.val())) {
        let value = Number($obj.val());
        if (value > 0) {
            if ($obj.val().indexOf('+') < 0) {
                $obj.val('+' + $obj.val());
            }
            // 양수일 경우 텍스트 색상 변경 #eb2020
            $obj.addClass('positive_number_color');
        } else if (value < 0) {
            // 음수일 경우 텍스트 색상 변경 #eb2020
            $obj.addClass('negative_number_color');
        } else {
            $obj.val('');
        }
    }
}
