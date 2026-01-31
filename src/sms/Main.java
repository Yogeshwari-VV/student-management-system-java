package sms;

import java.sql.*;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try (Connection con = DBConnection.getConnection()) {

                // ADD STUDENT
                if (choice == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    String sql = "INSERT INTO students VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setInt(3, age);
                    ps.setString(4, dept);

                    ps.executeUpdate();
                    System.out.println("Student added successfully.");
                }

                // VIEW STUDENTS
                else if (choice == 2) {
                    String sql = "SELECT * FROM students";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    System.out.println("\nID   Name   Age   Department");
                    System.out.println("--------------------------------");
                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + "   " +
                            rs.getString("name") + "   " +
                            rs.getInt("age") + "   " +
                            rs.getString("department")
                        );
                    }
                }

                // SEARCH STUDENT
                else if (choice == 3) {
                    System.out.print("Enter ID to search: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    String sql = "SELECT * FROM students WHERE id=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println(
                            "ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Age: " + rs.getInt("age") +
                            ", Dept: " + rs.getString("department")
                        );
                    } else {
                        System.out.println("Student not found.");
                    }
                }

                // UPDATE STUDENT
                else if (choice == 4) {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    String name = sc.nextLine();

                    System.out.print("New Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Department: ");
                    String dept = sc.nextLine();

                    String sql =
                        "UPDATE students SET name=?, age=?, department=? WHERE id=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.setString(3, dept);
                    ps.setInt(4, id);

                    int rows = ps.executeUpdate();
                    if (rows > 0)
                        System.out.println("Student updated successfully.");
                    else
                        System.out.println("Student not found.");
                }

                // DELETE STUDENT
                else if (choice == 5) {
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    String sql = "DELETE FROM students WHERE id=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, id);

                    int rows = ps.executeUpdate();
                    if (rows > 0)
                        System.out.println("Student deleted successfully.");
                    else
                        System.out.println("Student not found.");
                }

                // EXIT (PROFESSIONAL)
                else if (choice == 6) {
                    System.out.println("Thank you for using the Student Management System.");
                    System.out.println("Application closed successfully.");
                    System.exit(0);
                }

                else {
                    System.out.println("Invalid choice. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
