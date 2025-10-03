function initTimeTable(tableId) {
    const table = document.getElementById(tableId);
    table.innerHTML = `
        <thead>
        <tr></tr>
        </thead>
        <tbody id="scheduleBody">
        <!-- JS로 생성 -->
        </tbody>
    `;
}

function renderTimeTable(tableId, date) {
    showLoadingImage();

    callService(
        "GET"
        , "/reservation/employee/schedule/list?date=" + date
        , {}
        , function (response) {
            const fp1 = standardPicker($('.timetable_datepicker'));
            fp1.setDate(date);

            let employeeList = response.result;

            const table = document.getElementById(tableId);
            if (!table) {
                return;
            }

            const theadRow = table.querySelector('thead tr');
            const tbody = document.getElementById('scheduleBody');
            theadRow.innerHTML = '<th colspan="2"> </th>';
            tbody.innerHTML = '';

            // 직원 이름 열 추가
            employeeList.forEach(employee => {
                const th = document.createElement('th');
                th.textContent = employee.name;
                theadRow.appendChild(th);
            });

            /** ----------------------------
             * 1. 시간 미정 Row 추가
             * ---------------------------- */
            const trAllDay = document.createElement('tr');

            // 첫 두 칸(시간, 분) 합쳐서 "시간 미정"
            const allDayTh = document.createElement('th');
            allDayTh.colSpan = 2;
            allDayTh.textContent = "미정";
            trAllDay.appendChild(allDayTh);

            // 직원별 칸
            employeeList.forEach(employee => {
                const td = document.createElement('td');
                td.className = 'schedule-cell allday-cell';

                // 해당 직원의 allday 예약 찾기
                const alldayReservations = (employee.reservationList || []).filter(res => res.allDay);

                if (alldayReservations.length > 0) {
                    // td.style.backgroundColor = '#666';
                    // td.style.color = 'white';
                    // td.style.fontWeight = 'bold';

                    // 고객명을 줄바꿈으로 표시
                    td.innerHTML = alldayReservations
                        .map(res => `<span style="color:${getCellBackgroundColor(res.type)}"><span class="customerDetail" data-customerid="${res.customerId}">[${res.customerName}]</span>님 <span class="reservationDetail" data-reservationid="${res.reservationId}">예약</span></span>`)
                        .join('<br>');
                }

                trAllDay.appendChild(td);
            });

            tbody.appendChild(trAllDay);

            /** ----------------------------
             * 2. 기존 타임테이블 (09:00 ~ 23:00)
             * ---------------------------- */
            const startHour = 9;
            const endHour = 23;
            const skipMap = {}; // rowIdx-empIdx 키

            let rowIndex = 0;

            for (let hour = startHour; hour < endHour; hour++) {
                for (let i = 0; i < 6; i++) {
                    const minutes = i * 10;
                    const tr = document.createElement('tr');

                    if (i === 0) {
                        const hourTh = document.createElement('th');
                        hourTh.rowSpan = 6;
                        hourTh.textContent = `${String(hour).padStart(2, '0')}시`;

                        // ★ 시간별 id 추가 (예: time-09, time-10 ...)
                        hourTh.id = "time-" + String(hour).padStart(2, '0');

                        tr.appendChild(hourTh);
                    }

                    const minuteTh = document.createElement('th');
                    minuteTh.textContent = `${String(minutes).padStart(2, '0')}분`;
                    tr.appendChild(minuteTh);

                    employeeList.forEach((employee, empIdx) => {
                        const skipKey = `${rowIndex}-${empIdx}`;
                        if (skipMap[skipKey]) return;

                        const currentTime = new Date(`2000-01-01T${String(hour).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:00`);

                        // allDay 예약은 시간표에서는 제외
                        const reservation = (employee.reservationList || []).find(res => {
                            if (res.allDay) return false;
                            const from = new Date(res.consultingDatetimeFrom);
                            return from.getHours() === currentTime.getHours()
                                && from.getMinutes() === currentTime.getMinutes();
                        });

                        if (reservation) {
                            const from = new Date(reservation.consultingDatetimeFrom);
                            const to = new Date(reservation.consultingDatetimeTo);
                            const durationMin = (to - from) / (1000 * 60);
                            const rowspan = Math.max(1, durationMin / 10); // 10분 단위

                            const td = document.createElement('td');
                            td.rowSpan = rowspan;

                            // 배경 선택
                            td.className = 'schedule-cell';
                            td.style.backgroundColor = getCellBackgroundColor(reservation.type);
                            td.style.color = 'white';
                            td.style.fontWeight = 'bold';
                            td.innerHTML = `<span class="customerDetail" data-customerid="${reservation.customerId}">[${reservation.customerName}]</span>님 <span class="reservationDetail" data-reservationid="${reservation.reservationId}">예약</span>`;

                            // 중복 셀 제거용 skipMap 세팅
                            for (let j = 1; j < rowspan; j++) {
                                skipMap[`${rowIndex + j}-${empIdx}`] = true;
                            }

                            tr.appendChild(td);
                        } else {
                            const td = document.createElement('td');
                            td.className = 'schedule-cell';
                            tr.appendChild(td);
                        }
                    });

                    tbody.appendChild(tr);
                    rowIndex++;
                }
            }
        }
    );
}

function getCellBackgroundColor(type) {
    switch (type) {
        case 1:
            return siteEnv.typeColor1;
            break;
        case 2:
            return siteEnv.typeColor2;
            break;
        case 3:
            return siteEnv.typeColor3;
            break;
        case 4:
            return siteEnv.typeColor4;
            break;
        case 5:
            return siteEnv.typeColor5;
            break;
        case 6:
            return siteEnv.typeColor6;
            break;
        default:
            break;
    }
}
