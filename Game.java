import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Game {
    private int score;

    private final GamePanel gamePanel;
    private final JLabel scoreLabel;
    private final JButton startButton;

    public Game(GamePanel gamePanel, JLabel scoreLabel, JButton startButton) {
        this.gamePanel = gamePanel;
        this.scoreLabel = scoreLabel;
        this.startButton = startButton;
    }

    public void startGame() {
        score = 0;
        gamePanel.setGameOver(false);
        gamePanel.initElements();
        startButton.setEnabled(false);

        // score timer, increase every one second
        Timer scoreTimer = new Timer();
        scoreTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scoreLabel.setText("Score: " + score++);
            }
        }, 0, 1000);

        // start game loop thread
        Thread gameLoop = new Thread() {
            @Override
            public void run() {
                while (true) {
                    gamePanel.moveElements();
                    gamePanel.repaint();

                    for (Element element : gamePanel.getElements()) {
                        if (element.hitTarget(gamePanel.getMousePos())) {
                            if (element instanceof Escaper) {
                                score += 5;
                                scoreLabel.setText("Score: " + score);
                            } else if (element instanceof Chaser || element instanceof Randomer) {
                                scoreTimer.cancel();
                                gamePanel.setGameOver(true);
                                startButton.setEnabled(true);
                                interrupt();
                                return;
                            }
                        }
                    }

                    try {
                        Thread.sleep(7); // game speed
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        };

        gameLoop.start();
    }
}
