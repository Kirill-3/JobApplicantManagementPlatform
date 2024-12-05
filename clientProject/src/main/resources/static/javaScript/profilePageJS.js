document.addEventListener("DOMContentLoaded", function() {
    const tableRows = document.querySelectorAll(".tableClickable tbody tr");
    var tableRowsDynamic = document.querySelectorAll(".tableClickable tbody tr");
    var tableRowArray = Array.from(tableRows);
    var eventFilter = "" ;
    var locationFilter = "" ;
    var skillFilter = "" ;
    var statusFilter = "" ;
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
    document.getElementById("locationDropdown").addEventListener("change", function () {
        filterTable("data-location", this.value);
    });

    //event filter
    document.getElementById("eventDropdown").addEventListener("change", function () {
        filterTable("data-event", this.value);
    });

    //skill filter
    document.getElementById("skillDropdown").addEventListener("change", function () {
        filterTable("data-skill", this.value);
    });
    //status filter
    document.getElementById("statusDropdown").addEventListener("change", function () {
        filterTable("data-status", this.value);
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

    function filterTable(attribute, value) {
        if (attribute === "data-location") {
            locationFilter = value;
        } else if (attribute === "data-event") {
            eventFilter = value;
        } else if (attribute === "data-skill") {
            skillFilter = value;
        } else if (attribute === "data-status") {
            statusFilter = value;
            }


        rows = document.querySelectorAll(".tableClickable tbody tr");
        for (const row of rows) {
            if (row.getAttribute("data-location").includes(locationFilter) && row.getAttribute("data-event").includes(eventFilter) && row.getAttribute("data-skill").includes(skillFilter) && row.getAttribute("data-status").includes(statusFilter)) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        }
    }
});

function showEditForm() {
    document.getElementById('editForm').style.display = 'block';
}

function validateUKPhoneNumber(input) {
    const phoneNumber = input.value;
    // https://stackoverflow.com/questions/5286046/javascript-phone-number-validation
    const isValid = /^(\(?(0|\+44)[1-9]{1}\d{1,4}?\)?\s?\d{3,4}\s?\d{3,4})$/.test(phoneNumber);
    if (!isValid) {
        input.setCustomValidity("Please enter a valid UK phone number.");
    } else {
        input.setCustomValidity("");
    }
}

function validateStatus() {
    const statusInput = document.getElementById('status').value;
    const validStatuses = ['Internal', 'External'];
    if (!validStatuses.includes(statusInput)) {
        alert("Please enter a valid status: 'Internal' or 'External'.");
        return false;
    }
    return true;
}