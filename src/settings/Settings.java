/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

/**
 *
 * @author power
 */
public class Settings {
    final static public Integer submitInterval = 50;
    
    final static public double[] baseEmissions = {1820.3, 1038, 350, 312.7, 898.6};
    final static public double[] costParams    = {0.2755, 0.9065, 2.4665, 1.1080, 0.7845};
    final static public double[] emissionLimits = {1251, 860, 258, 215, 1314}; // prawdziwe
   //private double[] emissionLimits = {1251, 1860, 258, 215, 1314};
    final static public String [] names = {"USA", "EU", "Japonia", "CANZ", "WNP"};
    final static public double[] uncert = {0.13, 0.10, 0.15, 0.20, 0.30};
    final static public double[] uncertd = {0.13, 0.1, 0.15, 0.20, 0.30};
    final static public double[] uncertu = {0.13, 0.1, 0.15, 0.20, 0.30};
    final public static Double priceAccuracy = 0.005;
}
