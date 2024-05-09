package Stavan;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SubmitScore extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/quiz";
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException
    {
        int score = Integer.parseInt(req.getParameter("score"));
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        int wrongAnswers = 10 - score;
   
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(url, "root", "");
            
            String checkSql = "SELECT * FROM results WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String updateSql = "UPDATE results SET correct_answers = ?, wrong_answers = ? WHERE username = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, score);
                updateStmt.setInt(2, wrongAnswers);
                updateStmt.setString(3, username);
                updateStmt.executeUpdate();
                updateStmt.close();
            } else {
                String insertSql = "INSERT INTO results (username, correct_answers, wrong_answers) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, username);
                insertStmt.setInt(2, score);
                insertStmt.setInt(3, wrongAnswers);
                insertStmt.executeUpdate();
                insertStmt.close();
            }

            rs.close();
            checkStmt.close();
            conn.close();
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            e.printStackTrace();
        }
        res.sendRedirect("Result");
    }
}