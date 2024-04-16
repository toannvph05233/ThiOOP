package dao;

import model.Fresher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FresherDAO {
//    CREATE TABLE Freshers (
//            ID VARCHAR(50) PRIMARY KEY,
//    FullName VARCHAR(100),
//    BirthDay VARCHAR(20),
//    Phone VARCHAR(20),
//    Email VARCHAR(100),
//    GraduationDate VARCHAR(20),
//    GraduationRank VARCHAR(50),
//    Education VARCHAR(100)
//);

    public void addFresher(Fresher fresher) {
        String query = "INSERT INTO Freshers (ID, FullName, BirthDay, Phone, Email, GraduationDate, GraduationRank, Education) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fresher.getID());
            preparedStatement.setString(2, fresher.getFullName());
            preparedStatement.setString(3, fresher.getBirthDay());
            preparedStatement.setString(4, fresher.getPhone());
            preparedStatement.setString(5, fresher.getEmail());
            preparedStatement.setString(6, fresher.getGraduationDate());
            preparedStatement.setString(7, fresher.getGraduationRank());
            preparedStatement.setString(8, fresher.getEducation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fresher> getAllFreshers() {
        List<Fresher> freshers = new ArrayList<>();
        String query = "SELECT * FROM Freshers";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                String fullName = resultSet.getString("FullName");
                String birthDay = resultSet.getString("BirthDay");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                String graduationDate = resultSet.getString("GraduationDate");
                String graduationRank = resultSet.getString("GraduationRank");
                String education = resultSet.getString("Education");

                Fresher fresher = new Fresher(ID, fullName, birthDay, phone, email, graduationDate, graduationRank, education);
                freshers.add(fresher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freshers;
    }

    public void updateFresher(Fresher fresher) {
        String query = "UPDATE Freshers SET FullName = ?, BirthDay = ?, Phone = ?, Email = ?, GraduationDate = ?, GraduationRank = ?, Education = ? WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fresher.getFullName());
            preparedStatement.setString(2, fresher.getBirthDay());
            preparedStatement.setString(3, fresher.getPhone());
            preparedStatement.setString(4, fresher.getEmail());
            preparedStatement.setString(5, fresher.getGraduationDate());
            preparedStatement.setString(6, fresher.getGraduationRank());
            preparedStatement.setString(7, fresher.getEducation());
            preparedStatement.setString(8, fresher.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFresher(String ID) {
        String query = "DELETE FROM Freshers WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Fresher findById(String ID) {
        Fresher fresher = null;
        String query = "SELECT * FROM Freshers WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String fullName = resultSet.getString("FullName");
                    String birthDay = resultSet.getString("BirthDay");
                    String phone = resultSet.getString("Phone");
                    String email = resultSet.getString("Email");
                    String graduationDate = resultSet.getString("GraduationDate");
                    String graduationRank = resultSet.getString("GraduationRank");
                    String education = resultSet.getString("Education");

                    fresher = new Fresher(ID, fullName, birthDay, phone, email, graduationDate, graduationRank, education);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fresher;
    }}

