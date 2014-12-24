<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <!-- Bootstrap core CSS -->
    <%--<link href="<c:url value="bootstrap.min.css" />" rel="stylesheet">--%>
</head>
<style>
    form {
        width: 500px;
        margin-top: 100px !important;

    }
    fieldset {
        border-radius: 4px
    }
</style>
<div align="center">
    <h2>Добро пожаловать в DataArtInquirer</h2>

    <p></p>
    <!-- Путь к фильтру аутентификации -->
    <c:url var="authUrl" value="/static/j_spring_security_check"/>
    <form method="post" action="${authUrl}">
        <fieldset>
            <p style="color: red">${param.message}</p>
            <table cellspacing="0">
                <tr>
                    <th><label for="username_or_email">Имя пользователя </label></th>
                    <td><input class="form-control" id="username_or_email"

                               name="j_username"
                               type="text"/> <!-- Поле ввода имени пользователя -->
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Пароль </label></th>
                    <td><input class="form-control" id="password"
                               name="j_password"
                               type="password"/> <!-- Поле ввода пароля -->
                        <small><a href="/account/resend_password">Забыли?</a></small>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input id="remember_me"
                               name="_spring_security_remember_me"
                               type="checkbox"/> <!-- Флажок "запомнить меня" -->
                        <label for="remember_me"
                               class="inline">запомнить меня</label></td>
                </tr>
                <tr>
                    <th></th>
                    <td><button class="btn btn-lg btn-primary btn-block" name="commit"
                                type="submit">Войти</button></td>
                </tr>
            </table>
        </fieldset>
    </form>
    <script type="text/javascript">
        document.getElementById('username_or_email').focus();
    </script>
</div>
</html>