package com.Savore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Savore.config.DbConfig;
import com.Savore.model.UserModel;
import com.Savore.util.PasswordUtil;

public class LoginService {
    private Connection dbConn;
    private boolean isConnectionError = false;
    
    public LoginService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    public Boolean loginUser(UserModel userModel) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return null;
        }
        
        String query = "SELECT userName, Password FROM user WHERE userName = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, userModel.getUserName());
            
            ResultSet result = stmt.executeQuery();
            
            if (result.next()) {
                return validatePassword(result, userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return null; // User not found or invalid credentials
    }
    
    /**
	 * Validates the password retrieved from the database.
	 *
	 * @param result       the ResultSet containing the user name and password from
	 *                     the database
	 * @param user the UserModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validatePassword(ResultSet result, UserModel user) throws SQLException {
		String dbUserName = result.getString("userName");
		String dbPassword = result.getString("password");

		return dbUserName.equals(user.getUserEmail())
				&& PasswordUtil.decrypt(dbPassword, dbUserName).equals(user.getPassword());
	}
//    private Boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
//        String dbPassword = result.getString("Password");
//
//        String decryptedPassword = PasswordUtil.decrypt(dbPassword, userModel.getUserEmail());
//        if (decryptedPassword != null && decryptedPassword.equals(userModel.getPassword())) {
//            UserModel user = new UserModel();
//            user.setUserId(result.getInt("userId"));
//            user.setUserName(result.getString("userName"));
//            user.setUserEmail(result.getString("userEmail"));
//            user.setPassword(dbPassword); // Keep encrypted password for session
//            user.setAddress(result.getString("Address"));
//            user.setRole(result.getString("Role"));
//            return true;
//        }
//        
//        return false; // Password mismatch
//    }
//
//    public boolean userExists(String userEmail) {
//        if (isConnectionError) {
//            return false;
//        }
//        
//        String query = "SELECT userEmail FROM user WHERE userEmail = ?";
//        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
//            stmt.setString(1, userEmail);
//            ResultSet result = stmt.executeQuery();
//            
//            return result.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
