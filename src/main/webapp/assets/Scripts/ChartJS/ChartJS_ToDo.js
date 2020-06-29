var datenToDo = [];
var namenToDo = [];

var daten = JSON.parse('${overview.getTodoChartData()}');

for(var person in daten) {
    namenToDo.push(person);
    datenToDo.push(daten[person]);
}

var ctx2 = document.getElementById('myChartTodo').getContext('2d');
var chart = new Chart(ctx2, {
    type: 'pie',
    data: {
        labels: namenToDo,
        datasets: [{
            label: "ToDo's der WG",
            backgroundColor: ["#0074D9", "#FF4136", "#2ECC40", "#FF851B", "#7FDBFF", "#B10DC9", "#FFDC00", "#001f3f", "#39CCCC", "#01FF70", "#85144b", "#F012BE", "#3D9970", "#111111", "#AAAAAA"],
            data: datenToDo
        }]
    },
    options: {
    }
});