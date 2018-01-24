package ch.bbw.fahrplan;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Eduard Munteanu
 * 
 */
public class Connection {
    private String departureLocation;
    private int departurePlatform;
    private Date departureTime;
    private String arrivalLocation;
    private int arrivalPlatform;
    private Date arrivalTime;
    private ArrayList<String> trainNames;

    public Connection(String departureLocation, int departurePlatform, Date departureTime, String arrivalLocation, int arrivalPlatform, Date arrivalTime, ArrayList<String> trainNames) {
        this.departureLocation = departureLocation;
        this.departurePlatform = departurePlatform;
        this.departureTime = departureTime;
        this.arrivalLocation = arrivalLocation;
        this.arrivalPlatform = arrivalPlatform;
        this.arrivalTime = arrivalTime;
        this.trainNames = trainNames;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public int getDeparturePlatform() {
        return departurePlatform;
    }

    public void setDeparturePlatform(int departurePlatform) {
        this.departurePlatform = departurePlatform;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public int getArrivalPlatform() {
        return arrivalPlatform;
    }

    public void setArrivalPlatform(int arrivalPlatform) {
        this.arrivalPlatform = arrivalPlatform;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ArrayList<String> getTrainNames() {
        return trainNames;
    }

    public void setTrainNames(ArrayList<String> trainNames) {
        this.trainNames = trainNames;
    }
}
