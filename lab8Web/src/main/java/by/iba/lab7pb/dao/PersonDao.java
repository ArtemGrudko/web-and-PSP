package by.iba.lab7pb.dao;

import by.iba.lab7pb.model.Person;
import by.iba.lab7pb.util.ConnectorDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonDao {

    private static final Logger logger = LogManager.getLogger(PersonDao.class);

    private final static String SQL_GET_PERSONS = "SELECT * FROM persons";
    private final static String SQL_INSERT_PERSONS = "INSERT INTO persons(pname, phone, email, userID) VALUES (?,?,?,?)";
    private final static String SQL_GET_PERSON = "SELECT * FROM persons WHERE id = ?";
    private final static String SQL_GET_PERSON_BY_USER = "SELECT id FROM persons WHERE userID = ?";

    private static Connection connection;

    public PersonDao(Connection connection) {
        if (connection != null) {
            this.connection = connection;
            logger.info("get connection");
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                logger.info("close connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPerson(Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_INSERT_PERSONS);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getPhone());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4,person.getUserID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info("New Person " + person.getName() +" inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Person> getPersons() {
        List<Person> persons = new LinkedList<Person>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_PERSONS);

            Person person = null;
            while (resultSet.next()) {
                person = new Person();

                person.setName(resultSet.getString("pname"));
                person.setPhone(resultSet.getString("phone"));
                person.setEmail(resultSet.getString("email"));
                person.setUserID(resultSet.getInt("userID"));
                persons.add(person);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public Person getPerson(int id) {
        Person person = new Person();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_GET_PERSON);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                person.setName(resultSet.getString("pname"));
                person.setPhone(resultSet.getString("phone"));
                person.setEmail(resultSet.getString("email"));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public int getPersonByUser(int id){
        try{
            PreparedStatement ps = connection.prepareStatement(SQL_GET_PERSON_BY_USER);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
