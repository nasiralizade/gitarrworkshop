
async function drawStuff() {
    const data = new google.visualization.DataTable();
    data.addColumn('string', 'Month');
    data.addColumn('number', 'income');
    await fetch("myservlet")
        .then(response => response.json())
        .then(fetchedData => {
            fetchedData.forEach((item) => {
                data.addRow([item.month, item.income]);
            });
        });
    var options = {
        width: 800,
        legend: {position: 'none'},
        chart: {
            title: 'Monthly income',
            subtitle: 'in swedish kronor (SEK)'
        },
        axes: {
            x: {
                0: {side: 'top', label: 'AndersWorksShop'} // Top x-axis.
            }
        },
        bar: {groupWidth: "90%"}
    };
    var chart = new google.charts.Bar(document.getElementById('char-div'));
    // Convert the Classic options to Material options.
    chart.draw(data, google.charts.Bar.convertOptions(options));
}