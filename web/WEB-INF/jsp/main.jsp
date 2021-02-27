<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@page contentType="text/html;charset=UTF-8" import="by.academy.bean.News , java.util.List" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to the NEWS</title>
    <!--Bootstrap и CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/css/style.css">
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
</head>

<body style="background-color: #ECEFF4;">
<%
    List<News> news = (List<News>) request.getAttribute("news");
%>
<!--НАВБАР-->
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #3F5062;">
        <div class="container-md">
            <a class="navbar-brand" href="Controller?command=tomainpage"><img src="./resources/img/logo.png"
                                                                              class="img-fluid" alt="NEWS"></a>
            <div class="navbar-form ms-auto">
                <!-- Кнопка входа либо приветствие -->
                <!-- Приветствие -->
                <c:if test="${sessionScope.auth == null}">
                    <button type="button" class="btn btn-outline-success" data-bs-toggle="modal"
                            data-bs-target="#loginationModal" style="background-color: #68FDBD; border: 0px;">
                        <div style="color: white;">Войти</div>
                    </button>
                </c:if>
                <c:if test="${sessionScope.auth == true}">
                    <div class="helloCol" style="color: white">
                        Здравствуйте, <c:out value="${user.firstName}"/>! <a href="Controller?command=logout"><img
                            src="./resources/img/logout.png" class="img-fluid" alt="Logout"></a>
                    </div>
                </c:if>
                <form class="d-flex">
                    <!-- Модальное окно "Вход" -->
                    <div class="modal fade" id="loginationModal" tabindex="-1" aria-labelledby="loginationModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="loginationModalLabel">Войти на сайт</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <!--Форма логин_пароль-->
                                    <form action="Controller" method="post" class="logination p-1">
                                        <input type="hidden" name="command" value="logination">
                                        <div class="mb-2">
                                            <input type="text" name="login" class="form-control" id="loginLab"
                                                   placeholder="Введите логин" autocomplete="off">
                                        </div>
                                        <div class="mb-2">
                                            <input type="password" name="password" class="form-control" id="passwordLab"
                                                   placeholder="Введите пароль" autocomplete="off">
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена
                                        </button>
                                        <!--Кнопка регистрации-->
                                        <button class="btn btn-info" data-bs-dismiss="modal" id="Regstarter"
                                                onclick="Regform()">Регистрация
                                        </button>
                                        <button type="submit" class="btn btn-primary">Войти</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </nav>
</header>
<!--КОНЕЦ НАВБАРА-->
<div class="container-md pb-5">
    <!--РАЗМЕТКА BODY-->
    <!-- Вывод сообщений -->
    <c:if test="${requestScope.message != null}">
        <c:set var="str" scope="session" value="${requestScope.message}"/>
        <c:out value="${str}" escapeXml="false"/>
    </c:if>
    <!--Первая строка-->
    <!-- <div class="row pt-4"> -->
        <!--Карточка 1-->
        <div class="row pt-1">
            <div class="card w-90 mb-2" style="background-color: rgba(0, 0, 0, 0.1);">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${news[0].title}"/></h5>
                    <p class="card-text"><c:out value="${news[0].brief}"/></p>
                    <c:if test="${sessionScope.auth == true}"> <a
                            href="Controller?command=tonewspage&newsnum=${news[0].id}" class="btn btn-outline-light"
                            style="background-color: #3F5062; border: 0px;">Перейти к статье хоба</a></c:if>
                    <c:if test="${sessionScope.auth == null}">
                        <button type="button" class="btn btn-outline-light" data-bs-toggle="modal"
                                data-bs-target="#needToLoginModal" style="background-color: #3F5062; border: 0px;">
                            <div style="color: white;">Перейти к статье 222</div>
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
        <!--Карточка 2-->
        <div class="row pt-1">
            <div class="card w-90 mb-2" style="background-color: rgba(0, 0, 0, 0.1);">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${news[1].title}"/></h5>
                    <p class="card-text"><c:out value="${news[1].brief}"/></p>
                    <c:if test="${sessionScope.auth == true}"> <a
                            href="Controller?command=tonewspage&newsnum=${news[1].id}" class="btn btn-outline-light"
                            style="background-color: #3F5062; border: 0px;">Перейти к статье хоба</a></c:if>
                    <c:if test="${sessionScope.auth == null}">
                        <button type="button" class="btn btn-outline-light" data-bs-toggle="modal"
                                data-bs-target="#needToLoginModal" style="background-color: #3F5062; border: 0px;">
                            <div style="color: white;">Перейти к статье 222</div>
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
        <!--Карточка 3-->
        <div class="row pt-1">
            <div class="card w-90 mb-2" style="background-color: rgba(0, 0, 0, 0.1);">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${news[2].title}"/></h5>
                    <p class="card-text"><c:out value="${news[2].brief}"/></p>
                    <c:if test="${sessionScope.auth == true}"> <a
                            href="Controller?command=tonewspage&newsnum=${news[2].id}" class="btn btn-outline-light"
                            style="background-color: #3F5062; border: 0px;">Перейти к статье хоба</a></c:if>
                    <c:if test="${sessionScope.auth == null}">
                        <button type="button" class="btn btn-outline-light" data-bs-toggle="modal"
                                data-bs-target="#needToLoginModal" style="background-color: #3F5062; border: 0px;">
                            <div style="color: white;">Перейти к статье 222</div>
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
    <!-- </div> -->
    <div class="row"></div>
</div>

<!-- Модальное окно "Ошибка нужно авторизироваться" -->
<div class="modal fade" id="needToLoginModal" tabindex="-1" aria-labelledby="needToLoginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="needToLoginModalLabel">Ошибка!</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Для просмотра полной новости необходимо авторизироваться!
            </div>
        </div>
    </div>
</div>
<!-- ФОРМА РЕГИСТРАЦИИ-->
<div class="modal fade" id="registrationModal" tabindex="-1" aria-labelledby="registrationModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registrationModalLabel">Регистрация</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="save_new_user">
                    <div class="input-group mb-3">
                        <span class="input-group-text">Логин</span>
                        <input type="text" autocomplete="off" name="login" class="form-control" placeholder="user123"
                               aria-label="Username"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Пароль</span>
                        <input type="text" autocomplete="off" name="password" class="form-control"
                               placeholder="qwerty123" aria-label="Username"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Имя и фамилия</span>
                        <input type="text" autocomplete="off" name="firstName" aria-label="First name"
                               placeholder="Иван" class="form-control">
                        <input type="text" autocomplete="off" name="lastName" aria-label="Last name"
                               placeholder="Иванов" class="form-control">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Электронная почта</span>
                        <input type="text" autocomplete="off" name="email" class="form-control" placeholder="Username"
                               aria-label="Username"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="text-center p-3">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                        <button type="submit" class="btn btn-success">Подтвердить</button>
                    </div>
                </form>
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
</body>

</html>