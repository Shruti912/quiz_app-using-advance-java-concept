<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" type="text/css" href="Style.css"> 
    <script>
        function clearFormFields() 
        {
            document.getElementById("username").value = "";
            document.getElementById("password").value = "";
        }
    </script>
</head>
<body onload="clearFormFields()">
    <div class="error-page-int">
    <form action="Login" method="POST">
      <h2>Quiz Login</h2>
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required autocomplete="new-password">
      </div>
      <div class="form-group">
        <button id="login" type="submit">Login</button>
      </div>
    </form>
  </div>
</body>
</html>