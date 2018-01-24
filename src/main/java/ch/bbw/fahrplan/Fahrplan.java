package ch.bbw.fahrplan;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduard Munteanu
 *
 */
public class Fahrplan {

    private String fahrplan;
    private ArrayList<Connection> trainConnections;
    private ArrayList<String> connections;

    public Fahrplan(String departureLocation, String arrivalLocation) {
        trainConnections = new ArrayList<>();
        connections = new ArrayList<>();
        getInfo(departureLocation, arrivalLocation);
        printInfo();
    }

    public void getInfo(String departureLocation, String arrivalLocation) {
        try {
            fahrplan = "http://transport.opendata.ch/v1/connections?from=" + departureLocation + "&to=" + arrivalLocation;
            URL url = new URL(fahrplan);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            String json = sb.toString();
            JsonParser jp = new JsonParser();
            Object obj = jp.parse(json);
            JsonObject jb = (JsonObject) obj;

            JsonArray connections = (JsonArray) jb.getAsJsonArray("connections");
            for (int i = 1; i < connections.size(); i++) {
                if (i < 3) {
                    JsonObject connection = (JsonObject) connections.get(i);
                    JsonObject fromObj = (JsonObject) connection.get("from");
                    JsonObject fromStationObj = (JsonObject) fromObj.get("station");
                    long departureTimestamp = Long.parseLong(fromObj.get("departureTimestamp").toString());
                    Date departure = new Date(departureTimestamp * 1000);

                    JsonObject toObj = (JsonObject) connection.get("to");
                    JsonObject toStationObj = (JsonObject) toObj.get("station");
                    long arrivalTimestamp = Long.parseLong(toObj.get("arrivalTimestamp").toString());
                    Date arrival = new Date(arrivalTimestamp * 1000);

                    JsonArray products = (JsonArray) connection.getAsJsonArray("products");
                    ArrayList<String> trains = new ArrayList<>();
                    for (JsonElement je : products) {
                        trains.add(je.toString().replaceAll("\"", ""));
                    }

                    trainConnections.add(new Connection(fromStationObj.get("name").toString().replaceAll("\"", ""),
                            Integer.parseInt(fromObj.get("platform").toString().replaceAll("\"", "")),
                            departure, toStationObj.get("name").toString().replaceAll("\"", ""),
                            Integer.parseInt(toObj.get("platform").toString().replaceAll("\"", "")),
                            arrival, trains));
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Fahrplan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printInfo() {
        DateFormat df = new SimpleDateFormat("dd. MMMM 'um' HH:mm");
        System.out.println("Ihre Verbindungen:");
        int connectionNr = 1;
        for (Connection c : trainConnections) {
            String trains = "";
            for (int i = 0; i < c.getTrainNames().size(); i++) {
                if (i < 1) {
                    trains += c.getTrainNames().get(i);
                } else if (i >= 1) {
                    trains += ", ";
                    trains += c.getTrainNames().get(i);
                }
            }
            connections.add(connectionNr + ". Verbindung:\nVon "
                    + c.getDepartureLocation() + ", Gleis "
                    + c.getDeparturePlatform() + ", " + df.format(c.getDepartureTime())
                    + "\nNach " + c.getArrivalLocation() + ", Gleis "
                    + c.getArrivalPlatform() + ", " + df.format(c.getArrivalTime())
                    + "\nZüge: " + trains + "\n");
            
            connectionNr++;
        }
    }

    /*public static void main(String[] args) {
        
        //TEST Winterthur nach Zürich. Klein- / Grossschreibung und Umlaute unwichtig,
        //JSON-Datei wird trotzdem gefunden.
        Fahrplan fp = new Fahrplan();
        fp.setDepartureLocation("Winterthur");
        fp.setArrivalLocation("Genf");
        fp.getInfo();
        fp.printInfo();
    }*/

    public ArrayList<String> getConnections() {
        return connections;
    }
}
