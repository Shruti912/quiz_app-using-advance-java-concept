<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="Style.css">
    <script>
        function validateRegistrationForm() 
        {
            var username = document.forms["form1"]["username"].value;
            var email = document.forms["form1"]["email"].value;
            var password = document.forms["form1"]["password"].value;

            if (username === "") {
                alert("Username must be filled out");
                return false;
            }
            if (email === "") {
                alert("Email must be filled out");
                return false;
            }
            if (password === "") {
                alert("Password must be filled out");
                return false;
            }
            return true;
        }
        function clearFormFields() 
        {
            document.getElementById("username").value = "";
            document.getElementById("email").value = "";
            document.getElementById("password").value = "";
        }
    </script>
</head>
<body onload="clearFormFields()">
    <div class="error-page-int">
    <form action="Registration" method="POST" autocomplete="off">
      <h2>Quiz Registration</h2>
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required autocomplete="new-password">
      </div>
      <div class="form-group">
        <button type="submit">Register</button>
      </div>
    </form>
    </div>
</body>
</html>