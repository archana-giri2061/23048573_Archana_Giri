package com.Savore.Controller;

import java.io.IOException;
import java.sql.Connection;

import com.Savore.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = {"/LandingPage"})
public class LandingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/LandingPage.jsp").forward(req, resp);
    }
    
    
    
    
    
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	checkDatabaseConnection(req);
		super.doPost(req, resp);
		
	}





	/**
     * Checks database connection and sets relevant request attributes.
     *
     * @param request HttpServletRequest to set attributes on
     */
    private void checkDatabaseConnection(HttpServletRequest request) {
        try (Connection connection = DbConfig.getDbConnection()) {
            System.out.println("✅ Successfully connected to database!");
            request.setAttribute("dbStatus", "Connected successfully to database: " + DbConfig.getDbName());

            // Optional: Perform a simple query to demonstrate functionality
            performSampleQuery(connection, request);

        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            request.setAttribute("dbStatus", "Connection failed: " + e.getMessage());
        }
    }

	private void performSampleQuery(Connection connection, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
    
}