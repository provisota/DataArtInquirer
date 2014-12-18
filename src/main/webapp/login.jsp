<%--
  Created by IntelliJ IDEA.
  User: Илья
  Date: 22.11.2014
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    form {
        width: 500px;
        margin-top: 100px !important;
    }
</style>
<div align="center">
    <h2>Sign in to DataArtInquier</h2>
    <p></p>
    <!-- Путь к фильтру аутентификации -->
    <c:url var="authUrl" value="/static/j_spring_security_check" />
    <form method="post" class="signin" action="${authUrl}">
        <fieldset>
            <p style="color: red">${param.message}</p>
            <table cellspacing="0">
                <tr>
                    <th><label for="username_or_email">Username or Email</label></th>
                    <td><input id="username_or_email"

                               name="j_username"
                               type="text" /> <!-- Поле ввода имени пользователя -->
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Password</label></th>
                    <td><input id="password"
                               name="j_password"
                               type="password" /> <!-- Поле ввода пароля -->
                        <small><a href="/account/resend_password">Forgot?</a></small>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input id="remember_me"
                               name="_spring_security_remember_me"
                               type="checkbox"/> <!-- Флажок "запомнить меня" -->
                        <label for="remember_me"
                               class="inline">Remember me</label></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit" value="Sign In" /></td>
                </tr>
            </table>
        </fieldset>
    </form>
    <script type="text/javascript">
        document.getElementById('username_or_email').focus();
    </script>
</div>