/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.wvu.mwc0001.cs533.csproject3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * created to represent the question file and load up to 10000 questions. 
 * @author mc
 */
public class QuestionFile {
 //   String currentLine; 
    ArrayList<QuestionAnswer> QuestionFileLoad;
    String loadedFile;
    
    QuestionFile(String filepath) {
        this.QuestionFileLoad = new ArrayList<>();
        this.loadedFile = filepath; 
        
        // Passing the path to the file as a parameter
        Path fileToLoad = Paths.get(filepath);
        List<String> loadedFile;
        try{
            loadedFile = Files.readAllLines(fileToLoad, StandardCharsets.UTF_8);
        }catch(Exception e){
            return;
        }
        loadedFile.removeIf(n -> n.startsWith("*"));
        loadedFile.removeIf(n -> n.isBlank());
        //loadedFile.forEach(line -> System.out.println(line));
        int fileIndex;
        int questionAnswerIndex = 0;
        int questionIndex;
        int answerIndex;
        Integer correctAnswer;
        // System.out.println("size of list "+loadedFile.size());
        for(fileIndex=0;fileIndex<loadedFile.size();fileIndex++){
            
            loadquestion:  if(loadedFile.get(fileIndex).startsWith("@Q")){
                QuestionAnswer currentQuestionAnswer = new QuestionAnswer() ;
                List<String> question = new ArrayList<>();
                List<String> answer = new ArrayList<>();
                // question.clear();
                // we have the start of a new question
                //fileIndex++;
                for(questionIndex=1;(fileIndex+questionIndex)<loadedFile.size();questionIndex++)
                {
                    if(!loadedFile.get(questionIndex+fileIndex).startsWith("@A")){
                        //System.out.println((fileIndex+questionIndex)+" This is a question "+loadedFile.get(fileIndex+questionIndex));
                        question.add(loadedFile.get(fileIndex+questionIndex));
                    }else{
                        answer.clear();
                        //   System.out.println("We have an Answer");
                        fileIndex=fileIndex+questionIndex;
                        //we have found the start of the answer section
                        fileIndex++;
                        // System.out.println(fileIndex+ " This is the correct anwser number "+loadedFile.get(fileIndex));
                        correctAnswer = Integer.parseInt(loadedFile.get(fileIndex));
                        fileIndex++;
                        for(answerIndex=0;fileIndex+answerIndex<loadedFile.size();answerIndex++){
                            if(!loadedFile.get(fileIndex+answerIndex).startsWith("@E")){
                                // System.out.println((fileIndex+answerIndex)+" This is an answer "+loadedFile.get(fileIndex+answerIndex));
                                answer.add(loadedFile.get(fileIndex+answerIndex));
                            }else
                            {  fileIndex=fileIndex+answerIndex;
                            //System.out.println(fileIndex+" We are at the end of a question element");
                            currentQuestionAnswer.setQuestion(question);
                            currentQuestionAnswer.setAnswers(answer);
                            currentQuestionAnswer.setCorrectAnswer(correctAnswer);
                            // System.out.println("SAve question to QuestionfileOBject "+currentQuestionAnswer.getQuestion().toString());
                            // System.out.println("Save Answer to QuestionFileObject"+currentQuestionAnswer.getAnswers().toString());
                            
                            QuestionFileLoad.add( currentQuestionAnswer);
                            
                            break loadquestion ;
                            // we should completely break
                            }
                        }
                    }
                    
                }
            }

        }
        Collections.shuffle(this.QuestionFileLoad);
    }
    void shuffleQuestionFile(){
        Collections.shuffle(this.QuestionFileLoad);   
    }
    QuestionAnswer getNextQuestionAnswer(int nextQuestion){
        return QuestionFileLoad.get(nextQuestion);
    }
    
    String getFilename(){
        return this.loadedFile;
    }
                   
        }
