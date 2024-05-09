package Stavan;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Login extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/quiz";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "");

            String sql = "SELECT * FROM tbl_quiz WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) 
            {
                HttpSession session = req.getSession();
                session.setAttribute("loggedIn", true);
                session.setAttribute("username", username);
                res.sendRedirect("Quiz.jsp"); 
            } 
            else 
            {
                out.println("<h2>Login Failed. Invalid username or password.</h2>");
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}