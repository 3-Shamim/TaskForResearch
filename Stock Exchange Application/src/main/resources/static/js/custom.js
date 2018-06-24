$(document).ready(function () {


    $("#logFormImage").tilt();

    // Clock
    setTimeout('updateClock()', 1000);


    // Chart
    var code = $("#tradingCode").val();
    chart(code);

})

function chart(code) {
    $.ajax({
        url: "http://localhost:8080/latestshareprice/" + code,
        type: "GET",
        success: function (data) {
            console.log(data);
            var date = [];
            var _change = [];
            var trade = [];
            var value = [];
            var volume = [];

            for (var i in data) {
                date.push(data[i].dateTime);
                _change.push(data[i]._change);
                trade.push(data[i].trade);
                value.push(data[i].value);
                volume.push(data[i].volume);
            }

            var chartdata = {
                labels: date,
                datasets: [
                    {
                        label: "_Change",
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: '#fffcbe',
                        borderColor: '#fff741',
                        borderWidth: 1,
                        pointHoverBackgroundColor: '#83cbff',
                        pointHoverBorderColor: '#0006ff',
                        data: _change
                    },
                    {
                        label: "log10(Trade)",
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: '#ff4d52',
                        borderColor: 'rgba(29, 202, 255, 1)',
                        borderWidth: 1,
                        pointHoverBackgroundColor: '#ff1811',
                        pointHoverBorderColor: 'rgba(29, 202, 255, 1)',
                        data: trade
                    },
                    {
                        label: "log10(Value)",
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: 'rgba(211, 72, 54, 0.75)',
                        borderColor: 'rgba(211, 72, 54, 1)',
                        borderWidth: 1,
                        pointHoverBackgroundColor: 'rgba(211, 72, 54, 1)',
                        pointHoverBorderColor: 'rgba(211, 72, 54, 1)',
                        data: value
                    },
                    {
                        label: "log10(Volume)",
                        fill: false,
                        lineTension: 0.1,
                        backgroundColor: '#98ff7f',
                        borderColor: 'rgba(211, 72, 54, 1)',
                        borderWidth: 1,
                        pointHoverBackgroundColor: '#24ff00',
                        pointHoverBorderColor: 'rgba(211, 72, 54, 1)',
                        data: volume
                    }
                ]
            };
            var ctx = $("#chartOne");
            var LineGraph = new Chart(ctx, {
                type: 'bar',
                data: chartdata
            });

            var ctx1 = $("#chartTwo");
            var LineGraph = new Chart(ctx1, {
                type: 'line',
                data: chartdata
            });
        },
        error: function (data) {

        }
    });
}

function updateClock() {
    var currentTime = new Date();
    var currentHours = currentTime.getHours();
    var currentMinutes = currentTime.getMinutes();
    var currentSeconds = currentTime.getSeconds();
    var timeOfDay = "AM";


    var year = currentTime.getFullYear();
    var day = currentTime.getDay();
    var month = currentTime.getMonth();
    var date = currentTime.getDate();

    var days = new Array("Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat");
    day = days[day];

    var months = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    month = months[month];


    if (currentHours == 0) {
        currentHours = 12;
    }
    if (currentHours > 12) {
        currentHours = currentHours - 12;
        timeOfDay = "PM";
    }

    if (currentHours < 10) {
        currentHours = "0" + currentHours;
    }

    if (currentMinutes < 10) {
        currentMinutes = "0" + currentMinutes;
    }

    if (currentSeconds < 10) {
        currentSeconds = "0" + currentSeconds;
    }

    // Compose the string for display
    var currentTimeString = day + " " + date + " " + month + " " + year + " | " + currentHours + ":" + currentMinutes + ":" + currentSeconds + " " + timeOfDay;


    $("#clock").html(currentTimeString);

    setTimeout('updateClock()', 1000);
}