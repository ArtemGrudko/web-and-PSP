<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.06.2022
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <title>Title</title>
</head>
<body>
<div class ="container">
    <section id="content">
        <p><font color="red">${errorRegister}</font></p>

        <form action="RegisterServlet" method="POST">
            <h1> Регистрация нового пользователя </h1>
            <div>
                <input placeholder="Введите логин" required="" name="newLoginName" type="text" />
            </div>
            <div>
                <input placeholder="Введите пароль" id ="password" required="" name="newPassword" type="password" />
            </div>
            <div>
                <input placeholder="Введите Ваше имя" id ="name" required="" name="name" type="text" />
            </div>
            <div>
                <input placeholder="Введите телефон" id ="phone" required="" name="phone" type="tel" />
            </div>
            <div>
                <input placeholder="Введите email" id ="mail" required="" name="mail" type="email" />
            </div>
            <div>
                <input type="submit" value="Register"/>
            </div>
        </form>
    </section>
</div>
</body>
</html>
