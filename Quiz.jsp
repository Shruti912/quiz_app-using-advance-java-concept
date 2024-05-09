<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Quick Quiz</title>
    <link rel="stylesheet" href="Quiz.css" />
  </head>
  <body>
        <div class="menu-bar">
            <button id="home-button"><a href="http://localhost:8080/QuizApp/">Home</a></button>
            <button id="logout-button"><a href="Login.jsp">Logout</a></button>
        </div>                      
        <div class="name">Online Quiz</div>
        <div class="main">
            <div class="quiz">               
                <div class="question-number">Question <span id="question-number">1</span>/<span id="total-questions">10</span>
                    <div class="timer" id="timer"></div> 
                    <hr class="line">
                </div>
                <h1 id="question">Que</h1>
                <div id="answer-buttons">
                    <button class="btn">Option 1</button>
                    <button class="btn">Option 2</button>
                    <button class="btn">Option 3</button>
                    <button class="btn">Option 4</button>
                </div>
                <button id="next-button">Next</button>
            </div>
        </div>
      <script src="Logic.js"></script>
  </body>
</html>
