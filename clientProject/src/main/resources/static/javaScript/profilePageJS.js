document.addEventListener("DOMContentLoaded", function() {
    const tableRows = document.querySelectorAll(".tableClickable tbody tr");
    var tableRowArray = Array.from(tableRows);
    for (const tableRow of tableRows) {
        tableRow.addEventListener("click", function () {
            window.location.href = this.getAttribute("data-href");
        });
    }

    const checkboxes = document.querySelectorAll(".tableClickable tbody input[type='checkbox']");
    for (const checkbox of checkboxes) {
        checkbox.addEventListener("click", function(event) {
            event.stopPropagation();
        });
    }

    const tableHeaders = document.querySelectorAll(".tableClickable thead th");
    var sorted = {firstName: 0, lastName: 0};
    tableHeaders[0].addEventListener("click", function() {
        sortTable("data-firstName", "firstName");
    });

    tableHeaders[1].addEventListener("click", function() {
        sortTable("data-lastName", "lastName");
    });

    // Location filter
    const locationDropdown = document.getElementById("locationDropdown");
    locationDropdown.addEventListener("change", function () {
        const selectedLocation = this.value;
        const rows = document.querySelectorAll(".tableClickable tbody tr");
        rows.forEach(row => {
            if (selectedLocation === "" || row.getAttribute("data-location") === selectedLocation) {
                row.style.display = "table-row";
            } else {
                row.style.display = "none";
            }
        });
    });

    function sortTable(attribute, key) {
        if (sorted[key] === 0) {
            tableRowArray.sort((a, b) => {
                const aValue = a.getAttribute(attribute);
                const bValue = b.getAttribute(attribute);
                return aValue.localeCompare(bValue);
            });
            sorted[key] = 1;
        } else if (sorted[key] === 1) {
            tableRowArray.reverse();
            sorted[key] = 2;
        } else {
            tableRowArray = Array.from(tableRows);
            sorted[key] = 0;
        }
        const tableBody = document.querySelector(".tableClickable tbody");
        tableBody.innerHTML = "";
        tableRowArray.forEach(row => {
            tableBody.appendChild(row);
        });
    }
});