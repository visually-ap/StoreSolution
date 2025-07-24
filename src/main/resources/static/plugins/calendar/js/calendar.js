$(document).ready(function (){
    let date = new Date();

    const renderCalender = () => {
        // 달력을 보여주는 함수
        const viewYear = date.getFullYear();
        const viewMonth = date.getMonth();

        // 현재 연도와 월 표시
        document.querySelector('.year-month').innerHTML = `<span id="yearText">${viewYear}</span>년 <span id="monthText">${viewMonth + 1}</span>월`;
        // $('.year-month').text('<span id=yearText>' + viewYear + '년</span> <span id="monthText">' + (viewMonth + 1) + '</span>월');

        // 지난달 마지막날과 이번달 마지막날
        const prevLast = new Date(viewYear, viewMonth, 0); // 현재기준(2023 12 31 일)
        const thisLast = new Date(viewYear, viewMonth + 1, 0); // 현재기준(2024 1 31 수)

        const PLDate = prevLast.getDate(); // 지난달 마지막 날 31일
        const PLDay = prevLast.getDay(); // 지난달 마지막 요일 토요일()

        const TLDate = thisLast.getDate(); // 이번달 마지막 날 31일
        const TLDay = thisLast.getDay(); // 이번달 마지막 요일 화요일()

        const prevDates = [];
        const thisDates = [...Array(TLDate + 1).keys()].slice(1);
        const nextDates = [];

        // 달력 합치기
        if(PLDay !== 6) {
            for(let i = 0; i < PLDay + 1; i++) {
                prevDates.unshift(PLDate - i); // 지난달 날짜들 넣어줌
            }
        }

        for(let i = 1; i < 7 - TLDay; i++) {
            nextDates.push(i); // 다음달 날짜 넣어줌
        }

        const dates = prevDates.concat(thisDates, nextDates); // 지날달 이번달 다음달 합쳐주기

        // 화면에 날짜 뿌려주기
        const firstDateIndex = dates.indexOf(1); // 이번달 1일의 인덱스 찾기
        const lastDateIndex = dates.lastIndexOf(TLDate); // 이번달 막날의 인덱스 찾기

        dates.forEach((date, i) => {
            const condition = i >= firstDateIndex && i < lastDateIndex + 1
                ? 'this' : 'other';
            dates[i] = `<div class="eachDate date date_click"><span class="${condition}">${date}</span></div>`
        });

        document.querySelector('.dates').innerHTML = dates.join('');

        // 오늘 날짜 표시하기
        const today = new Date();

        if(viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
            for(let date of document.querySelectorAll('.this')) {
                if(+date.innerText === today.getDate()) {
                    date.classList.add('today');
                    break;
                }
            }
        }
    };

    renderCalender();

    // 캘린더출력 함수 실행하고 이전달 다음달로 이동
    const prevMonth = () => {
        date.setMonth(date.getMonth() - 1);
        renderCalender();
    }

    const nextMonth = () => {
        date.setMonth(date.getMonth() + 1);
        renderCalender();
    }

    $(document).on('click', '.date_click', function () {
        if (!$(this).children('span').hasClass('other')) {
            $(this).parent().find('span').removeClass('this').removeClass('today');
            $(this).children('span').addClass('this today');

            $('.month-day').text($('#monthText').text() + '/' + $(this).text());

            let year = $('#yearText').text();
            let month = $('#monthText').text();
            let date = $(this).text();

            // callLazyService(
            //     "POST"
            //     , "/reservation/list"
            //     , JSON.stringify({
            //         "reservationDate": `${year}-${String(month).padStart(2, "0")}-${String(date).padStart(2, "0")}`
            //     })
            //     , function (response) {
            //         $('#reservationList').empty();
            //         if (response.dataList.length > 0) {
            //             $.each(response.dataList, function (i, x) {
            //                 let reservation = `<li class="line"><span class="date">${x.timeString}</span><p><span>${x.name}</span> 고객님 방문 예약</p></li>`;
            //                 $("#reservationList").append(reservation);
            //             });
            //         } else {
            //             $("#reservationList").append('<span class="reservationBlank">예약이 없습니다.</span>');
            //         }
            //     }
            // );
        }
    });

    $('#calendarPrevBtn').click(function () {
        prevMonth();
    });

    $('#calendarNextBtn').click(function () {
        nextMonth();
    });
});


