window.addEventListener("load", function (ev) {

    document.getElementById("add").addEventListener("click", add);

    function add(e) {
        let data = JSON.stringify(new TaskDTO(0, document.getElementById("title").value,
            document.getElementById("description").value, false));
        new ServiceXHR().post("/api/1.0/", data);
        e.preventDefault();
    }
});