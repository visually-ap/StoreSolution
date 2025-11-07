function initOptionImage(target) {
    $(target).each(function () {
        $(this).show();
    });
}

function hideOptionImage(target, codeIdList) {
    $(target).each(function () {
        const codeId = $(this).find('.img-frame').data('codeid');
        if (codeIdList.includes(codeId)) {
            $(this).hide();
        }
    });
}

function hideOptionImageNot(target, codeIdList) {
    $(target).each(function () {
        const codeId = $(this).find('.img-frame').data('codeid');
        if (!codeIdList.includes(codeId)) {
            $(this).hide();
        }
    });
}

function selectDesignOption(target, value) {
    $(target).next().find('.selectedImage').removeClass('selectedImage');
    $(target).val(value);
    $(target).next().find(`[data-codeid=${value}]`).addClass('selectedImage');
}
/**
 * 카반 자켓 여부 제한 시작
 */
const JACKET_CAVAN_SET = [
    29  // 스타일
    , 191   // 단추개수
    , 177   // 앞주머니
    , 105   // amf
    , 97    // lapelQ, 라펠디자인
    , 139   // lapel, 라펠 너비
];

$(document).ready(function () {
    setJacketDesignOptionWithCavan('.jacketStyleDiv');
    setJacketDesignOptionWithCavan('.jacketButtonCountDiv');
    setJacketDesignOptionWithCavan('.jacketFrontPocketDiv');
    setJacketDesignOptionWithCavan('.jacketAmfDiv');
    setJacketDesignOptionWithCavan('.jacketLapelDiv');
    setJacketDesignOptionWithCavan('.jacketLapelQDiv');
    setDisableJacketStyleLimit(jacket.jacketStyle, true);
    setDisableJacketSleeveLimit(jacket.jacketSleeve, true);

    setDisablePantsFlapLimit(pants.pantsFlap, true);
    setDisablePantsBeltLoopLimit(pants.pantsFlapLength, true);
    setDisablePantsHemThicknessLimit(pants.pantsHemShape, true);

    setCoatDesignOptionWithRaglan('.coatStyleDiv');
    setCoatDesignOptionWithRaglan('.coatButtonCountDiv');
    setCoatDesignOptionWithRaglan('.coatChestPocketDiv');
    setCoatDesignOptionWithRaglan('.coatShoulderDiv');
    setCoatDesignOptionWithRaglan('.coatFrontPocketDiv');
    setCoatDesignOptionWithRaglan('.coatBackSlitDiv');
    setCoatDesignOptionWithRaglan('.coatSleeveDiv');
    setCoatDesignOptionWithRaglan('.coatLapelDiv');
    setCoatDesignOptionWithRaglan('.coatSleeveButtonDiv');
    setCoatDesignOptionWithRaglan('.coatLapelQDiv');
    setCoatDesignOptionWithRaglan('.coatAmfDiv');
});

function setJacketDesignOptionWithCavan(target) {
    initOptionImage(target);
    if (jacket.jacketPattern != null && jacket.jacketPattern == 33) {   // 카반
        hideOptionImageNot(target, JACKET_CAVAN_SET);
    } else {    // 카반 아닌것
        hideOptionImage(target, JACKET_CAVAN_SET);
    }
}


/**
 * 옵션 선택 사항 제한 시작
 */
const JACKET_SINGLE_BUTTON_COUNT_SET = [
    187, 188, 191
];
const JACKET_DOUBLE_BUTTON_COUNT_SET = [
    30, 31, 189, 191
];
const JACKET_SINGLE_N_LAPELQ_SET = [
    307, 308, 309, 93, 97, 95, 310, 504
];
const JACKET_SINGLE_P_LAPELQ_SET = [
    91, 92, 38, 93, 97, 95, 310, 504
];
const JACKET_DOUBLE_P_LAPELQ_SET = [
    37, 307, 308, 309, 93, 95, 310, 504, 509
];

$(document).on('click', '.jacketStyleDiv .img-frame', function () {
    setJacketDesignOptionWithCavan('.jacketButtonCountDiv');
    setJacketDesignOptionWithCavan('.jacketLapelQDiv');
    setDisableJacketStyleLimit($(this).data('codeid'), false);
});

function setDisableJacketStyleLimit(style, isInit) {
    const jacketButtonCount = $('#jacketButtonCount').val();
    const jacketLapelQ = $('#jacketLapelQ').val();

    if (style == 27) {  // 싱글 노치드
        // 단추갯수 초기화
        hideOptionImage('.jacketButtonCountDiv', JACKET_SINGLE_BUTTON_COUNT_SET);

        // 라펠 디자인 초기화
        hideOptionImage('.jacketLapelQDiv', JACKET_SINGLE_N_LAPELQ_SET);
        if (!isInit && (jacketButtonCount != 30 || jacketLapelQ != 37)) {
            alert('상의 스타일 변경 시 단추갯수와 라펠디자인이 초기화됩니다.\n단추갯수 및 라펠디자인 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#jacketButtonCount', 30);
            selectDesignOption('#jacketLapelQ', 37);
        }
    } else if (style == 292) {  // 싱글 피크드
        // 단추갯수 초기화
        hideOptionImage('.jacketButtonCountDiv', JACKET_SINGLE_BUTTON_COUNT_SET);

        // 라펠 디자인 초기화
        hideOptionImage('.jacketLapelQDiv', JACKET_SINGLE_P_LAPELQ_SET);
        if (!isInit && (jacketButtonCount != 30 || jacketLapelQ != 37)) {
            alert('상의 스타일 변경 시 단추갯수와 라펠디자인이 초기화됩니다.\n단추갯수 및 라펠디자인 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#jacketButtonCount', 30);
            selectDesignOption('#jacketLapelQ', 37);
        }
    } else if (style == 28) {   // 더블 피크드
        // 단추갯수 초기화
        hideOptionImage('.jacketButtonCountDiv', JACKET_DOUBLE_BUTTON_COUNT_SET);

        // 라펠 디자인 초기화
        hideOptionImageNot('.jacketLapelQDiv', JACKET_DOUBLE_P_LAPELQ_SET);
        if (!isInit && (jacketButtonCount != 187 || jacketLapelQ != 37)) {
            alert('상의 스타일 변경 시 단추갯수와 라펠디자인이 초기화됩니다.\n단추갯수 및 라펠디자인 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#jacketButtonCount', 187);
            selectDesignOption('#jacketLapelQ', 37);
        }
    }
}


const JACKET_SLEEVE_BUTTON_SET = [
    144,140,141,143,142,145,146,149,150,148
];

// 소매디자인 제한
$(document).on('click', '.jacketSleeveDiv .img-frame', function () {
    setJacketDesignOptionWithCavan('.jacketSleeveButtonDiv');
    setDisableJacketSleeveLimit($(this).data('codeid'), false);
});

function setDisableJacketSleeveLimit(sleeve, isInit) {
    const jacketSleeveButton = $('#jacketSleeveButton').val();

    if (sleeve != 114) {    // 오픈 해리마감이 아니면
        hideOptionImage('.jacketSleeveButtonDiv', []);
        if (!isInit && jacketSleeveButton != 144) {
            alert('상의 소매디자인 변경 시 소매단추가 초기화됩니다.\n소매단추 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#jacketSleeveButton', 144);
        }
    } else {    // 오픈 해리마감이면
        hideOptionImage('.jacketSleeveButtonDiv', JACKET_SLEEVE_BUTTON_SET);
        if (!isInit && jacketSleeveButton != 147) {
            alert('상의 소매디자인 변경 시 소매단추가 초기화됩니다.\n소매단추 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#jacketSleeveButton', 147);
        }
    }
}


/**
 * 하의 비죠 길이 제한
 */
const PANTS_FLAP_LENGTH_SET = [
    211
];
$(document).on('click', '.pantsFlapDiv .img-frame', function () {
    initOptionImage('.pantsFlapLengthDiv');
    setDisablePantsFlapLimit($(this).data('codeid'), false);
});

function setDisablePantsFlapLimit(flap, isInit) {
    const pantsFlapLength = $('#pantsFlapLength').val();
    const pantsBeltLoop = $('#pantsBeltLoop').val();

    if (flap == 209) {    // 비죠없음이면
        hideOptionImageNot('.pantsFlapLengthDiv', PANTS_FLAP_LENGTH_SET);
        if (!isInit && (pantsFlapLength != 211 || pantsBeltLoop != 192)) {
            alert('하의 비죠 변경 시 밸트고리와 길이가 초기화됩니다.\n벨트고리 및 길이 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#pantsFlapLength', 211);
            selectDesignOption('#pantsBeltLoop', 192);
        }
    } else {    // AP 삼각이면
        hideOptionImage('.pantsFlapLengthDiv', PANTS_FLAP_LENGTH_SET);
        if (!isInit && (pantsFlapLength == 211 || pantsBeltLoop != 192)) {
            alert('하의 비죠 변경 시 밸트고리와 길이가 초기화됩니다.\n벨트고리 및 길이 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#pantsFlapLength', 212);
            selectDesignOption('#pantsBeltLoop', 192);
        }
    }
}
/**
 * 하의 벨트 고리 제한
 */
const PANTS_BELT_LOOP_SET = [
    193
];
$(document).on('click', '.pantsFlapLengthDiv .img-frame', function () {
    initOptionImage('.pantsBeltLoopDiv');
    setDisablePantsBeltLoopLimit($(this).data('codeid'), false);
});

function setDisablePantsBeltLoopLimit(pantsFlapLength, isInit) {
    const pantsBeltLoop = $('#pantsBeltLoop').val();

    if (pantsFlapLength == 211 || pantsFlapLength == 212) {    // X, 6.5
        hideOptionImage('.pantsBeltLoopDiv', PANTS_BELT_LOOP_SET);
        if (!isInit && pantsBeltLoop != 192) {
            alert('하의 길이 변경 시 벨트고리가 초기화됩니다.\n벨트고리 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#pantsBeltLoop', 192);
        }
    } else {    // 그외라면
        hideOptionImage('.pantsBeltLoopDiv', []);
        if (!isInit && pantsBeltLoop != 192) {
            alert('하의 길이 변경 시 벨트고리가 초기화됩니다.\n벨트고리 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#pantsBeltLoop', 192);
        }
    }
}

/**
 * 밑단 두께 제한
 */
const PANTS_HEM_THICKNESS_SET = [
    202
];
$(document).on('change', '.pantsHemShapeDiv', function () {
    initOptionImage('.pantsFlapLengthDiv');
    setDisablePantsHemThicknessLimit($(this).data('codeid'), false);
});

function setDisablePantsHemThicknessLimit(hemShape, isInit) {
    const pantsHemThickness = $('#pantsHemThickness').val();
    if (hemShape != 199) {    // 카브라가 아니면
        hideOptionImage('.pantsHemThicknessDiv', PANTS_HEM_THICKNESS_SET);
        if (!isInit && pantsHemThickness != 202) {
            alert('하의 밑단모양 변경 시 밑단두께가 초기화됩니다.\n밑단두께 옵션을 확인하시기 바랍니다.');
            selectDesignOption('#pantsHemThickness', 202);
        }
    } else {    // 카브라 라면
        hideOptionImage('.pantsHemThicknessDiv', []);
        if (!isInit && pantsHemThickness != 202) {
            alert('하의 밑단모양 변경 시 밑단두께가 초기화됩니다.\n밑단두께 옵션을 확인하시기 바랍니다.');
            $('#pantsHemThickness').val(202);
        }
    }
}

























/**
 * 싱글레글런 여부 제한 시작
 */
const COAT_SINGLE_RAGLAN_SET = [
    330  // 스타일
    , 337   // 단추개수
    , 347   // 가슴포켓
    , 351   // 어깨
    , 363    // 앞주머니
    , 372   // 뒷트임
    , 403   // 소매
    , 397   // lapel, 라펠 너비
    , 439   // 소매 단추
    , 412   // lapelQ, 라펠 디자인
    , 420   // amf
];
const COAT_DOUBLE_RAGLAN_SET = [
    331  // 스타일
    , 340   // 단추개수
    , 347   // 가슴포켓
    , 351   // 어깨
    , 363    // 앞주머니
    , 372   // 뒷트임
    , 403   // 소매
    , 397   // lapel, 라펠 너비
    , 439   // 소매 단추
    , 412   // lapelQ, 라펠 디자인
    , 420   // amf
];

function setCoatDesignOptionWithRaglan(target) {
    if (coat.coatPattern != null && coat.coatPattern == 18) {   // 싱글 레글런
        hideOptionImageNot(target, COAT_SINGLE_RAGLAN_SET);
    } else if (coat.coatPattern != null && coat.coatPattern == 22) {
        hideOptionImageNot(target, COAT_DOUBLE_RAGLAN_SET);
    } else {    // 카반 아닌것
        hideOptionImage(target, COAT_SINGLE_RAGLAN_SET);
        hideOptionImage(target, COAT_DOUBLE_RAGLAN_SET);
    }
}
