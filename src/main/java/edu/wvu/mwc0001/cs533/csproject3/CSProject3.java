/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.wvu.mwc0001.cs533.csproject3;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.cli.*;

/**
 *
 * @author mc
 */
public class CSProject3 {

    public static void main(String[] args) {
        Path questionFileToLoad = null;
        String questionFileArg = "";
        QuestionFile questionBatch;
        int numberOfQuestionsToAsk = 0;
        int gameTimer = 0;

        String username = System.getProperty("user.name");

        Options cmdOptions = new Options();
        cmdOptions.addOption("h", "help", false, "Help Information");
        cmdOptions.addOption("s", "score", false, "Show users score and exit");
        cmdOptions.addOption("f", "file", true, "Path to question file");
        cmdOptions.addOption("t", "time", true, "How long to run the quiz, default is no limit");
        cmdOptions.addOption("n", "number", true, "Number of Questions to ask, default is whole file");
        cmdOptions.addOption("a", "admin", true, "Supply Password to displays scores for all users");

        CommandLineParser cmdParser = new DefaultParser();
        try {
            CommandLine cmd = cmdParser.parse(cmdOptions, args);
            if (cmd.hasOption("h")) {
                printCMDHelp(cmdOptions);
                return;
            }
            if (cmd.hasOption("s")) {
                CMDScoreViewer.displayUsersScores(username);
                return;
            }

            if (cmd.hasOption("a")) {
                if ("Security".equals(cmd.getOptionValue("a"))) {
                    CMDScoreViewer.displayAllUsersScores();
                } else {
                    System.out.println("Incorrect password");
                }
                return;
            }

            if (cmd.hasOption("f")) {
                // has a file, lets setup the quiz game
                try {
                    questionFileToLoad = Paths.get(cmd.getOptionValue("f"));
                    File selectedFile = questionFileToLoad.toFile();
                    questionBatch = new QuestionFile(selectedFile.toString());
                    if (cmd.hasOption("t")) {
                        gameTimer = Integer.parseInt(cmd.getOptionValue("t"));
                    } else {
                        gameTimer = 0;
                    }
                    if (cmd.hasOption("n")) {
                        numberOfQuestionsToAsk = Integer.parseInt(cmd.getOptionValue("n"));
                    } else {
                        numberOfQuestionsToAsk = 0;
                    }
                    if (!questionBatch.QuestionFileLoad.isEmpty()) {
                        QuestionCMDApp commandLine = new QuestionCMDApp(questionBatch, questionFileToLoad.toString(), username);
                        commandLine.startGame(numberOfQuestionsToAsk, gameTimer);
                    } else {
                        System.out.println("Invalid or empty file");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid File");
                    e.printStackTrace();
                }
            } else {
                printCMDHelp(cmdOptions);
            }

        } catch (ParseException e) {
            printCMDHelp(cmdOptions);

        }

    }

    static void printCMDHelp(Options cmdOptions) {
        System.out.println("Quiz App Version 3a - Michael W. Cole");
        HelpFormatter cmdFormatter = new HelpFormatter();
        cmdFormatter.printHelp("java quiz app", cmdOptions);

    }

}
