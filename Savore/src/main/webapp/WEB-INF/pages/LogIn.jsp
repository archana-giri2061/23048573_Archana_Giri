<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/LogIn.css"/>
</head>
<body>
    <nav class="navbar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/Resources/Images/System/FinalLogo.png" alt="Savore Logo">
        </div>
    </nav>
    <div class="container">
        <div class="login-card">
            <h1 class="brand">Savore</h1>
            <h2>Welcome Back</h2>
            <p>Sign in with your email address and password</p>

            <form method="post" action="${pageContext.request.contextPath}/logIn">
                <label for="userName">User Name</label>
                <input type="email" name="userName" placeholder="Enter email" required>

                <label for="password">Password</label>
                <input type="password" name="password" placeholder="Enter password" required>

                <a href="#" class="forgot">Forget Password?</a>

                <button type="submit" class="signin-btn">Sign In</button>
            </form>

            <p class="signup-text">Don’t have an account? <a href="${pageContext.request.contextPath}/RegistrationPage" class="signup-link">SignUp</a></p>
        </div>
    </div>
</body>
</html>
