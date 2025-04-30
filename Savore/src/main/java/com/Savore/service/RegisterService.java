package com.Savore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Savore.config.DbConfig;
import com.Savore.model.UserModel;
import com.Savore.util.PasswordUtil;

public class RegisterService {
	 private Connection dbConn;

	    public RegisterService() {
	        try {
	            this.dbConn = DbConfig.getDbConnection();
	        } catch (SQLException | ClassNotFoundException ex) {
	            System.err.println("Database connection error: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }

	    /**
	     * Debug method to check the table structure
	     */
	    public void debugTableStructure() {
	        try (Statement stmt = dbConn.createStatement()) {
	            ResultSet rs = stmt.executeQuery("DESCRIBE user");
	            System.out.println("User table structure:");
	            while (rs.next()) {
	                System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
	            }
	        } catch (SQLException e) {
	            System.err.println("Error checking table structure: " + e.getMessage());
	        }
	    }

	    /**
	     * Checks if a username already exists in the database
	     */
	    public boolean isUserNameExists(String userName) {
	        if (dbConn == null) return false;
	        
	        String sql = "SELECT COUNT(*) FROM user WHERE userName = ?";
	        
	        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
	            stmt.setString(1, userName);
	            
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error checking username: " + e.getMessage());
	            e.printStackTrace();
	        }
	        
	        return false;
	    }
	    
	    /**
	     * Checks if an email already exists in the database
	     */
	    public boolean isUserEmailExists(String userEmail) {
	        if (dbConn == null) return false;
	        
	        String sql = "SELECT COUNT(*) FROM user WHERE userEmail = ?";
	        
	        try (PreparedStatement stmt = dbConn.prepareStatement(sql)) {
	            stmt.setString(1, userEmail);
	            
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error checking email: " + e.getMessage());
	            e.printStackTrace();
	        }
	        
	        return false;
	    }

	   
	    /**
	     * Register a new user in the database
	     */
	    public boolean registerUser(UserModel user) {
	        System.out.println("Attempting to register user: " + user.getUserName() + ", " + user.getUserEmail());
	        
	        if (dbConn == null) {
	            System.err.println("Database connection is null!");
	            return false;
	        }
	        
	        // Debug table structure
	        debugTableStructure();
	        
	        // First check if username or email already exists
	        if (isUserNameExists(user.getUserName())) {
	            System.err.println("Username already exists: " + user.getUserName());
	            return false;
	        }
	        
	        if (isUserEmailExists(user.getUserEmail())) {
	            System.err.println("Email already exists: " + user.getUserEmail());
	            return false;
	        }

	        String sql = "INSERT INTO user (userName, userEmail, Password, Address, Role) " +
	                     "VALUES (?, ?, ?, ?, ?)";

	        try (PreparedStatement stmt = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS )) {
	            // Log the SQL and values
	            System.out.println("Executing SQL: " + sql);
	            System.out.println("With values: " + user.getUserName() + ", " + user.getUserEmail() + ", " + 
	            				"[PASSWORD]" + ", " + user.getAddress() + 
	                          ", "  + user.getRole());
	            
	            stmt.setString(1, user.getUserName());
	            stmt.setString(2, user.getUserEmail());
	            stmt.setString(3, PasswordUtil.encrypt(user.getPassword(), user.getUserName()));
	            stmt.setString(4, user.getAddress());
	            stmt.setString(5, user.getRole());

	            int rowsAffected = stmt.executeUpdate();
	            
	            if (rowsAffected > 0) {
	                System.out.println("Registration successful! Rows affected: " + rowsAffected);
	                // Get the auto-generated user ID
	                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        user.setUserId(generatedKeys.getInt(1));
	                        System.out.println("Generated user ID: " + user.getUserId());
	                    }
	                }
	                return true;
	            }
	            System.out.println("No rows affected by INSERT");
	            return false;
	        } catch (SQLException e) {
	            System.err.println("SQL Error: " + e.getMessage());
	            System.err.println("SQL State: " + e.getSQLState());
	            System.err.println("Error Code: " + e.getErrorCode());
	            e.printStackTrace();
	            return false;
	        }
	    }
}
