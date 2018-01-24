package ch.bbw.fahrplan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Eduard Munteanu
 * 
 */
public interface FahrplanInterface extends Remote {
    
    public ArrayList<String> getConnections(String departureLocation, String arrivalLocation) throws RemoteException;
    
}
