<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Регистрация</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <!-- Bootstrap core CSS -->
    <link type="text/css" href="<c:url value="resources/css/bootstrap.min.css" />"
          rel="stylesheet">
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
<body>
<div align="center">
    <h2>Регистрация в DataArtInquirer</h2>

    <p></p>
    <c:url var="regUrl" value="/register"/>
    <form method="post" action="${regUrl}">
        <fieldset>
            <p style="color: red">${param.message}</p>
            <table>
                <tr>
                    <th><label for="username">Имя пользователя </label></th>
                    <td><input style="margin-bottom: 5px"
                               class="form-control" id="username"
                               name="username" placeholder="введите имя пользователя"
                               type="text"/> <!-- Поле ввода имени пользователя -->
                    </td>
                </tr>
                <tr>
                    <th><label for="email">E-mail </label></th>
                    <td><input style="margin-bottom: 5px"
                               class="form-control" id="email"
                               name="email" placeholder="введите пароль"
                               type="text"/> <!-- Поле ввода эл.почты -->
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Пароль </label></th>
                    <td><input style="margin-bottom: 5px"
                               class="form-control" id="password"
                               name="password" placeholder="введите пароль"
                               type="password"/> <!-- Поле ввода пароля -->
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Подтвердите пароль </label></th>
                    <td><input style="margin-bottom: 5px"
                               class="form-control" id="confirm_password"
                               name="confirm_password" placeholder="подтвердите пароль"
                               type="password"/> <!-- Подтверждение пароля -->
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button style="width: 200px; margin: auto auto 5px;"
                                class="btn btn-success btn-block" name="commit"
                                type="submit">Зарегестрироваться
                        </button>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <script type="text/javascript">
        document.getElementById('username').focus();
    </script>
</div>
</body>
</html>
