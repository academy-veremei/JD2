<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <%@page contentType="text/html;charset=UTF-8" import="java.util.List, by.academy.bean.News" %>
    <%@ page import="by.academy.bean.User" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to the NEWS</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/css/style.css">
</head>

<body style="background-color: #ECEFF4;">
<%
    List<News> news = (List<News>) request.getAttribute("news");
    User user = (User) session.getAttribute("user");
    String message = (String) request.getAttribute("message");
%>
<!--НАВБАР-->
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #3F5062;">
        <div class="container-md">
            <a class="navbar-brand" href="Controller?command=toauthpage"><img src="./resources/img/logo.png"
                                                                              class="img-fluid" alt="NEWS"></a>
            <div class="navbar-form ms-auto">
                <div class="helloCol" style="color: white">
                    Здравствуйте, <c:out value="${user.firstName}"/>! <a href="Controller?command=logout"><img
                        src="./resources/img/logout.png" class="img-fluid" alt="Logout"></a>
                </div>
            </div>
        </div>
    </nav>
</header>
<!--КОНЕЦ НАВБАРА-->
<div class="container-md pb-5">
    <!--РАЗМЕТКА BODY-->
    <!-- Сообщение -->
    <c:if test="${message != null}">
        <c:set var = "str" scope = "session" value = "${message}"/>
        <c:out value="${str}" escapeXml="false"/>
    </c:if>
    <!--Первая строка-->
    <div class="row">
        <div class="text-center p-3">
            <img src="./resources/img/top3.png" class="img-fluid" alt="Топ 3">
        </div>
    </div>
    <!--Вторая строка-->
    <div class="row">
        <!--Карточка 1-->
        <div class="col">
            <div class="card w-90 mb-4" style="background-color: rgba(0, 0, 0, 0.1);">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${news[0].title}"/></h5>
                    <p class="card-text"><c:out value="${news[0].brief}"/></p>
                    <a href="Controller?command=tonewspage&newsnum=${news[0].id}" class="btn btn-outline-light"
                       style="background-color: #3F5062; border: 0px;">Перейти к статье</a>
                </div>
            </div>
        </div>
        <!--Карточка 2-->
        <div class="col">
            <div class="card w-90 mb-4" style="background-color: rgba(0, 0, 0, 0.1);">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${news[1].title}"/></h5>
                    <p class="card-text"><c:out value="${news[1].brief}"/></p>
                    <a href="Controller?command=tonewspage&newsnum=${news[1].id}" class="btn btn-outline-light"
                       style="background-color: #3F5062; border: 0px;">Перейти к статье</a>
                </div>
            </div>
        </div>
        <!--Карточка 3-->
        <div class="col">
            <div class="card w-90 mb-4" style="background-color: rgba(0, 0, 0, 0.1);">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${news[2].title}"/></h5>
                    <p class="card-text"><c:out value="${news[2].brief}"/></p>
                    <a href="Controller?command=tonewspage&newsnum=${news[2].id}" class="btn btn-outline-light"
                       style="background-color: #3f5062; border: 0px;">Перейти к статье</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ФУТЕР -->
<footer class="fixed-bottom expand-lg" style="background-color: #3F5062;">
    <div class="row p-3 text-center">
        <div class="col-12 col-md">
            <div class="textCol" style="color: white;">
                Веремей Д.Ю. MD-JD-2-74-21
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
        integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
        integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
        crossorigin="anonymous"></script>
</body>

</html>