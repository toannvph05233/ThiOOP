package dao;

import model.Experience;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDAO {
//    CREATE TABLE Experiences (
//            ID VARCHAR(50) PRIMARY KEY,
//    FullName VARCHAR(100),
//    BirthDay VARCHAR(20),
//    Phone VARCHAR(20),
//    Email VARCHAR(100),
//    ExpInYear INT,
//    ProSkill VARCHAR(100)
//);

    public void addExperience(Experience experience) {
        String query = "INSERT INTO Experiences (ID, FullName, BirthDay, Phone, Email, ExpInYear, ProSkill) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, experience.getID());
            preparedStatement.setString(2, experience.getFullName());
            preparedStatement.setString(3, experience.getBirthDay());
            preparedStatement.setString(4, experience.getPhone());
            preparedStatement.setString(5, experience.getEmail());
            preparedStatement.setInt(6, experience.getExpInYear());
            preparedStatement.setString(7, experience.getProSkill());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Experience> getAllExperiences() {
        List<Experience> experiences = new ArrayList<>();
        String query = "SELECT * FROM Experiences";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                String fullName = resultSet.getString("FullName");
                String birthDay = resultSet.getString("BirthDay");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                int expInYear = resultSet.getInt("ExpInYear");
                String proSkill = resultSet.getString("ProSkill");

                Experience experience = new Experience(ID, fullName, birthDay, phone, email, expInYear, proSkill);
                experiences.add(experience);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experiences;
    }

    public void updateExperience(Experience experience) {
        String query = "UPDATE Experiences SET FullName = ?, BirthDay = ?, Phone = ?, Email = ?, ExpInYear = ?, ProSkill = ? WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, experience.getFullName());
            preparedStatement.setString(2, experience.getBirthDay());
            preparedStatement.setString(3, experience.getPhone());
            preparedStatement.setString(4, experience.getEmail());
            preparedStatement.setInt(5, experience.getExpInYear());
            preparedStatement.setString(6, experience.getProSkill());
            preparedStatement.setString(7, experience.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteExperience(String ID) {
        String query = "DELETE FROM Experiences WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Experience findById(String ID) {
        Experience experience = null;
        String query = "SELECT * FROM Experiences WHERE ID = ?";
        try (Connection connection = ConnectCSDL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String fullName = resultSet.getString("FullName");
                    String birthDay = resultSet.getString("BirthDay");
                    String phone = resultSet.getString("Phone");
                    String email = resultSet.getString("Email");
                    int expInYear = resultSet.getInt("ExpInYear");
                    String proSkill = resultSet.getString("ProSkill");

                    experience = new Experience(ID, fullName, birthDay, phone, email, expInYear, proSkill);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experience;
    }
}

