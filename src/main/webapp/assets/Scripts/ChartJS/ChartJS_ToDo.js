var datenToDo = [];
var namenToDo = [];

var daten = JSON.parse('${overviewBean.getTodoChartData()}');

for (var person in daten) {
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
            backgroundColor: ["#558ed5", "#fc645c", "#6de77b", "#f89a50", "#8cd4f5", "#d27ce0", "#ece08a", "#343a58", "#66f6f6", "#6fffab", "#fa98c1", "#6c3c5d", "#50876f", "#111111", "#aaaaaa"],
            data: datenToDo
        }]
    },
    options: {}
});