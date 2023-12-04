<%@ page import="by.iba.lab7pb.model.Ticket" %>
<%@ page import="by.iba.lab7pb.dao.TicketDao" %>
<%@ page import="java.util.List" %>
<%@ page import="by.iba.lab7pb.service.Dao" %>
<%@ page import="by.iba.lab7pb.service.DaoImpl" %>
<%@ page import="by.iba.lab7pb.model.Plane" %>
<%@ page import="by.iba.lab7pb.dao.PersonDao" %>
<%@ page import="by.iba.lab7pb.model.Person" %>
<%@ page import="java.io.IOException" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/MainFrozen.css">
    <title>Admin page</title>
    <!-- Bootstrap core CSS -->
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"> </head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

<body>
<header>
    <nav role="navigation" class="navbar navbar-default" style="float: left">
        <div class="navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="LoginServlet">Login</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="LogoutServlet">Logout</a></li>
            </ul>
        </div>
    </nav>
    <H2 style="position: absolute; margin-left: 40%; margin-right: 50%; width: max-content; color: coral">Welcome admin ${name}</H2>
</header>
<section id="main" style="box-sizing: content-box; min-height: 60%;">
    <section id="tickets" style="margin-left: 30px; position: relative; float: left; box-sizing: content-box; height: max-content; ">
        <%! private Dao dao = new DaoImpl(); %>
        <%! private List<Ticket> tickets; %>
        <H2>Список брони билетов</H2>
        <table border="4" cellspacing="2">
            <tr>
                <td>Имя</td>
                <td>email</td>
                <td>Номер рейса</td>
                <td>Дата вылета</td>
                <td>Компания</td>
                <td>Отмена брони</td>
            </tr>
            <%
                tickets = dao.getTickets();
                if(tickets!=null) {
                    for (Ticket ticket : tickets) {
                        Plane plane = dao.getPlane(ticket.getIdPlane());
                        Person person = dao.getPerson(ticket.getIdPerson());
                        out.println("<tr id='"+ticket.getId()+"'><td>" + person.getName() + "</td><td>" + person.getEmail() + "</td><td>" + plane.getNum()
                                + "</td><td>" + plane.getDate() + "</td><td>" + plane.getCompany() + "</td><td><button id='deleteBooking'" +
                                " value='"+ticket.getId()+"'>Отмена</button></td></tr>");

                    }
                }
            %>
        </table>
    </section>
    <section style="display: inline-block; margin-right: 30px; width: 40%; position: relative; margin-right: 0;box-sizing: border-box;">
        <div style="position: absolute; right: 30px">
            <H2>Добавить новый рейс</H2>
            <form style="position: relative" method="POST" action="GroupListServlet">
                <table>
                    <tr>
                        <td><label> Введите номер рейса</label></td>
                        <td><input name="num" type="text" required /></td>
                    </tr>
                    <tr>
                        <td><label> Введите компанию перевозчика</label></td>
                        <td><input name="comp" type="text" required/></td>
                    </tr>
                    <tr>
                        <td><label> Введите город вылета</label></td>
                        <td><input name="cityFrom" type="text" required/></td>
                    </tr>
                    <tr>
                        <td><label> Введите город прилета</label></td>
                        <td><input name="cityTo" type="text" required/></td>
                    </tr>
                    <tr>
                        <td><label> Введите дату и время вылета</label></td>
                        <td><input name="date" type="text" required/></td>
                    </tr>
                    <tr>
                        <td><label> Введите количество мест</label></td>
                        <td><input name="tickets" type="number" required/></td>
                    </tr>
                </table> <button style="position: absolute; bottom: -40px; left: 20px;" name="add" type="submit">Добавить рейс</button> </form>
        </div>
    </section>
</section>
<dialog class="modal">
    <p><button id="closeDialog">X</button></p>
    <p id="check"></p>
    <form action="delete" method="POST"> <input id="confirm" name="id" value="" type="hidden" /> <button type="submit">Подтвердить</button> </form>
</dialog>
<footer class="footer">
    <section class="konez">
        <a class="konez2" href="https://na.wotblitz.com/content/docs/system-requirements/">Системные требования World of Tanks Blitz</a>
        <a class="konez2" href="https://worldoftanks.ru/ru/content/docs/download/">Системные требования World of Tanks</a>
        <a class="konez2" href="https://na.wotblitz.com/ru/">Официальный сайт Мира Танков</a>
        <a class="konez2" href="https://ru.wargaming.net/ru">Официальный сайт Lesta.ru</a>
    </section>
    <section class="konez3">
        <div class="konez3">Сайт создан с помощью информации из </div><a class="konez3" href="http://htmlbook.ru/">HTMLBook</a>
        <a class="konez3" href="steam://rungameid/444200">Играть в Tanks Blitz</a>
        <a class="konez3" href="file:///D:/Games/WoT/World_of_Tanks_RU/wgc_api.exe">Играть в World of Tanks</a>
    </section>

    <div class="Polny_Konez">
        2003-2023 Eugen&Co. Powered by <a class="konez4" href="https://ru.wargaming.net/ru">Lesta.org</a> ©<br>Никакие права не защищены. Не воруйте пожалуйта мой сайт!
    </div>
</footer>
<script>
    var dialog = document.querySelector('dialog');
    document.querySelectorAll('#deleteBooking').forEach(b => b.addEventListener('click', checking));
    function checking(e) {
        var id = this.value;
        var check = document.querySelector('#check');
        var planes = document.getElementById(id);
        planes = planes.childNodes;
        check.textContent = "Вы подтверждаете удаление билета пользователя "+planes[0].textContent+" на рейс " + planes[2].textContent +" вылет в " +planes[3].textContent +"?";
        document.querySelector("#confirm").value=id;
        dialog.show(); // Показываем диалоговое окно
    }
    document.querySelector('#closeDialog').onclick = function() {
        dialog.close(); // Прячем диалоговое окно
    }
</script>
<script>
    var ticketsHeight = $('#tickets').height();
    var main = document.querySelector("#main");
    console.log(ticketsHeight);
    main.style.height = ticketsHeight;
</script>
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
