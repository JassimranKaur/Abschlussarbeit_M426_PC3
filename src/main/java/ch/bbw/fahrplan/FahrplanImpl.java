/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.fahrplan;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Eduard Munteanu
 * 
 */
public class FahrplanImpl extends UnicastRemoteObject implements FahrplanInterface {
    
    public FahrplanImpl() throws RemoteException {
        
    }
    
    public ArrayList<String> getConnections(String departureLocation, String arrivalLocation) throws RemoteException {
        Fahrplan fp = new Fahrplan(departureLocation, arrivalLocation);
        return fp.getConnections();
    }
    
    public static void main(String[] args) {
        try {
            
            LocateRegistry.createRegistry(1099); //Use default Port 1099
            
        } catch(RemoteException e) {
            System.out.println("Could not start Registry"); //Kann auch bedeuten, dass die Registry schon läuft
        }
        
        try {
            
            FahrplanImpl fpimpl = new FahrplanImpl();
            
            String fpService = "FahrplanPC3";
            Naming.rebind("//localhost/" + fpService, fpimpl); //my ip: 172.25.42.37
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
