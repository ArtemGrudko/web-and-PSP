package by.iba.lab7pb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
    private Integer id;
    private String num;
    private String company;
    private String cityFrom;
    private String cityTo;
    private String date;
    private int ticketsAll;
    private int ticketsNotBooked;
}
