import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Menu   {
   JLabel jLabel;
    JFrame jFrame;
    JPanel jPanel;
    JPanel jPanel2;
    public void menu(){
        jFrame = new JFrame();
        jLabel = new JLabel();
        jPanel = new JPanel();
        jPanel2 = new JPanel();
        jFrame.setTitle("Главное меню");
        Image imageIcon = new ImageIcon("2f8471d99987463bc56bd4bf5a6d80bd.jpg").getImage();
       jLabel.setIcon(new ImageIcon("2f8471d99987463bc56bd4bf5a6d80bd.jpg"));
       jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.setBackground(Color.black);
        jFrame.setIconImage(imageIcon);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton jButton = new JButton("Начать игру");
        JButton jButton2 = new JButton("Настройки");
        jButton.addActionListener(new MyButton());
        jButton2.addActionListener(new MyButton2());
     jPanel.add(jLabel);
       jPanel2.add(jButton);
       jPanel2.add(jButton2);
        jFrame.getContentPane().add(BorderLayout.CENTER, jPanel);
        jFrame.getContentPane().add(BorderLayout.SOUTH, jPanel2);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    class MyButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new GameFrame();
            jFrame.setVisible(false);
        }
    }
    class MyButton2 implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Setting setting = new Setting();
            setting.setting();
            boolean galocka = setting.setPole();
            setting.jCheckBox.setSelected(galocka);
            boolean colorSnake = setting.setRainbowSnake();
            setting.jCheckBox2.setSelected(colorSnake);
            jFrame.setVisible(false);
        }
    }
}