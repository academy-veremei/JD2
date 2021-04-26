<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <%@page contentType="text/html;charset=UTF-8"
            import="java.util.List , by.academy.bean.News" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to the NEWS</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/css/style.css">
    <!-- Локализация -->
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local"/>
</head>

<body style="background-color: #ECEFF4;">
<!--НАВБАР-->
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #3F5062;">
        <div class="container-md">
            <a class="navbar-brand" href="Controller?command=tomainpage">
                <img src="./resources/img/logo.png" class="img-fluid" alt="NEWS">
            </a>
            <c:if test="${sessionScope.locale eq \"en\"}">
                <a class="navbar-brand" href="Controller?command=change_locale&locale=ru">
                    <img src="./res/img/eng_lang.png" class="img-fluid" alt="RU">
                </a>
            </c:if>
            <c:if test="${sessionScope.locale eq \"ru\"}">
                <a class="navbar-brand" href="Controller?command=change_locale&locale=en">
                    <img src="./res/img/ru_lang.png" class="img-fluid" alt="EN">
                </a>
            </c:if>
            <div class="navbar-form ms-auto">
                <div class="helloCol" style="color: white">
                    <fmt:message key="hellouser.text"/>, <c:out value="${sessionScope.firstName}"/> ! <a
                        href="Controller?command=logout"><img
                        src="./resources/img/logout.png" class="img-fluid" alt="Logout"></a>
                </div>
            </div>
        </div>
    </nav>
</header>
<!--КОНЕЦ НАВБАРА-->
<div class="container-md pb-5">
    <!--РАЗМЕТКА BODY-->
    <!--Основная карточка-->
    <div class="col">
        <div class="card w-90 mb-4" style="background-color: rgba(0, 0, 0, 0.1);">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${requestScope.news.title}"/></h5>
                <p class="card-text"><c:out value="${requestScope.news.content}"/></p>
                <a href="Controller?command=tomainpage" class="btn btn-outline-light"
                   style="background-color: #3F5062; border: 0px;">
                    <fmt:message key="button.news.backtomain"/>
                </a>
                <c:if test="${sessionScope.role eq \"admin\"}">
                    <button button class="btn btn-outline-light" data-bs-toggle="modal"
                            data-bs-target="#editNews" style="background-color: #3F5062; border: 0px;">
                        <fmt:message key="button.news.edit"/>
                    </button>
                    <a href="Controller?command=deletenews&newsnum=${requestScope.news.id}"
                       class="btn btn-outline-light"
                       style="background-color: #3F5062; border: 0px;">
                        <fmt:message key="button.news.delete"/>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<!-- ФУТЕР -->
<footer class="text-center" style="background-color: #DEE3EA;">
    <div class="p-3">
        Веремей Д.Ю. MD-JD-2-74-21
    </div>
</footer>

<!-- Редактировать новость -->
<div class="modal fade" id="editNews" tabindex="-1" aria-labelledby="#editNewsLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-body">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="edit">
                    <input type="hidden" name="newsnum" value="${requestScope.news.id}">
                    <input type="hidden" name="newsdate" value="${requestScope.news.date}">
                    <div class="input-group mb-3">
                        <span class="input-group-text">
                            <fmt:message key="modify.news.title"/>
                        </span>
                        <input type="text" class="form-control" name="title" aria-label="Username" autocomplete="off"
                               aria-describedby="basic-addon1" value="${requestScope.news.title}">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">
                            <fmt:message key="modify.news.brief"/>
                        </span>
                        <textarea type="text" class="form-control" name="brief" aria-label="With textarea"
                                  autocomplete="off"
                                  aria-describedby="basic-addon1">${requestScope.news.brief}</textarea>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">
                            <fmt:message key="modify.news.content"/>
                        </span>
                        <textarea type="text" class="form-control" name="content" aria-label="With textarea"
                                  autocomplete="off"
                                  aria-describedby="basic-addon1">${requestScope.news.content}</textarea>
                    </div>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        <fmt:message key="button.cancel"/>
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="button.submit"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
        integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
        integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
        crossorigin="anonymous"></script>
</body>
</html>