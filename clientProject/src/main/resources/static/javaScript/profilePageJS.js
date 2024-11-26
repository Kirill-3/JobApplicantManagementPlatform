document.addEventListener("DOMContentLoaded", function() {
    const tableRows = document.querySelectorAll(".tableClickable tbody tr");
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
            } else {
                window.location.href = this.getAttribute("data1-href") + this.getAttribute("data2-href");
            }

        }
    )}
});
