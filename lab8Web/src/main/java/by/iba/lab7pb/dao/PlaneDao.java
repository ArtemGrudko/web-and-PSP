package by.iba.lab7pb.dao;

import by.iba.lab7pb.model.Plane;
import by.iba.lab7pb.util.ConnectorDB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PlaneDao {
    private final static String SQL_GET_PLANES = "select * from plane";
    private final static String SQL_UPDATE_PLANES = "UPDATE `plane` set ticketsNotBooked = ? where id = ?";
    private final static String SQL_GET_PLANE = "SELECT * FROM plane WHERE id =?";
    private final static String SQL_INSERT_PLANE = "INSERT INTO plane(num, company, cityFrom, cityTo, date, ticketsAll, ticketsNotBooked) VALUES (?,?,?,?,?,?,?)";
    private Connection connection;
    public PlaneDao(Connection connection) {
        this.connection = connection;
    }

    public List<Plane> getPlanes() {
        List<Plane> planes = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_PLANES);

            Plane plane = null;
            while (resultSet.next()) {
                plane = new Plane();

                setPlane(plane, resultSet);

                planes.add(plane);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planes;
    }
    public void updatePlanesTickets(int id, boolean plus){
        Plane plane = getPlane(id);
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_PLANES);
            if(plus) {
                ps.setInt(1, plane.getTicketsNotBooked() -1);
            }else  ps.setInt(1, plane.getTicketsNotBooked()+1);
            ps.setInt(2, plane.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Plane getPlane(int id) {
        Plane plane = new Plane();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_GET_PLANE);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                setPlane(plane, resultSet);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }

    public void insertPlane(Plane plane) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_INSERT_PLANE);
            preparedStatement.setString(1,plane.getNum());
            preparedStatement.setString(2,plane.getCompany());
            preparedStatement.setString(3,plane.getCityFrom());
            preparedStatement.setString(4,plane.getCityTo());
            preparedStatement.setString(5,plane.getDate());
            preparedStatement.setInt(6,plane.getTicketsAll());
            preparedStatement.setInt(7,plane.getTicketsAll());
            preparedStatement.executeUpdate();
        } catch (SQLException e) { e.printStackTrace();
        }
    }

    private void setPlane(Plane plane, ResultSet resultSet) throws SQLException {
        plane.setId(resultSet.getInt("id"));
        plane.setNum(resultSet.getString("num"));
        plane.setDate(resultSet.getString("date"));
        plane.setCompany(resultSet.getString("company"));
        plane.setCityTo(resultSet.getString("cityTo"));
        plane.setCityFrom(resultSet.getString("cityFrom"));
        plane.setTicketsAll(resultSet.getInt("ticketsAll"));
        plane.setTicketsNotBooked(resultSet.getInt("ticketsNotBooked"));
    }
}
