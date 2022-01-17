<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.12.2021
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.12.2021
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="ru.job4j.dream.store.MemStore" %>
<%@ page import="ru.job4j.dream.store.DbStore" %>
<%@ page import="ru.job4j.dream.model.Post" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="ru.job4j.dream.store.Store" %>
<%@ page import="ru.job4j.dream.store.DbStore" %>

<%
    String id = request.getParameter("id");
    boolean remove = request.getParameter("remove") != null;
    Candidate candidate = new Candidate(0, "");
    if (id != null && remove) {
        candidate = DbStore.instOf().findCanById(Integer.valueOf(id));
        DbStore.instOf().remove(candidate);
        response.sendRedirect(request.getContextPath() + "/candidates.do");
    } else {
%>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>

    <title>Работа мечты</title>
</head>
<body>
<%
    /*String id = request.getParameter("id");
    Candidate candidate = new Candidate(0, "");*/
    if (id != null) {
        candidate = DbStore.instOf().findCanById(Integer.valueOf(id));
    }
%>
<div class="container pt-3">
    <%@include file="../header.jsp"%>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                Новый кандидат.
                <% } else { %>
                Редактирование кандидата.
                <% } %>
                <span id="hello"></span>
                <div class="row">
                    <ul class="nav">
                        <c:if test="${user != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath()%>/logout.do"> <c:out value="${user.name}"/> | Выйти</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <div class="card-body">
                <form class="formValidation" action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>" method="post">
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="name field" name="iName" value="<%=candidate.getName()%>">
                    </div>
                    <div class="form-group">
                        <label>Город</label>
                        <select class="city field" name="cityName" id="id_city">
                            <option selected>Город не выбран...</option>
                        </select>

                    </div>
                    <input type="submit" value="Сохранить" class="validateBtn">
                </form>
            </div>
        </div>
    </div>
</div>

<script>

    let form = document.querySelector('.formValidation');
    let validateBtn = form.querySelector('.validateBtn');
    let name = form.querySelector(".name");
    let city = form.querySelector(".city");
    let fields = form.querySelectorAll(".field");

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        let errors = form.querySelectorAll(".error");
        let err = false;

        for (let i = 0; i < errors.length; i++) {
            errors[i].remove();
        }

        for (let i = 0; i < fields.length; i++) {
            if (!fields[i].value) {
                err = true;
                let error = document.createElement("div");
                error.className = "error";
                error.style.color = "red";
                error.innerHTML = "Поле не может быть пустым";
                form[i].parentElement.insertBefore(error, fields[i]);
            }
        }

        if (!err) {
            form.submit();
        }

    })

    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: '<c:url value="/cities"/>',
            dataType: 'json'
        }).done(function (data) {
            for (var city of data) {
                if (<%=candidate.getCityId()%>==city.id ) {
                    $('#id_city option:last').after(`<option value="${city.id}" selected>${city.name}</option>`);
                } else {
                    $('#id_city option:last').after(`<option value="${city.id}">${city.name}</option>`);
                }
            }
            $('#id_city option:first').remove();
        }).fail(function (err) {
            $('#hello').text("Smth wrong");
        });
    });

</script>

</body>
</html>
<%
    }
%>