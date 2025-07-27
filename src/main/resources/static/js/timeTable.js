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

            const startHour = 9;
            const endHour = 23;

            for (let hour = startHour; hour < endHour; hour++) {
                for (let i = 0; i < 6; i++) {
                    const minutes = i * 10;
                    const tr = document.createElement('tr');

                    // 시간(rowspan 6)
                    if (i === 0) {
                        const hourTh = document.createElement('th');
                        hourTh.rowSpan = 6;
                        hourTh.textContent = `${String(hour).padStart(2, '0')}시`;
                        tr.appendChild(hourTh);
                    }

                    // 분(minute) 열
                    const minuteTh = document.createElement('th');
                    minuteTh.textContent = `${String(minutes).padStart(2, '0')}분`;
                    tr.appendChild(minuteTh);

                    // 직원별 셀
                    employeeList.forEach(() => {
                        const td = document.createElement('td');
                        td.className = 'schedule-cell';
                        tr.appendChild(td);
                    });

                    tbody.appendChild(tr);
                }
            }
        }
    );
}
