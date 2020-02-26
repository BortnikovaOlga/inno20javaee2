
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>

<body>
<div style="text-align: center">
    <h1>Login</h1>
    <form action="${pageContext.request.contextPath}/loginservlet" method="post" autocomplete="off">
        <label for="login">Логин</label>
        <input id = "login" name="login" size="30" />
        <br><br>
        <label for="password">Пароль</label>
        <input id="password" type="password" name="password" size="30" />
        <br>${message}
        <br><br>
        <button type="submit">Login</button>
    </form>
</div>
</body>

</html>
