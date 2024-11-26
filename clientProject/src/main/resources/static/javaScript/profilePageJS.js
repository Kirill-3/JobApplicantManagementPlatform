document.addEventListener("DOMContentLoaded", function() {
    const tableRows = document.querySelectorAll(".tableClickable tbody tr");
    for (const tableRow of tableRows) {
        tableRow.addEventListener("click", function () {
            window.location.href = this.getAttribute("data-href");
        });
    }
});
document.addEventListener("DOMContentLoaded", function() {
    const tableRows = document.querySelectorAll(".tableClickable thead th");
    for (const tableRow of tableRows) {
        tableRow.addEventListener("click", function () {
            if (window.location.href.includes(this.getAttribute("data1-href"))) {
                if (window.location.href.includes("Ascending")) {
                    window.location.href = this.getAttribute("data1-href") + "Descending";
                } else if (this.getAttribute("data2-href") === "Descending" || window.location.href.includes("Descending")){
                    window.location.href = "/profile";
                } else
                {
                    window.location.href = this.getAttribute("data1-href") + "Ascending";
                }
            } else if (this.getAttribute("data1-href").includes("Name")) {
                window.location.href = this.getAttribute("data1-href") + this.getAttribute("data2-href");
            }

        }
    )}
});

document.addEventListener("DOMContentLoaded", function() {
    const tableHeaders = document.querySelectorAll(".tableClickable thead th");
    for (const tableHeader of tableHeaders) {
        tableHeader.addEventListener("click", function (event) {
            if (this.textContent === "Location") {
                event.preventDefault();
                const rows = document.querySelectorAll(".tableClickable tbody tr");
                rows.forEach(row => {
                    if (row.getAttribute("data-location").includes("a")) {
                        console.log(row);
                        row.style.display = "table-row";
                    } else {
                        row.style.display = "none";
                    }
                });
            }
        });
    }
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
});
