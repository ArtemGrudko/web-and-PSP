package by.iba.lab7pb.service;

import by.iba.lab7pb.dao.PersonDao;
import by.iba.lab7pb.dao.PlaneDao;
import by.iba.lab7pb.dao.TicketDao;
import by.iba.lab7pb.dao.UserDao;
import by.iba.lab7pb.model.Person;
import by.iba.lab7pb.model.Plane;
import by.iba.lab7pb.model.Ticket;
import by.iba.lab7pb.model.User;
import by.iba.lab7pb.util.ConnectionPool;

import java.sql.Connection;
import java.util.List;

public class DaoImpl implements Dao{
    private UserDao userDao;
    private PersonDao personDao;
    private PlaneDao planeDao;
    private TicketDao ticketDao;
    private ConnectionPool connectionPool;

    public DaoImpl(){
        connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public List<Person> getPersons() {
        Connection connection = connectionPool.getConnection();
        personDao = new PersonDao(connection);
        List<Person> persons =  personDao.getPersons();
        connectionPool.releaseConnection(connection);
        return persons;
    }

    @Override
    public void insertPerson(Person person) {
        Connection connection = connectionPool.getConnection();
        personDao = new PersonDao(connection);
        personDao.insertPerson(person);
        connectionPool.releaseConnection(connection);
    }

    @Override
    public int getLastUser() {
        Connection connection = connectionPool.getConnection();
        userDao = new UserDao(connection);
        int lastUser = userDao.getLastUser();
        connectionPool.releaseConnection(connection);
        return lastUser;
    }

    @Override
    public Person getPerson(int id) {
        Connection connection = connectionPool.getConnection();
        personDao = new PersonDao(connection);
        Person person = personDao.getPerson(id);
        connectionPool.releaseConnection(connection);
        return person;
    }

    @Override
    public void updatePlanesTickets(int id, boolean plus) {
        Connection connection = connectionPool.getConnection();
        planeDao = new PlaneDao(connection);
        planeDao.updatePlanesTickets(id,plus);
        connectionPool.releaseConnection(connection);
    }

    @Override
    public List<Plane> getPlanes() {
        Connection connection = connectionPool.getConnection();
        planeDao = new PlaneDao(connection);
        List<Plane> planes = planeDao.getPlanes();
        connectionPool.releaseConnection(connection);
        return planes;
    }

    @Override
    public Plane getPlane(int id) {
        Connection connection = connectionPool.getConnection();
        planeDao = new PlaneDao(connection);
        Plane plane = planeDao.getPlane(id);
        connectionPool.releaseConnection(connection);
        return plane;
    }

    @Override
    public List<Ticket> getTickets() {
        Connection connection = connectionPool.getConnection();
        ticketDao = new TicketDao(connection);
        List<Ticket> tickets = ticketDao.getTickets();
        connectionPool.releaseConnection(connection);
        return tickets;
    }

    @Override
    public boolean insertTicket(int idPlane, int idPerson) {
        Connection connection = connectionPool.getConnection();
        ticketDao = new TicketDao(connection);
        boolean ready = ticketDao.insertTicket(idPlane,idPerson);
        connectionPool.releaseConnection(connection);
        return ready;
    }

    @Override
    public void deleteBook(int id) {
        Connection connectionForTicket = connectionPool.getConnection();
        ticketDao =new TicketDao(connectionForTicket);
        Connection connectionForPlane = connectionPool.getConnection();
        planeDao = new PlaneDao(connectionForPlane);
        planeDao.updatePlanesTickets(ticketDao.getTicket(id).getIdPlane(), false);
        ticketDao.deleteBook(id);
        connectionPool.releaseConnection(connectionForPlane);
        connectionPool.releaseConnection(connectionForTicket);
    }

    @Override
    public User isValidUser(String login, byte[] password) {
        Connection connection = connectionPool.getConnection();
        userDao = new UserDao(connection);
        connectionPool.releaseConnection(connection);
        return userDao.isValidUser(login, password);
    }

    @Override
    public boolean insertUser(User user) {
        Connection connection = connectionPool.getConnection();
        userDao = new UserDao(connection);
        connectionPool.releaseConnection(connection);
        return userDao.insertUser(user);
    }

    @Override
    public void insertPlane(Plane plane) {
        Connection connection = connectionPool.getConnection();
        planeDao = new PlaneDao(connection);
        planeDao.insertPlane(plane);
        connectionPool.releaseConnection(connection);
    }

    @Override
    public int getPersonByUser(int id) {
        Connection connection = connectionPool.getConnection();
        personDao = new PersonDao(connection);
        int person = personDao.getPersonByUser(id);
        connectionPool.releaseConnection(connection);
        return person;
    }
}
