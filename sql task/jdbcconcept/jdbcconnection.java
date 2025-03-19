package jdbcconcept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class jdbcconnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/userdb"; // Database name
        String user = "root"; // MySQL username
        String password = "DILIP@2003@s"; // MySQL password

        String query = "INSERT INTO employee (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";

        Scanner scanner = new Scanner(System.in);

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Established Successfully");

            // Prepare Statement
            PreparedStatement pstmt = con.prepareStatement(query);

            System.out.print("Enter the number of employees: ");
            int numEmployees = scanner.nextInt();

            for (int i = 0; i < numEmployees; i++) {
               
                
                System.out.print("Enter Employee Code: ");
                int empcode = scanner.nextInt();
                
                scanner.nextLine(); // Consume newline
                
                System.out.print("Enter Employee Name: ");
                String empname = scanner.nextLine();
                
                System.out.print("Enter Employee Age: ");
                int empage = scanner.nextInt();
                
                System.out.print("Enter Employee Salary: ");
                double esalary = scanner.nextDouble();

                
                pstmt.setInt(1, empcode);
                pstmt.setString(2, empname);
                pstmt.setInt(3, empage);
                pstmt.setDouble(4, esalary);

                // Execute insert
                int rowInsertion = pstmt.executeUpdate();

                if (rowInsertion > 0) {
                    System.out.println("Employee " + empname + " inserted successfully.");
                }
            }
            pstmt.close();
            con.close();
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
