/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.wvu.mwc0001.cs533.csproject3;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * Record the questions that have been answered 
 * along with current score 
 * number correct
 * questions that were wrong
 * 
 * 
 * @author mc
 */

public class Score implements Serializable {
    //   
    private int numberOfQuestionsAnswered=0; // if they skip a question. 
    //int[] QuestionsAnswered; // to allow history of questions
    //int[] AnwserChoosen; // for history
    private int numberCorrect=0;
    private int numberToAsk=0; 
    private int currentQuestion=0;
    private int questionsSkipped=0;
    private String username;
    private String questionFilename;
    //private int totalTimeSeconds; 
    ArrayList<Integer> QuestionsAnswered = new ArrayList<>();
    private Instant startTime = Instant.now();
    private Instant endTime = Instant.now();
    private final LocalDateTime gameDateTime = LocalDateTime.now();
   
    
    
    void AddResult(int QuestionNumber,int AnswerChoosen, int CorrectAnswer){
        this.numberToAsk--; 
        QuestionsAnswered.add(QuestionNumber);
        if (AnswerChoosen == CorrectAnswer){
            numberCorrect++;
        }
    }
    void startGame(String username,String filename){
        this.username = username;
        this.questionFilename = filename;
        this.startTime = Instant.now();
    }
    void setStartTime(){
        this.startTime = Instant.now();
    }
    
    void endGame(){
        this.endTime = Instant.now();
    }
       
    Duration getGameDuration(){
        return Duration.between(startTime,endTime);
    }
    
    long getGameDurationMinutes(){
        return Duration.between(startTime, endTime).toMinutes();
    }
    
    String getGameDurationMinutesString(){
        return Long.toString(Duration.between(startTime, endTime).toMinutes());
       // Duration.between(startTime, startTime)
    }
    
    String getGameDurationSecondsString(){
        return Long.toString(Duration.between(startTime, endTime).toSeconds());
    }
    
    String getGameDurationMinutesSecondsString(){
        String minutes = Long.toString(Duration.between(startTime, endTime).toMinutes());
        String seconds = Long.toString(Duration.between(startTime, endTime).toSecondsPart());
      return minutes + ":" + seconds;   
    }
   
    /*
    Duration getGameDuration(){
        return Duration.between(startTime, Instant.now());
    }
    
    long getGameDurationMinutes(){
        return Duration.between(startTime, Instant.now()).toMinutes();
    }
    
    String getGameDurationMinutesString(){
        return Long.toString(Duration.between(startTime, Instant.now()).toMinutes());
       // Duration.between(startTime, startTime)
    }
    
    String getGameDurationSecondsString(){
        return Long.toString(Duration.between(startTime, Instant.now()).toSeconds());
    }
    
    String getGameDurationMinutesSecondsString(){
        String minutes = Long.toString(Duration.between(startTime, Instant.now()).toMinutes());
        String seconds = Long.toString(Duration.between(startTime, Instant.now()).toSecondsPart());
      return minutes + ":" + seconds;   
    }
    */

    float percentCorrect(){
        return this.numberCorrect / this.numberOfQuestionsAnswered;
    }
    
    String percentCorrectString(){
        float percent = 0;
        if (this.numberOfQuestionsAnswered > 0){
            percent = 100 * this.numberCorrect / this.numberOfQuestionsAnswered;   
        }
        return String.format("%.0f%%",percent);
    }
    
    void addAnsweredCorrect(){
        this.numberCorrect++;
        this.numberOfQuestionsAnswered++;
    }
    
    void addAnsweredInCorrect(int wrongGuess){
        this.numberOfQuestionsAnswered++;
    }
        
    int getQuestionsToAskRemaining(){
        return numberToAsk-numberOfQuestionsAnswered;
    }
   
    int getTotalAnswer(int Question){
        return this.numberToAsk;
    }
    
    int getTotalCorrect(){
        return this.numberCorrect;  
    }
    
    int getCurrentQuestion(){
        return this.currentQuestion;
    }
    void setNumberToAsk(int numberToAsk){
        this.numberToAsk = numberToAsk;
    }
    
    void addQuestionsSkipped(){
        this.questionsSkipped++;
    }
    
    int getQuestionsSkipped(){
        return this.questionsSkipped;
    }
    String getQuestionsSkippedString(){
        return Integer.toString(this.questionsSkipped);
    }
    
    int getNumberOfQuestionsAnswered(){
        return this.numberOfQuestionsAnswered;
    }
    
    String getUsername(){
        return this.username;
    }
    String getQuestionFilename(){
        return this.questionFilename;
    }
    String getGameDateTime(){
        return gameDateTime.format(DateTimeFormatter.ISO_DATE);
    }
    
    int nextQuestion(){
        this.currentQuestion++;
        return this.currentQuestion;
    }

    void resetScore(){
        this.numberCorrect = 0;
        this.numberOfQuestionsAnswered = 0;
        this.numberToAsk = 0;
        this.currentQuestion = 0;
        this.questionsSkipped = 0;
    }
    
    int getQuestionsLeft(){
        return this.numberToAsk - this.numberOfQuestionsAnswered ;
    }
    
    void gameOver(){
        // any cleanup before we seralize the score 
        
    }
}
