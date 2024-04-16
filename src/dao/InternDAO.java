package dao;

import model.Intern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InternDAO {

//    CREATE TABLE Interns (
//            ID VARCHAR(50) PRIMARY KEY,
//    FullName VARCHAR(100),
//    BirthDay VARCHAR(20),
//    Phone VARCHAR(20),
//    Email VARCHAR(100),
//    Majors VARCHAR(100),
//    Semester VARCHAR(20),
//    UniversityName VARCHAR(100)
//);

    public void addIntern(Intern intern) {
        String query = "INSERT INTO Interns (ID, FullName, BirthDay, Phone, Email, Majors, Semester, UniversityName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, intern.getID());
            preparedStatement.setString(2, intern.getFullName());
            preparedStatement.setString(3, intern.getBirthDay());
            preparedStatement.setString(4, intern.getPhone());
            preparedStatement.setString(5, intern.getEmail());
            preparedStatement.setString(6, intern.getMajors());
            preparedStatement.setString(7, intern.getSemester());
            preparedStatement.setString(8, intern.getUniversityName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Intern> getAllInterns() {
        List<Intern> interns = new ArrayList<>();
        String query = "SELECT * FROM Interns";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                String fullName = resultSet.getString("FullName");
                String birthDay = resultSet.getString("BirthDay");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                String majors = resultSet.getString("Majors");
                String semester = resultSet.getString("Semester");
                String universityName = resultSet.getString("UniversityName");

                Intern intern = new Intern(ID, fullName, birthDay, phone, email, majors, semester, universityName);
                interns.add(intern);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interns;
    }

    public void updateIntern(Intern intern) {
        String query = "UPDATE Interns SET FullName = ?, BirthDay = ?, Phone = ?, Email = ?, Majors = ?, Semester = ?, UniversityName = ? WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, intern.getFullName());
            preparedStatement.setString(2, intern.getBirthDay());
            preparedStatement.setString(3, intern.getPhone());
            preparedStatement.setString(4, intern.getEmail());
            preparedStatement.setString(5, intern.getMajors());
            preparedStatement.setString(6, intern.getSemester());
            preparedStatement.setString(7, intern.getUniversityName());
            preparedStatement.setString(8, intern.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteIntern(String ID) {
        String query = "DELETE FROM Interns WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Intern findById(String ID) {
        Intern intern = null;
        String query = "SELECT * FROM Interns WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String fullName = resultSet.getString("FullName");
                    String birthDay = resultSet.getString("BirthDay");
                    String phone = resultSet.getString("Phone");
                    String email = resultSet.getString("Email");
                    String majors = resultSet.getString("Majors");
                    String semester = resultSet.getString("Semester");
                    String universityName = resultSet.getString("UniversityName");

                    intern = new Intern(ID, fullName, birthDay, phone, email, majors, semester, universityName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return intern;
    }

}
