<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@page contentType="text/html;charset=UTF-8" import="by.academy.bean.News" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to the NEWS</title>
    <!--Bootstrap и CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/css/style.css">
    <!-- Локализация -->
    <c:set var="defaultLocale" value="en"/>
    <fmt:setLocale value="${sessionScope.locale == null ? defaultLocale : sessionScope.locale}"/>
    <fmt:setBundle basename="local"/>
    <!-- Локализация плейсхолдеров -->
    <fmt:message key="logination.login.placeholder" var="loginph"/>
    <fmt:message key="logination.pass.placeholder" var="passph"/>
    <fmt:message key="registration.input.placeholder.email" var="regemailph"/>
    <fmt:message key="registration.input.placeholder.login" var="regloginph"/>
    <fmt:message key="registration.input.placeholder.pass" var="regpassph"/>
    <fmt:message key="registration.input.placeholder.fname" var="regfnameph"/>
    <fmt:message key="registration.input.placeholder.lname" var="reglnameph"/>
    <fmt:message key="modify.news.placeholder.brief" var="briefph"/>
    <fmt:message key="modify.news.placeholder.title" var="titleph"/>
    <fmt:message key="modify.news.placeholder.content" var="contentph"/>
</head>
<body style="background-color: #ECEFF4;">
<!--НАВБАР-->
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #3F5062;">
        <div class="container-md">
            <a class="navbar-brand" href="Controller?command=tomainpage">
                <img src="./res/img/logo.png" class="img-fluid" alt="NEWS">
            </a>
            <c:if test="${sessionScope.locale == null || sessionScope.locale eq \"en\"}">
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
                <!-- Кнопка входа либо приветствие -->
                <!-- Приветствие -->
                <c:if test="${sessionScope.auth == null}">
                    <button type="button" class="btn btn-outline-success" data-bs-toggle="modal"
                            data-bs-target="#loginationModal" style="background-color: #68FDBD; border: 0px;">
                        <div style="color: white;">
                            <fmt:message key="local.button.login"/>
                        </div>
                    </button>
                </c:if>
                <c:if test="${sessionScope.auth == true}">
                    <div class="helloCol" style="color: white">
                        <fmt:message key="hellouser.text"/>, <c:out value="${sessionScope.firstName}"/>! <a
                            href="Controller?command=logout"><img
                            src="./resources/img/logout.png" class="img-fluid" alt="Logout"></a>
                    </div>
                </c:if>
            </div>
        </div>
    </nav>
</header>
<!--КОНЕЦ НАВБАРА-->
<div class="container-md pb-5">
    <!--РАЗМЕТКА BODY-->
    <!-- Вывод сообщений -->
    <c:if test="${sessionScope.message != null}">
        <div class="row">
            <div class="alert alert-info alert-dismissible fade show" role="alert">
                <c:out value="${sessionScope.message}"/>
                <c:remove var="message"/>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </div>
    </c:if>
    <!-- НОВОСТИ -->
    <!-- Построчный вывод карточек с новостями -->
    <c:forEach var="someNews" items="${requestScope.news}">
        <div class="row pt-1">
            <div class="card w-90 mb-2" style="background-color: rgba(0, 0, 0, 0.1);">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${someNews.value.title}"/></h5>
                    <p class="card-text"><c:out value="${someNews.value.brief}"/></p>
                    <c:if test="${sessionScope.auth == true}"> <a
                            href="Controller?command=tonewspage&newsID=${someNews.value.id}"
                            class="btn btn-outline-light"
                            style="background-color: #3F5062; border: 0px;">
                        <fmt:message key="button.tonews"/>
                    </a></c:if>
                    <c:if test="${sessionScope.auth == null}">
                        <button type="button" class="btn btn-outline-light" data-bs-toggle="modal"
                                data-bs-target="#needToLoginModal" style="background-color: #3F5062; border: 0px;">
                            <div style="color: white;">
                                <fmt:message key="button.tonews"/>
                            </div>
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
    <!-- Добавить новость -->
    <c:if test="${sessionScope.role eq \"admin\"}">
        <div class="row">
            <div class="text-center mt-2">
                <button type="button" class="btn btn-outline-light" data-bs-toggle="modal"
                        data-bs-target="#addNewsModal" style="background-color: #3F5062; border: 0px;">
                    <div style="color: white;">
                        <fmt:message key="button.news.add"/>
                    </div>
                </button>
            </div>
        </div>
    </c:if>
</div>
<!-- МОДАЛЬНЫЕ ОКНА -->
<!-- Окно "Добавить новость" -->
<div class="modal fade" id="addNewsModal" tabindex="-1" aria-labelledby="#addNewsLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-body">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="addnews">
                    <div class="input-group mb-3">
                        <span class="input-group-text">
                            <fmt:message key="modify.news.title"/>
                        </span>
                        <input type="text" class="form-control" name="title" aria-label="Username" autocomplete="off"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">
<fmt:message key="modify.news.brief"/>
                        </span>
                        <textarea type="text" class="form-control" name="brief" aria-label="With textarea"
                                  autocomplete="off" aria-describedby="basic-addon1"></textarea>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">
                            <fmt:message key="modify.news.content"/>
                        </span>
                        <textarea type="text" class="form-control" name="content" aria-label="With textarea"
                                  autocomplete="off" aria-describedby="basic-addon1"></textarea>
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
<!-- Окно "Ошибка нужно авторизироваться" -->
<div class="modal fade" id="needToLoginModal" tabindex="-1" aria-labelledby="needToLoginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="needToLoginModalLabel"><fmt:message key="needlogin.title"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <fmt:message key="needlogin.text"/>
            </div>
        </div>
    </div>
</div>
<!-- Окно логинации -->
<div class="modal fade" id="loginationModal" tabindex="-1" aria-labelledby="loginationModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginationModalLabel">
                    <fmt:message key="logination.title"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!--Форма логин_пароль-->
                <form action="Controller" method="post" class="logination p-1">
                    <input type="hidden" name="command" value="logination">
                    <div class="mb-2">
                        <input type="text" name="login" class="form-control" id="loginLab"
                               placeholder="${loginph}" autocomplete="off">
                    </div>
                    <div class="mb-2">
                        <input type="password" name="password" class="form-control" id="passwordLab"
                               placeholder="${passph}" autocomplete="off">
                    </div>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        <fmt:message key="button.cancel"/>
                    </button>
                    <!--Кнопка регистрации-->
                    <button class="btn btn-info" data-bs-dismiss="modal" id="Regstarter"
                            onclick="Regform()">
                        <fmt:message key="button.registration"/>
                    </button>
                    <button type="submit" class="btn btn-primary" id="submit">
                        <fmt:message key="button.submit"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Окно регистрации-->
<div class="modal fade" id="registrationModal" tabindex="-1" aria-labelledby="registrationModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registrationModalLabel">
                    <fmt:message key="registration.title"/>
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="save_new_user">
                    <div class="input-group mb-3">
                        <span class="input-group-text">
                            <fmt:message key="registration.input.text.login"/>
                        </span>
                        <input type="text" autocomplete="off" name="login" class="form-control"
                               placeholder="${regloginph}"
                               aria-label="Username"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><fmt:message key="registration.input.text.pass"/></span>
                        <input type="text" autocomplete="off" name="password" class="form-control"
                               placeholder="${regpassph}" aria-label="Username"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><fmt:message key="registration.input.text.name"/></span>
                        <input type="text" autocomplete="off" name="firstName" aria-label="First name"
                               placeholder="${regfnameph}" class="form-control">
                        <input type="text" autocomplete="off" name="lastName" aria-label="Last name"
                               placeholder="${reglnameph}" class="form-control">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><fmt:message key="registration.input.text.email"/></span>
                        <input type="text" autocomplete="off" name="email" class="form-control"
                               placeholder="${regemailph}"
                               aria-label="Username"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="text-center p-3">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                key="button.cancel"/></button>
                        <button type="submit" class="btn btn-success"><fmt:message key="button.submit"/></button>
                    </div>
                </form>
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
<!-- СКРИПТЫ -->
<!-- Скрипты для Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
        integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
        integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
        crossorigin="anonymous"></script>
<!--Открытие окна регистрации по нажатию кнопки-->
<script>
    function Regform() {
        const firstM = document.getElementById('Regstarter')

        var myModal = new bootstrap.Modal(document.getElementById("registrationModal"), {});
        myModal.show();
    }
</script>
<!--Очистка инпутов при закрытии окна-->
<script>
    const loginModal = document.getElementById('loginationModal')
    loginModal.addEventListener('hidden.bs.modal', function (event) {
        const input = document.getElementById("loginationModal")
        const pass = document.getElementById("passwordLab")
        const log = document.getElementById("loginLab")
        pass.value = ""
        log.value = ""
    })
</script>
</body>
</html>