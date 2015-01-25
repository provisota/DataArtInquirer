<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Смена пароля</title>
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
    <h2>Смена пароля
        <a title="на домашнюю страницу" href="DataArtInquirer.html">DataArtInquirer</a>
    </h2>
    <p></p>
    <c:url var="changePassUrl" value="change_password.do"/>
    <form action="${changePassUrl}" method="post">
        <p style="color: red; font-size: medium">${requestScope.error_message}</p>
        <p style="color: green; font-size: medium">${requestScope.success_message}</p>
        <input type="hidden" id="confirm_id" name="confirm_id"
               value="${requestScope.confirm_id}">
        <%--<input type="hidden" id="callback_confirm_id" name="inputName"--%>
               <%--value="${requestScope.confirm_id}">--%>
        <fieldset>
            <p></p>
            <table>
                <tr>
                    <th><label for="password">Новый пароль </label></th>
                    <td><input style="margin-bottom: 5px"
                               title="должен содержать и цифры и буквы лат. алфавита
                               (6 - 15 символов)"
                               class="form-control" id="password"
                               name="password" placeholder="введите новый пароль"
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
                                type="submit">Подтвердить
                        </button>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
    <script type="text/javascript">
        document.getElementById('password').focus();
    </script>
</div>
</body>
</html>
