const questions = [
    {
        question: "What is the capital of France??",
        answers: [
            {text: "Rome", correct: false},
            {text: "Madrid", correct: false},
            {text: "Paris", correct: true},
            {text: " Berlin", correct: false}
        ]
    },
    {
        question: "Who wrote the famous play Romeo and Juliet?",
        answers: [
            {text: "William Shakespeare", correct: true},
            {text: "Jane Austen", correct: false},
            {text: " Charles Dickens", correct: false},
            {text: " F. Scott Fitzgerald", correct: false}
        ]
    },
    {
        question: "What is the tallest mountain in Africa?",
        answers: [
            {text: " Mount Kilimanjaro", correct: true},
            {text: "Mount Everest", correct: false},
            {text: "Mount McKinley", correct: false},
            {text: "Mount Elbrus", correct: false}
        ]
    },
    {
        question: "Who is the author of the Harry Potter book series?",
        answers: [
            {text: "J.R.R. Tolkien", correct: false},
            {text: "J.K. Rowling", correct: true},
            {text: "George Orwell", correct: false},
            {text: "Agatha Christie", correct: false}
        ]
    },
    {
        question: "Which planet is known as the Red Planet?",
        answers: [
            {text: "Venus", correct: false},
            {text: "Mars", correct: true},
            {text: "Jupiter", correct: false},
            {text: "Saturn", correct: false}
        ]
    },
    {
        question: "Who is the author of To Kill a Mockingbird?",
        answers: [
            {text: "Harper Lee", correct: true},
            {text: "J.K. Rowling", correct: false},
            {text: "Ernest Hemingway", correct: false},
            {text: "George Orwell", correct: false}
        ]
    },
    {
        question: "Who was the first woman to win a Nobel Prize?",
        answers: [
            {text: "Marie Curie", correct: true},
            {text: "Mother Teresa", correct: false},
            {text: "Ada Lovelace", correct: false},
            {text: "Rosalind Franklin", correct: false}
        ]
    },
    {
        question: "Who painted the Mona Lisa?",
        answers: [
            {text: "Pablo Picasso", correct: false},
            {text: "Leonardo da Vinci", correct: true},
            {text: "Vincent van Gogh", correct: false},
            {text: "Michelangelo", correct: false}
        ]
    },
    {
        question: "What is the largest planet in our solar system?",
        answers: [
            {text: "Earth", correct: false},
            {text: "Mars", correct: false},
            {text: "Jupiter", correct: true},
            {text: "Venus", correct: false}
        ]
    },
    {
        question: "Who is the Greek god of the sea?",
        answers: [
            {text: "Zeus", correct: false},
            {text: "Hades", correct: false},
            {text: "Poseidon", correct: true},
            {text: "Apollo", correct: false}
        ]
    }
    
];


let timer; 
let timeLeft = 30;

function startTimer() 
{
    clearInterval(timer);
    updateTimerDisplay();

    timer = setInterval(function () 
    {
        if (timeLeft <= 0) 
        {
            clearInterval(timer);
            handleNextButton();
        } else 
        {
            timeLeft--;
            updateTimerDisplay();
        }
    }, 1000);
}

function updateTimerDisplay() 
{
    const timerDisplay = document.getElementById("timer");
        timerDisplay.textContent = timeLeft.toLocaleString('en-US', {minimumIntegerDigits: 2});
;
}

startTimer();

function shuffleArray(array) 
{
    for (let i = array.length - 1; i > 0; i--) 
    {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

shuffleArray(questions);


const questionElement = document.getElementById("question");
const answerButtons = document.getElementById("answer-buttons");
const nextButton = document.getElementById("next-button");

let currentQuestionIndex = 0;
let score = 0;

function startQuiz()
{
    currentQuestionIndex = 0;
    score = 0;
    nextButton.innerHTML = "Next";
    showQuestion();
}

function showQuestion()
{
    resetState();
    let currentQuestion = questions[currentQuestionIndex];
    questionElement.innerHTML = currentQuestion.question;

    currentQuestion.answers.forEach(answer => 
        {
        const button = document.createElement("button");
        button.innerHTML = answer.text;
        button.classList.add("btn");
        answerButtons.appendChild(button);
        if(answer.correct)
        {
            button.dataset.correct = answer.correct;
        }
        button.addEventListener("click", selectAnswer);
        });
        document.getElementById("question-number").textContent = currentQuestionIndex + 1;
        document.getElementById("total-questions").textContent = questions.length;
}

function resetState()
{
    nextButton.style.display = "none";
    while(answerButtons.firstChild)
    {
        answerButtons.removeChild(answerButtons.firstChild);
    }
}

function selectAnswer(e)
{
    const selectedBtn = e.target;
    const isCorrect = selectedBtn.dataset.correct === "true";
    if(isCorrect)
    {
        selectedBtn.classList.add("correct");
        score++;
    }
    else
    {
        selectedBtn.classList.add("incorrect");
    }
    Array.from(answerButtons.children).forEach(button => 
        {
        if(button.dataset.correct === "true")
        {
            button.classList.add("correct");
        }
        button.disabled = true;
        });
        nextButton.style.display = "block";
}

function showScore() 
{
    resetState();
    window.location.href = "Result.jsp";
}

function submitScore() 
{
    const form = document.createElement("form");
    form.method = "POST";
    form.action = "SubmitScore";

    const scoreInput = document.createElement("input");
    scoreInput.type = "hidden";
    scoreInput.name = "score";
    scoreInput.value = score;

    form.appendChild(scoreInput);
    document.body.appendChild(form);
    form.submit();
}

function handleNextButton() {
    clearInterval(timer);
    timeLeft = 30;
    currentQuestionIndex++;

    if (currentQuestionIndex < questions.length) {
        showQuestion();
        startTimer();
    } else {
        nextButton.style.display = "none";
        const submitButton = document.createElement("button");
        submitButton.innerHTML = "Submit";
        submitButton.classList.add("submit-button");
        submitButton.addEventListener("click", submitScore);
        answerButtons.appendChild(submitButton);
    }
}

nextButton.addEventListener("click", () => {
    if (currentQuestionIndex < questions.length) {
        handleNextButton();
    } else {
        startQuiz();
    }
});

startQuiz();



