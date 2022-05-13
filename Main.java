import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();

        gameFrame.setSize(850, 800);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null); // center on screen
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
    }
}