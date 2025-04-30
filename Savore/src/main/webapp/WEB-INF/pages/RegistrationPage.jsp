<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/RegistrationPage.css">
</head>
<body>
	<nav class="navbar">
        <div class="logo"><img src="${pageContext.request.contextPath}/Resources/Images/System/FinalLogo.png" alt="Italian Trulli"></div>
    </nav>
	<div class="container">
    <div class="form-box">
      <h1><span style="color: #b45f06;">Savore</span></h1>
      <h2>Create account</h2>

      <form action="${pageContext.request.contextPath}/RegistrationPage" method="post">
        <label for="fullname">Full Name</label>
        <input type="text" id="fullname" name="fullname" placeholder="Enter full name" required>

        <label for="email">Email Address</label>
        <input type="email" id="email" name="email" placeholder="Enter email" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter password" required>

        <label for="address">Address</label>
        <input type="text" id="address" name="address" placeholder="Enter address" required>

        <label for="phone">Phone number</label>
        <input type="tel" id="phone" name="phone" placeholder="Enter phone number" required>

        <label for="role">Role</label>
        <select id="role" name="role" required>
          <option value="">Select role</option>
          <option value="admin">Admin</option>
          <option value="user">User</option>
        </select>

        <button type="submit" class="signup-btn">Sign Up</button>
      </form>

      <p>Join with</p>
      <div class="social-icons">
        <img src="facebook-icon.png" alt="Facebook" />
        <img src="mail-icon.png" alt="Email" />
        <img src="camera-icon.png" alt="Other" />
      </div>
    </div>
  </div>
</body>
</html>