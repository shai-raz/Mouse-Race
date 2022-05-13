import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame implements ActionListener {
    private final JLabel scoreLabel;
    private final JButton startButton;
    private final GamePanel gamePanel;
    private final Game game;

    public GameFrame() {
        super("Mouse Race");
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(8, 0, 0, 0);

        scoreLabel = new JLabel("Score: " + 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(scoreLabel, constraints);

        startButton = new JButton("Start");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(startButton, constraints);
        startButton.addActionListener(this);

        gamePanel = new GamePanel();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipadx = GamePanel.WIDTH;
        constraints.ipady = GamePanel.HEIGHT;
        add(gamePanel, constraints);

        game = new Game(gamePanel, scoreLabel, startButton);
    }


    private void startGame() {
        gamePanel.initElements();
        game.startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startGame();
        }
    }
}