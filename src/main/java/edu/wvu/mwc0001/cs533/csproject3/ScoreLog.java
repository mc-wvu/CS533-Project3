/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.wvu.mwc0001.cs533.csproject3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mc
 */
public class ScoreLog {

    private static final String SCORES_FILE = "game_scores.ser";

    ScoreLog() throws IOException {
        File file = new File(SCORES_FILE);

        if (!(file.exists())) {
            file.createNewFile();
        }
    }

    private static void checkFile() {
        File file = new File(SCORES_FILE);
        if (!(file.exists())) {
            try {file.createNewFile();
                List<Score> scores = new ArrayList<>();
                scores.clear();
                saveScores(scores);
            } catch (IOException e) {
                System.out.println("Unable to create score file");
            }
        }
    }

    public static void saveScores(List<Score> scores) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SCORES_FILE))) {
            outputStream.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Score> loadScores() {
        checkFile();
        List<Score> scores = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(SCORES_FILE))) {
            scores = (List<Score>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static void addScore(Score currentScore) {
        List<Score> scores = loadScores();
        scores.add(currentScore);
        saveScores(scores);
    }

    public static List<Score> getUsersScores(String username) {
        List<Score> scoresInFile = loadScores();
        List<Score> usersScores = scoresInFile.stream()
                .filter(entry -> entry.getUsername().equals(username))
                .collect(Collectors.toList());
        return usersScores;
    }

}
