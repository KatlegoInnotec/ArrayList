
package pracdb;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;

public class PracDB {

    public static void main(String[] args) throws SQLException {

       /* String[] names = {"Kat Inno", "Joe Mark"};

        for (int i = 0; i < names.length; i++) {

            System.out.println(names[i].charAt(0));
            for (int j = 0; j < names[i].length(); j++) {
                if (Character.isWhitespace(names[i].charAt(j))) {
                    System.out.println(names[i].charAt(j+1));
                }
            }

        }
*/
       //Create the url link str
     String URL = "jdbc:derby://localhost:1527/CarDB";
     
     //Declare all your class 
     Connection conn = null;
     ResultSet rs = null;
     Statement stmt = null;
     
     Scanner sc = new Scanner(System.in);
     
     //1st thing create connect to the Driver Manager
     conn = DriverManager.getConnection(URL);
     
     //2nd create a statement
     stmt = conn.createStatement();
     
     //3td Query your database 
     rs = stmt.executeQuery("SELECT * FROM CARS");
     
    
     
    selectSpecificCars(rs);
    }

    static void getMostExpensiveCar(ResultSet rs) throws SQLException {
        double price = Double.MIN_VALUE;
        String expensive = "";

        while (rs.next()) {

            double mostExpensive = rs.getDouble("price");
            if (mostExpensive > price) {
                price = mostExpensive;

                expensive = rs.getString("make") + " " + rs.getString("model");
            }

        }
        System.out.println("Most Expensive Car " + expensive + " ( R" + price + ")");

    }

    static void averagePrice(ResultSet rs) throws SQLException {
        double averagePrice = 0;
        int count = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        while (rs.next()) {
            double mostExpensive = rs.getDouble("price");
            averagePrice += mostExpensive;
            count++;
        }

        averagePrice = averagePrice / (double) count;
        System.out.println("Average price: " + df.format(averagePrice));
    }

    static void selectSpecificCars(ResultSet rs) throws SQLException {
        String carDetails = "";
        String storeCars = "";

        while (rs.next()) {
            carDetails = rs.getString("make");
            if (carDetails.charAt(0) == 'M') {
                storeCars += "Make " + rs.getString("make") + " model " + rs.getString("model") + " manufactured " + rs.getString("MANUFACTURE_YEAR") + " (R" + rs.getDouble("price") + ")\n";
            }
        }
        System.out.println(storeCars);
    }

    static void dispalyAllCarMake(ResultSet rs) throws SQLException {
        String makeNames = "";

        while (rs.next()) {
            String makeCar = rs.getString("Make");
            makeNames += rs.getString("make");

        }
    }
}
