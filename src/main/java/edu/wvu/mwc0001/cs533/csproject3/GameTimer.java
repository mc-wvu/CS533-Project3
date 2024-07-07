/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.wvu.mwc0001.cs533.csproject3;
import java.util.EventListener;
  import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer gameTimer;
    private TimerListener gameTimerListener;
    private int totalTime;
    private int elapsedTime;

    public GameTimer( TimerListener listener) {
        this.gameTimerListener = listener;
        this.totalTime = 0; 
        this.elapsedTime = 0;
    }

   /* public void startTimer(int seconds) {
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerExpired();
            }
        }, seconds * 1000);
    }
*/
        public void startTimer(int time) {
        this.elapsedTime = time;
        this.totalTime = time;
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
 //           private int elapsedTime;
            @Override
            public void run() {
               // System.out.print("elapsedTime "+elapsedTime);
                elapsedTime--;
                if (gameTimerListener != null) {
                    gameTimerListener.onTimerTick(elapsedTime);
                    
                }
                if (elapsedTime <= 0) {
                    stopTimer();
                    if (gameTimerListener != null) {
                        gameTimerListener.onTimerExpired();
                    }
                }
            }
        }, 1000, 1000); // Schedule to run every second
    }
    
    private void timerExpired() {
        System.out.println("Timer expired!");
        if (gameTimerListener != null) {
            gameTimerListener.onTimerExpired(); // Notify the listener
        }
    }

    public void stopTimer() {
        if (gameTimer != null) {
            gameTimer.cancel();
        }
    }
    
public int getElapsedTime(){
    return this.elapsedTime;
}

public int getRemainingTime(){
    return this.totalTime - this.elapsedTime;
}    

public interface TimerListener extends EventListener {
    void onTimerTick(int secondsRemaining);
    void onTimerExpired();
}

    
 }



        

