let expanded = false;

function showCheckboxes() {
    const checkboxes = document.getElementById("checkboxes");
    if (!expanded) {
        checkboxes.style.display = "block";
        expanded = true;
    } else {
        checkboxes.style.display = "none";
        expanded = false;
    }
}

function populateEventSelector(data) {
    const checkboxes = document.getElementById("checkboxes");
    checkboxes.innerHTML = ""; // Clear previous content

    data.forEach((item) => {
        const label = document.createElement("label");

        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.value = item.eventAttended;
        checkbox.classList.add("eventCheckbox");

        label.appendChild(checkbox);
        label.appendChild(document.createTextNode(item.eventAttended));
        checkboxes.appendChild(label);
    });

    // Update the chart when checkboxes are selected
    checkboxes.addEventListener("change", () => {
        const selectedEvents = Array.from(
            document.querySelectorAll(".eventCheckbox:checked")
        ).map((checkbox) => checkbox.value);

        // Filter and aggregate data for selected events
        const filteredData = data.filter((item) =>
            selectedEvents.includes(item.eventAttended)
        );

        const aggregatedData = aggregateData(filteredData, "eventAttended", "count");
        renderChart("eventPieChart", aggregatedData, "eventAttended", "count", "pie");
    });
}



// Aggregate data for multiple selected events
function aggregateData(data, labelKey, valueKey) {
    const aggregated = {};
    data.forEach((item) => {
        if (!aggregated[item[labelKey]]) {
            aggregated[item[labelKey]] = 0;
        }
        aggregated[item[labelKey]] += item[valueKey];
    });


    return Object.entries(aggregated).map(([key, value]) => ({
        [labelKey]: key,
        [valueKey]: value,
    }));
}


// Render the chart
function renderChart(canvasId, data, labelKey, valueKey, chartType) {
    const labels = data.map(item => item[labelKey]);
    const values = data.map(item => item[valueKey]);


    const chartConfig = {
        type: chartType,
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


    const chartCanvas = document.getElementById(canvasId);
    if (chartCanvas.chartInstance) {
        chartCanvas.chartInstance.destroy(); // Destroy existing chart to avoid overlap
    }


    chartCanvas.chartInstance = new Chart(chartCanvas, chartConfig);
}


// Function to return different background colors based on the chart type
function getBackgroundColor(chartType) {
    switch (chartType) {
        case "bar":
            return "rgba(75, 192, 192, 0.2)";
        case "line":
            return "rgba(153, 102, 255, 0.2)";
        case "pie":
            return [
                "#005EB8", "#76D1C4", "#EA7600", "#003087", "#AE2573",
                "#FFD1D1", "#6699CC", "#FF9999", "#FFCC33", "#B3D9FF",
                "#28A197", "#FFE680", "#D1B3C4", "#993300", "#0072CE",
                "#336633", "#C2E5FF", "#E87722", "#40E0D0", "#FFCC00",
                "#330072", "#B57EDC", "#80BFFF", "#CC3300", "#FF6666",
                "#669900", "#99CC00", "#CCFF99", "#FFB81C", "#D9B38C",
                "#660066", "#003366", "#A3A3A3", "#000000", "#505050",
                "#FFFFFF", "#F3F3F3", "#E6E6FA", "#00A37A", "#00A499"
            ];
        default:
            return "rgba(75, 192, 192, 0.2)";
    }
}


// Event listener for DOM content loaded
document.addEventListener("DOMContentLoaded", () => {
    fetchData("/api/statistics/location", (data) =>
        renderChart("locationRecruitmentChart", data, "location", "count", "bar"));
    fetchData("/api/statistics/time-period", (data) =>
        renderChart("timePeriodRecruitmentChart", data, "time_period", "count", "line"));
    fetchData("/api/statistics/event", (data) => {
        populateEventSelector(data);
        renderChart("eventRecruitmentChart", data, "eventAttended", "count", "pie");
    });
});

// Fetch data from the server
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
