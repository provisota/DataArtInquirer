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
        border: 1px solid lightgray;
        border-radius: 4px
    }
</style>
<body>
<div align="center">
    <h2>Регистрация в
        <a title="на домашнюю страницу" href="/dataartinquirer/">DataArtInquirer</a>
    </h2>

    <p></p>
    <c:url var="regUrl" value="register.do"/>
    <form action="${regUrl}" method="post">
        <p style="color: green; font-size: medium">${requestScope.success_message}</p>
        <fieldset>
            <p></p>
            <table>
                <tr>
                    <th><label for="username">Имя пользователя </label></th>
                    <td><input style="margin-bottom: 5px"
                               title="может содержать только цифры и буквы лат. алфавита
                               (6 - 15 символов)"
                               class="form-control" id="username"
                               name="username" placeholder="введите имя пользователя"
                               type="text" value="${requestScope.username}"/>
                        <!-- Поле ввода имени пользователя -->
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <p style="color: red; margin: auto auto 5px">
                            ${requestScope.error_username}
                        </p>
                    </td>
                </tr>
                <tr>
                    <th><label for="email">E-mail </label></th>
                    <td><input style="margin-bottom: 5px"
                               title="введите реальный email для подтверждения регистрации"
                               class="form-control" id="email"
                               name="email" placeholder="введите эл.почту"
                               type="text" value="${requestScope.email}"/>
                        <!-- Поле ввода эл.почты -->
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <p style="color: red; margin: auto auto 5px">
                            ${requestScope.error_email}
                        </p>
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Пароль </label></th>
                    <td><input style="margin-bottom: 5px"
                               title="должен содержать и цифры и буквы лат. алфавита
                               (6 - 15 символов)"
                               class="form-control" id="password"
                               name="password" placeholder="введите пароль"
                               type="password" value="${requestScope.password}"/>
                        <!-- Поле ввода пароля -->
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <p style="color: red; margin: auto auto 5px">
                            ${requestScope.error_password}
                        </p>
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Подтвердите пароль </label></th>
                    <td><input style="margin-bottom: 5px"
                               title="должен содержать и цифры и буквы лат. алфавита
                               (6 - 15 символов)"
                               class="form-control" id="confirm_password"
                               name="confirm_password" placeholder="подтвердите пароль"
                               type="password" value="${requestScope.confirm_password}"/>
                        <!-- Подтверждение пароля -->
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <p style="color: red; margin: auto auto 5px">
                            ${requestScope.error_confirm_password}
                        </p>
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
