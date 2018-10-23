<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>

<html>
<body>
<h2>To Do</h2>
<table>
    <tr>
        <th>#</th>
        <th>Title</th>
        <th>Description</th>
        <th></th>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>
                <button id="${task.id}" value="${task.id}" class="del">Delete</button>
            </td>
        </tr>
    </c:forEach>
    <tr id="hiddenetr" hidden>
        <td></td>
        <td></td>
        <td></td>
        <td>
            <button class="del">Delete</button>
        </td>
    </tr>
</table>
<form method="post">
    <input value="" name="title" id="title" type="text" placeholder="Enter here..."/></br>
    <input value="" name="description" id="description" type="text" placeholder="Enter here..."/></br>
    <button id="add" type="submit">Add</button>
    <br/>
</form>
<script src="../../resources/js/ServiceXHR.js" type="text/javascript"></script>
<script src="../../resources/js/TaskDTO.js" type="text/javascript"></script>
<script src="../../resources/js/addTask.js" type="text/javascript"></script>
<script src="../../resources/js/deleteTask.js" type="text/javascript"></script>
</body>
</html>
