package com.Savore.Controller;
import java.io.IOException;

import com.Savore.model.UserModel;
import com.Savore.service.RegisterService;
import com.Savore.util.PasswordUtil;
import com.Savore.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(asyncSupported = true, urlPatterns = {"/RegistrationPage"})
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService registerService;
	private ValidationUtil validationUtil = new ValidationUtil();
	@Override
	public void init() throws ServletException{
		registerService = new RegisterService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/RegistrationPage.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doPost()");

		try {
			System.out.println("Registration is being processed");
			System.out.println("Extracting user data...");
			UserModel user = extractUserModel(req);
			System.out.println("User extracted: " + user.getUserName());

			Boolean isRegistered = registerService.registerUser(user);
			 if (isRegistered) {
	                System.out.println("Registration successful for user: " + user.getUserName());
	                // Set success message and redirect to login
	                HttpSession session = req.getSession();
	                session.setAttribute("successMessage", "Registration successful! Please login.");
	                res.sendRedirect("Login");
	            } else {
	                System.out.println("Registration failed for user: " + user.getUserName());
	                // To check for failures
	                String errorMsg = "Registration failed. ";
	                if (registerService.isUserNameExists(user.getUserName())) {
	                    errorMsg += "Username already exists. ";
	                } else if(!validationUtil.isAlphanumericStartingWithLetter(user.getUserName())) {
	                	errorMsg += "Invalid username, try again! ";
	                }
	                if (registerService.isUserEmailExists(user.getUserEmail())) {
	                    errorMsg += "Email already exists. ";
	                } else if (!validationUtil.isValidEmail(user.getUserEmail())){
	                	errorMsg += "Invalid email id, try again!";
	                }
	                if (errorMsg.equals("Registration failed.")) {
	                	errorMsg += "Please check the logs for more details.";
	                }
	                req.setAttribute("error", errorMsg);
	                req.getRequestDispatcher("/WEB-INF/pages/LandingPage.jsp").forward(req, res);
	                }
	      }catch (Exception e) {
	            System.err.println("Exception in registration process: " + e.getMessage());
	            e.printStackTrace();
	            req.setAttribute("error", "An error occurred: " + e.getMessage());
	            req.getRequestDispatcher("/WEB-INF/pages/Registration.jsp").forward(req, res);
	        }
		}

	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            System.out.println("Register form submitted - processing registration");
//            
//            // Extract user info from form
//            UserModel user = extractUserModel(req);
//            System.out.println("User extracted: " + user.getUserName());
//            
//            // Call the service to register the user
//            boolean isRegistered = registerService.registerUser(user);
//            
//            if (isRegistered) {
//                System.out.println("Registration successful for user: " + user.getUserName());
//                // Set success message and redirect to login
//                HttpSession session = req.getSession();
//                session.setAttribute("successMessage", "Registration successful! Please login.");
//                resp.sendRedirect("Login");
//            } else {
//                System.out.println("Registration failed for user: " + user.getUserName());
//                // Be more specific about the failure reason
//                String errorMsg = "Registration failed. ";
//                if (registerService.isUserNameExists(user.getUserName())) {
//                    errorMsg += "Username already exists. ";
//                }
//                if (registerService.isUserEmailExists(user.getUserEmail())) {
//                    errorMsg += "Email already exists. ";
//                }
//                
//                if (errorMsg.equals("Registration failed. ")) {
//                    errorMsg += "Please check the logs for more details.";
//                }
//                
//                req.setAttribute("error", errorMsg);
//                req.getRequestDispatcher("/WEB-INF/pages/LandingPage.jsp").forward(req, resp);
//            }
//        } catch (Exception e) {
//            System.err.println("Exception in registration process: " + e.getMessage());
//            e.printStackTrace();
//            req.setAttribute("error", "An error occurred: " + e.getMessage());
//            req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);
//        }
//    }
//    
    // Helper method to extract UsersModel from the form
    private UserModel extractUserModel(HttpServletRequest req) {
        UserModel user = new UserModel();
        // Get form data
        String userName = req.getParameter("fullname");
        String Password = req.getParameter("password");
        String userEmail = req.getParameter("email");
        String Address = req.getParameter("address");
        String Role = req.getParameter("role");
        
        System.out.println("Extracted form data - Username: " + userName + ", Email: " + userEmail + 
                          ", Address: " + Address  + ", Role: " + Role);
        
        System.out.println("Registering with: " + Address );

        // Set required user details
        user.setUserName(userName);
        
        // Encrypt password using PasswordUtil
        String encryptedPassword = PasswordUtil.encrypt(userName, Password);
        if (encryptedPassword != null) {
            user.setPassword(encryptedPassword);
            System.out.println("Password encrypted successfully");
        } else {
            System.err.println("Password encryption failed, storing plain password temporarily for debugging");
            
        }
        
        user.setUserEmail(userEmail);
        user.setAddress(Address);
        user.setRole(Role);
		return user;
        
    }
}
