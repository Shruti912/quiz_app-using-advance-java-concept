<%@ page import="java.util.List, Stavan.ScoreResult" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Results</title>
    <link rel="stylesheet" href="Quiz.css" />
</head>
<body>
    <h1>Quiz Scores</h1>
    <div class="table-container">
    <table>
        <tr>
            <th>Username</th>
            <th>Correct Answers</th>
            <th>Wrong Answers</th>
        </tr>
        <%
        List<ScoreResult> scoreResults = (List<ScoreResult>) request.getAttribute("scoreResults");
        if (scoreResults != null) {
            for (ScoreResult scoreResult : scoreResults) {
        %>
        <tr>
            <td><%= scoreResult.getUsername() %></td>
            <td><%= scoreResult.getCorrectAnswers() %></td>
            <td><%= scoreResult.getWrongAnswers() %></td>
        </tr>
        <%
            }
        }
        %>
    </table>
    </div>
</body>
</html>