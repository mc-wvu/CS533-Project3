/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.wvu.mwc0001.cs533.csproject3;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.commons-lang3 ;

/**
 *
 * @author mc
 */
public class CMDScoreViewer {
    
    
   
public static void displayUsersScores(String username){
   List<Score> usersScores = ScoreLog.getUsersScores(username);
   Score thisScore;
          System.out.println("Scores For user: "+username);
          System.out.println("|    Date    | # Asked | # Correct |    Time   | QuestionFile") ;         
   for (int i = 0; i < usersScores.size(); i++){
        thisScore = usersScores.get(i);
       System.out.println("| "+thisScore.getGameDateTime()+" | "+ StringUtils.leftPad(String.valueOf(thisScore.getNumberOfQuestionsAnswered()),5) +"   |    "+StringUtils.leftPad(String.valueOf(thisScore.getTotalCorrect() ),5)+"  |"+StringUtils.leftPad(thisScore.getGameDurationSecondsString() ,10) +" | "+thisScore.getQuestionFilename());

    }
   } 
    
public static void displayAllUsersScores(){
     List<Score> usersScores = ScoreLog.loadScores();
   Score thisScore;
         System.out.println("|     Username |    Date    | # Asked | # Correct |    Time   | QuestionFile") ;         
   for (int i = 0; i < usersScores.size(); i++){
        thisScore = usersScores.get(i);
       System.out.println("| "+StringUtils.leftPad(thisScore.getUsername(), 12)+" | "+thisScore.getGameDateTime()+" | "+ StringUtils.leftPad(String.valueOf(thisScore.getNumberOfQuestionsAnswered()),5) +"   |    "+StringUtils.leftPad(String.valueOf(thisScore.getTotalCorrect() ),5)+"  |"+StringUtils.leftPad(thisScore.getGameDurationMinutesSecondsString(),10) +" | "+thisScore.getQuestionFilename());

    }   
   
}

}
