class ServiceXHR {
    del(url, id) {
        let request = new XMLHttpRequest();
        request.open("DELETE", url, true);
        request.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
        request.onreadystatechange = function () {
            if (request.status === 200 && request.readyState === 4) {
                let btn = document.getElementById(id);
                let cur = btn.parentNode.parentNode;
                cur.parentNode.removeChild(cur);
            }
        };
        request.send();
    }

    post(url, data) {
        let request = new XMLHttpRequest();
        request.open("POST", url, true);
        request.setRequestHeader("Content-Type", 'application/json; charset=utf-8');
        request.onreadystatechange = function (e) {
            if (request.status === 200 && request.readyState === 4) {
                let taskJSON = JSON.parse(request.responseText);
                let hiddenelem = document.getElementById("hiddenetr");
                let copy = hiddenelem.cloneNode(true);
                copy.removeAttribute("hidden");
                copy.removeAttribute("id");
                let tds = copy.children;
                tds[0].innerHTML = taskJSON.id;
                tds[1].innerHTML = taskJSON.title;
                tds[2].innerHTML = taskJSON.description;
                let btn = tds[3].getElementsByClassName('del')[0];
                btn.value = taskJSON.id;
                btn.id = taskJSON.id;
                let btnclick = btn;
                btn.addEventListener("click", function (e) {
                    new ServiceXHR().del("/api/1.0/" + this.id, this.id);
                    e.preventDefault();
                }, false);
                let par = hiddenelem.parentElement;
                par.insertBefore(copy, hiddenelem);
            }
        };
        request.send(data);
    }
}