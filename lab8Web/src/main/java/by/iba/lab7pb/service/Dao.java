package by.iba.lab7pb.service;

import by.iba.lab7pb.model.Person;
import by.iba.lab7pb.model.Plane;
import by.iba.lab7pb.model.Ticket;
import by.iba.lab7pb.model.User;

import java.util.List;

public interface Dao {
    public List<Person> getPersons();
    public void insertPerson(Person person);
    public int getLastUser();
    public Person getPerson(int id);
    public void updatePlanesTickets(int id, boolean plus);
    public List<Plane> getPlanes();
    public Plane getPlane(int id);
    public List<Ticket> getTickets();
    public boolean insertTicket(int idPlane, int idPerson);
    public void deleteBook(int id);
    public User isValidUser(final String login, final byte[] password);
    public boolean insertUser(User user);
    public void insertPlane(Plane plane);
    public int getPersonByUser(int id);
}
