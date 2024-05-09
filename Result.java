package Stavan;

import Stavan.ScoreResult; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Result extends HttpServlet {
    String url = "jdbc:mysql://localhost:3306/quiz";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        List<ScoreResult> scoreResults = new ArrayList<>();
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(url, "root", "");
            
            String sql = "SELECT username, correct_answers, wrong_answers FROM results";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                int correctAnswers = rs.getInt("correct_answers");
                int wrongAnswers = rs.getInt("wrong_answers");
                ScoreResult scoreResult = new ScoreResult(username, correctAnswers, wrongAnswers);
                scoreResults.add(scoreResult);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) 
        {
            e.printStackTrace();
        }

        req.setAttribute("scoreResults", scoreResults);
        req.getRequestDispatcher("Result.jsp").forward(req, res);
    }
}