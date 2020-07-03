var datenGuthaben = ${overview.getBalanceChartData()};

var monthNames = ["Januar", "Februar", unescape("M%E4rz"), "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"];

var today = new Date();
var d;
var month;
var labels = [];

for(var i = 5; i >= 0; i -= 1) {
    d = new Date(today.getFullYear(), today.getMonth() - i, 1);
    month = monthNames[d.getMonth()];
    labels.push(month);
}


var ctx = document.getElementById('myChart').getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',

    // The data for our dataset
    data: {
        labels: labels,
        datasets: [{
            label: unescape("%DCbersicht") +  ' Finanzen',
            backgroundColor: 'rgb(215,90,90)',
            borderColor: 'rgb(215,90,90)',
            data: datenGuthaben
        }]
    },

    // Configuration options go here
    options: {}
});