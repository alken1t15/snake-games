import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame {
    static JFrame frame;

    GameFrame() {
        frame = new JFrame();
        frame.add(new GamePanel());
        frame.setTitle("Snake");
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}