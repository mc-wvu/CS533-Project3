/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.wvu.mwc0001.cs533.csproject3;
import java.util.List;
import java.util.ArrayList;
/**
 * max number of questions is 10000
 * @author mc
 */
public class QuestionAnswer {
    
    private int QuestionNumber; 
    private List<String> question = new ArrayList<>();
    private int correctAnswer; 
    private List<String> answers = new ArrayList<>();
  
    QuestionAnswer(){
        QuestionNumber=0;
        question.clear();
        answers.clear();
        correctAnswer = 0;
    }
 
    QuestionAnswer(int questionNumberToSave, List<String>  questionToSave,int correctAnswerToSave,List<String> answersToSave){
       this.question = questionToSave; 
       this.answers = answersToSave;
       this.correctAnswer = correctAnswerToSave;
       this.QuestionNumber = questionNumberToSave;
    
    }

    List<String> getQuestion(){
    // string of 75 chars per line but up to 10 lines. 
    return question;
}
    String getQuestionString(){
     
    String arraylist = String.join(System.getProperty("line.separator"), this.question);
    return arraylist;
    }
    void setQuestion(List<String> questionToAdd){
        this.question = questionToAdd;
    }
    
    boolean checkAnswer(int answer){
        if (answer == this.correctAnswer){
            return true;
        }else {
            return false; 
        }
    }
            
    List<String> getAnswers(){
    // a single line up to 10 
        return answers;
    }
    
    String getCorrectAnswerString(){
        return this.answers.get(this.correctAnswer - 1);
    }
    
    void setAnswers(List<String> answerToAdd){
        this.answers = answerToAdd; 
    }
    
    int getCorrectAnswer(){
    return correctAnswer;
    }
    
    void setCorrectAnswer(int correctAnswerToAdd){
       this.correctAnswer = correctAnswerToAdd; 
    }
    
}
