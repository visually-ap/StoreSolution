/**
 * 문자열이 빈 문자열인지 체크하여 결과값을 리턴한다.
 * @param str        : 체크할 문자열
 */
function isEmpty(str) {

    if (typeof str == "undefined" || str == null || str == "")
        return true;
    else
        return false;
}

/**
 * 문자열이 빈 문자열인지 체크하여 기본 문자열로 리턴한다.
 * @param str            : 체크할 문자열
 * @param defaultStr    : 문자열이 비어있을경우 리턴할 기본 문자열
 */
function nvl(str, defaultStr) {

    if (typeof str == "undefined" || str == null || str == "")
        str = defaultStr;

    return str;
}

function addDashToMobileNumber(mobileNumber) {
    let buffer = mobileNumber;
    if (mobileNumber) {
        if (mobileNumber.length == 10) {
            buffer = mobileNumber.substring(0, 3) + "-" + mobileNumber.substring(3, 6) + "-" + mobileNumber.substring(6, 10);
        } else if (mobileNumber.length == 11) {
            buffer = mobileNumber.substring(0, 3) + "-" + mobileNumber.substring(3, 7) + "-" + mobileNumber.substring(7, 11);
        }
    }
    return buffer;
}

// 숫자만 남기기 (음수/소수점 허용)
function removeComma(v) {
    return (v || '')
        .replace(/[^\d.-]/g, '')     // 숫자, -, . 이외 제거
        .replace(/(?!^)-/g, '')      // 맨 앞 외의 - 제거
        .replace(/(\..*)\./g, '$1'); // 소수점 1개만 허용
}

// 1000단위 콤마 넣기
function addComma(v) {
    if (v === '' || v === '-' || v === '.' || v === '-.') return v;
    const sign = v.startsWith('-') ? '-' : '';
    v = v.replace(/^-/, '');
    const parts = v.split('.');
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    return sign + parts.join('.');
}
