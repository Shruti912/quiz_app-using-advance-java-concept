package Stavan; 

public class ScoreResult {
    private final String username;
    private final int correctAnswers;
    private final int wrongAnswers;

    public ScoreResult(String username, int correctAnswers, int wrongAnswers) 
    {
        this.username = username;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }

    public String getUsername() {
        return username;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }
}