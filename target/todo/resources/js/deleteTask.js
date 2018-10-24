window.addEventListener("load", function (ev) {

    let b = document.getElementsByClassName("del");

    for (let i = 0; i < b.length; i++) {
        b[i].addEventListener("click", function (e) {
            new ServiceXHR().del("/api/1.0/" + b[i].value, b[i].value);
            e.preventDefault();
        }, false);
    }
});

