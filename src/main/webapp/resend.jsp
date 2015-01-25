<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Восстановление пароля</title>
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
    <h2>Восстановление пароля
        <a title="на домашнюю страницу" href="DataArtInquirer.html">DataArtInquirer</a>
    </h2>

    <p></p>
    <c:url var="resendUrl" value="resend.do"/>
    <form action="${resendUrl}" method="post">
        <p style="color: red; font-size: medium">${requestScope.error_message}</p>
        <p style="color: green; font-size: medium">${requestScope.success_message}</p>
        <fieldset>
            <p></p>
            <table>
                <tr>
                    <th><label for="email">E-mail </label></th>
                    <td><input style="margin-bottom: 5px; width: 260px"
                               title="введите email под которым вы регистрировались"
                               class="form-control" id="email"
                               name="email" placeholder="введите регистрационную эл.почту"
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
                    <th></th>
                    <td>
                        <button style="width: 200px; margin: auto auto 5px;"
                                class="btn btn-success btn-block" name="commit"
                                type="submit">Отправить
                        </button>
                    </td>
                </tr>
            </table>
            <%-- TODO Здесь будет капча --%>
        </fieldset>
    </form>
    <script type="text/javascript">
        document.getElementById('email').focus();
    </script>
</div>
</body>
</html>
