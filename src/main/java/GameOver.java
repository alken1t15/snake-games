import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver {
    JFrame frame;
    JPanel jPanel;
    JLabel jLabel;
    JPanel jPanel2;
    JButton jButton;
    JButton jButton2;

    public void gameOver(int eat) {
        jLabel = new JLabel();
        jPanel = new JPanel();
        jPanel2 = new JPanel();
        frame = new JFrame();
        jButton = new JButton("Начать заново игру");
        jButton2 = new JButton("Вернутться в главное меню");
        jPanel2.setBackground(Color.black);
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        jLabel.setText("Количество очков : " + eat);
        //Устанавливает цвет
        jLabel.setForeground(Color.red);
        //Устанавливает шрифт
        jLabel.setFont(new Font("Ink Free", Font.BOLD, 40));
        jPanel.setBackground(Color.black);
        jPanel.add(jLabel);
        jButton.addActionListener(new ButtonOne());
        jButton2.addActionListener(new ButtonTwo());
        jPanel2.add(jButton);
        jPanel2.add(jButton2);
        frame.getContentPane().add(BorderLayout.CENTER, jPanel);
        frame.getContentPane().add(BorderLayout.EAST, jPanel2);
        frame.setTitle("Snake");
        frame.setSize(1000, 1000);
        frame.setResizable(false);
        // jFrame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class ButtonOne implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new GameFrame();
        }
    }

    class ButtonTwo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new Menu().menu();
        }
    }
}