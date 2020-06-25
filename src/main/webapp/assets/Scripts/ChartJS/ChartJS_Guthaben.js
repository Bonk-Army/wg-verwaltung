var ctx = document.getElementById('myChart').getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',

    // The data for our dataset
    data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [{
            label: 'Mein Guthaben',
            backgroundColor: 'rgb(215,90,90)',
            borderColor: 'rgb(215,90,90)',
            data: [0, 100, 350, -200, 200, 300, 450]
        }]
    },

    // Configuration options go here
    options: {}
});