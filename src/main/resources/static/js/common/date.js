function standardPicker($obj) {
    return flatpickr($obj, {
        locale: "ko" // 언어 설정
        , dateFormat: "Y-m-d"
        , allowInput: false
        , disableMobile: "true"
    });
}

function standardPickerWithMinDate($obj) {
    return  flatpickr($obj, {
        locale: "ko" // 언어 설정
        , dateFormat: "Y-m-d"
        , minDate: 'today'
        , allowInput: false
        , disableMobile: "true"
    });
}

function periodPicker(from, to) {
    const fromPicker = flatpickr(from, {
        locale: "ko",
        dateFormat: "Y-m-d",
        onChange: function(selectedDates, dateStr) {
            if (selectedDates.length > 0) {
                toPicker.set('minDate', selectedDates[0]); // 종료일 최소값 = 시작일
            }
        }
    });

    const toPicker = flatpickr(to, {
        locale: "ko",
        dateFormat: "Y-m-d",
        onChange: function(selectedDates, dateStr) {
            if (selectedDates.length > 0) {
                fromPicker.set('maxDate', selectedDates[0]); // 시작일 최대값 = 종료일
            }
        }
    });
}

function checkDateInputForSearch() {
    let fromDate = $('input[name=dateFrom]').val();
    let toDate = $('input[name=dateTo]').val();
    if (!(fromDate == '' && toDate == '')) {
        if (fromDate == '' || toDate == '') {
            alert('검색할 기간의 날짜를 모두 입력해 주세요.');
            return false;
        }
    }

    return true;
}

function getDatetimeString(datetime, format) {
    return moment(datetime).format(format);
}

function getToday(format) {
    return moment().format(format);
}
