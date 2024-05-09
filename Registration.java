package Stavan;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Registration extends HttpServlet
{
    String url = "jdbc:mysql://localhost:3306/quiz";
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
   
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(url, "root", "");
            
            String sql = "INSERT INTO tbl_quiz(username, email, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) 
            {
                HttpSession session = req.getSession();
                session.setAttribute("registered", true);
                session.setAttribute("username", username);
                res.sendRedirect("Quiz.jsp");
            } 
            else 
            {
                out.println("<h2>Registration Failed.</h2>");
            }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
   
    }
}