document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('uploadForm').addEventListener('submit', function(event) {
        const fileInput = document.getElementById('fileInput');
        const file = fileInput.files[0];

        if (file.size > 2 * 1024 * 1024) {
            alert('File size must be less than 2MB.');
            event.preventDefault();
            return;
        }

        const validFormats = ['image/png', 'image/jpeg'];
        if (!validFormats.includes(file.type)) {
            alert('Invalid file format. Only PNG and JPG are allowed.');
            event.preventDefault();
        }
    });
});