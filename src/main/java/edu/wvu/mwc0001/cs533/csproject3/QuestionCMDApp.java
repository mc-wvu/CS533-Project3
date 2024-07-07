/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.wvu.mwc0001.cs533.csproject3;

import edu.wvu.mwc0001.cs533.csproject3.GameTimer.TimerListener;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mc
 */
public class QuestionCMDApp implements TimerListener{
    final private QuestionFile cmdQuestionBatch;
    int questionFileSize = 0; 
    private QuestionAnswer currentQuestionAnswer;
    int gameElapsedTime = 0;
    Score gameScore = new Score();
    GameTimer timer = new GameTimer( this);
    int gameTimerChoosen;
    String username;
    String questionFileName;
    
    /**
     * Creates Interactive Instance of the CMDQuestion App
     * the game exists in the startGame method.  
     * @param questionBatch
     * @param questionFileName
     * @param username
     */
    public  QuestionCMDApp (QuestionFile questionBatch,String questionFileName, String username) {
   this.username = username; 
   this.questionFileName = questionFileName;
    //QuestionAnswer currentQuestionAnswer = new QuestionAnswer();
    this.cmdQuestionBatch = questionBatch;
    questionFileSize = this.cmdQuestionBatch.QuestionFileLoad.size();
    this.gameScore.resetScore();
    
  
    //this.cmdQuestionBatch = questionBatch; 
        //questionBatch.shuffleQuestionFile();
    
    }
    
    /**
     * Starts and Runs command line quiz game
     * the game ends when the number of questions is hit or a non integer char is
     * given as answer
     * @param numberOfQuestions
     * if param is 0 then the whole quiz file is used. 
     * @param gameTimer 
     * if gameTimer is 0 then no timer; 
     */
    
    public void startGame(int numberOfQuestions,int gameTimer){
        this.gameTimerChoosen = gameTimer;
         //GameTimer timer = new GameTimer(100, this);
         this.gameScore.startGame(username,questionFileName );
         if(gameTimerChoosen > 0){
         timer.startTimer(gameTimer);
         }
        String enteredValueString;
        Scanner sc = new Scanner(System.in);
        
        if(numberOfQuestions == 0 || numberOfQuestions >= this.questionFileSize)
        {
            numberOfQuestions = this.questionFileSize; 
        }
        
        this.gameScore.resetScore();
        this.gameScore.setStartTime();
        this.cmdQuestionBatch.shuffleQuestionFile();
        
        System.out.println("Welcome to Quiz App It will be asking "+numberOfQuestions+" Questions - Press Enter To Start - Type x to exit anytime");
        enteredValueString = sc.nextLine().strip();
        if(enteredValueString.contentEquals("x")){
            return;
        }
        for(int questionIndex=0; questionIndex < numberOfQuestions ; questionIndex++){
            System.out.println("");
            this.currentQuestionAnswer =  cmdQuestionBatch.getNextQuestionAnswer(questionIndex);
            displayQuestion(this.currentQuestionAnswer.getQuestionString());
            displayAnswers(this.currentQuestionAnswer.getAnswers());
            System.out.print("Your Answer: ");
            enteredValueString = sc.nextLine().strip();
            // if String is Integer check answer, otherwise assume they typed exit or quit and want out, end program
            try {
                checkCMDAnswer(Integer.parseInt(enteredValueString));
                //choosenAnswer--;
                //System.out.println("You have "+ timer.getRemainingTime()+" seconds remaining"
                 //       + "");
            }catch(Exception e){
          // They entered something other then a number so assume they want to quit and exit. 
                //displayCurrentStats();
                //return ;
            endGame();
            }    
        }
        // Hit the max questions
        endGame();
        //
        displayCurrentStats();
        
    }
    
    private void checkCMDAnswer(int choosenAnswer){
       if(this.currentQuestionAnswer.checkAnswer(choosenAnswer)){
           System.out.println( choosenAnswer + " Was the correct Answer");
        this.gameScore.addAnsweredCorrect();
                }else{
                    System.out.println("The Correct Answer was: "+this.currentQuestionAnswer.getCorrectAnswerString());
                this.gameScore.addAnsweredInCorrect(choosenAnswer);
                }
       if(this.gameTimerChoosen > 0){
          System.out.println("You have "+this.gameElapsedTime+" seconds remaining");
       }
          // System.out.println("You have "+ );
    }
         
    private void displayQuestion(String question){
        System.out.println("Question \n "+ question);
    }

    private void displayAnswers(List<String> answers){
        for (int answersIndex=0;answersIndex<answers.size();answersIndex++){            
            System.out.println("("+(answersIndex+1)+"): "+ answers.get(answersIndex));
        }
        //System.out.println("Your Answer: ");
    }
    private void endGame(){
        displayCurrentStats();
        this.timer.stopTimer();
        ScoreLog.addScore(gameScore);
        System.out.println("Past Scores");
        CMDScoreViewer.displayUsersScores(username);
        System.exit(0);
    }        
    private void displayCurrentStats(){
        System.out.println("");
        System.out.println("Currently have Answered "+this.gameScore.getNumberOfQuestionsAnswered());
        System.out.println("and of this you have gotten correct "+this.gameScore.getTotalCorrect());
        System.out.println("For a percentage of "+ ""+this.gameScore.percentCorrectString());
        System.out.println("You have played for "+this.gameScore.getGameDurationMinutesSecondsString()+" minutes and seconds");
        System.out.println("");
    
    }

    @Override
    public void onTimerExpired() {
      System.out.println("Time Expired Game Over");
      endGame();
      //   this.displayCurrentStats();
    //   System.exit(0);
    }

    @Override
    public void onTimerTick(int secondsRemaining) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //System.out.println("System");
    this.gameElapsedTime = secondsRemaining;
   //
   //System.out.println("Seconds Remaining "+ secondsRemaining);
    }

}
