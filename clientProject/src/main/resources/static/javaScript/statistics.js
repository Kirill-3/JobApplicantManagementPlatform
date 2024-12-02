function fetchData(route, callback) {
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", route, true);
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            const response = JSON.parse(xhttp.responseText);
            callback(response);
        }
    };
    xhttp.send();
}

document.addEventListener("DOMContentLoaded", () => {
    // Fetch and render charts for different categories
    fetchData("/api/statistics/location", (data) =>
        renderChart
        ("locationRecruitmentChart", data, "location", "count", "bar"));
    fetchData("/api/statistics/time-period", (data) =>
        renderChart
        ("timePeriodRecruitmentChart", data, "time_period", "count", "line"));
    fetchData("/api/statistics/event", (data) =>
        renderChart
        ("eventRecruitmentChart", data, "eventAttended", "count", "pie"));
});

function renderChart(canvasId, data, labelKey, valueKey, chartType) {
    const labels = data.map(item => item[labelKey]);
    const values = data.map(item => item[valueKey]);

    let chartConfig = {
        type: chartType,  // Dynamically set the chart type
        data: {
            labels: labels,
            datasets: [{
                label: "Count",
                data: values,
                backgroundColor: getBackgroundColor(chartType),
                borderColor: "rgb(75,192,174)",
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: chartType === "bar" ? {
                y: {
                    beginAtZero: true
                }
            } : {}
        }
    };

    new Chart(document.getElementById(canvasId), chartConfig);
}

// Function to return different background colors based on the chart type
function getBackgroundColor(chartType) {
    switch(chartType) {
        case "bar":
            return "rgba(75, 192, 192, 0.2)";
        case "line":
            return "rgba(153, 102, 255, 0.2)";
        case "pie":
            return ["#FF5733", "#33FF57", "#3357FF", "#F4FF33", "#FF33F4",
                "#FF8C00", "#800080", "#008080", "#FFD700", "#FF4500",
                "#4682B4", "#32CD32", "#DC143C", "#6A5ACD", "#20B2AA",];
        default:
            return "rgba(75, 192, 192, 0.2)";
    }
}
