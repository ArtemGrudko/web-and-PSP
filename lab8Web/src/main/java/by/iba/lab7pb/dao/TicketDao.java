package by.iba.lab7pb.dao;

import by.iba.lab7pb.model.Plane;
import by.iba.lab7pb.model.Ticket;
import by.iba.lab7pb.model.User;
import by.iba.lab7pb.service.Dao;
import by.iba.lab7pb.service.DaoImpl;
import by.iba.lab7pb.util.ConnectorDB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TicketDao {
    private final static String SQL_GET_TICKETS = "select * from tickets";
    private final static String SQL_ADD_TICKET = "INSERT INTO tickets(person,plane) VALUES (? , ?)";
    private final static String SQL_DELETE_BOOK = "DELETE FROM tickets WHERE num = ?";
    private final static String SQL_GET_TICKET = "SELECT * FROM tickets WHERE num =?";

    private Connection connection;
    public TicketDao(Connection connection) {
        if(connection!=null){
            this.connection = connection;
        }

    }


    public List<Ticket> getTickets() {
        List<Ticket> tickets = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_TICKETS);

            Ticket ticket = null;
            while (resultSet.next()) {
                ticket = new Ticket();

                ticket.setId(resultSet.getInt("num"));
                ticket.setIdPlane(resultSet.getInt("plane"));
                ticket.setIdPerson(resultSet.getInt("person"));

                tickets.add(ticket);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public boolean insertTicket(int idPlane, int idPerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_ADD_TICKET);
            preparedStatement.setInt(1, idPerson);
            preparedStatement.setInt(2, idPlane);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteBook(int id){
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BOOK);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket getTicket(int id) {
        Plane plane = new Plane();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_GET_TICKET);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Ticket(resultSet.getInt("num"), resultSet.getInt("person"), resultSet.getInt("plane"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
