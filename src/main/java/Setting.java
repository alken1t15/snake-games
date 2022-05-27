import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Setting {
    JFrame frame;
    JButton jButton;
    JCheckBox jCheckBox;
    JCheckBox jCheckBox2;
    static String onOrOff = "off";
    static String snakeColor = "off";

    public void setting() {
        frame = new JFrame();
        JPanel jPanel = new JPanel();
        jButton = new JButton("Назад");
        jCheckBox = new JCheckBox("Настройка полей(Поставьте галочку если включить)");
        jCheckBox2 = new JCheckBox("Разноцветная змейка");
        jCheckBox.addItemListener(new CheckBox());
        jButton.addActionListener(new ButtonBack());
        jCheckBox2.addItemListener(new CheckBox2());
        jPanel.add(jCheckBox2);
        jPanel.add(jCheckBox);
        jPanel.add(jButton);
        frame.getContentPane().add(BorderLayout.CENTER, jPanel);
        frame.setTitle("Настройки");
        frame.setSize(1000, 1000);
        frame.setResizable(false);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public boolean setPole() {
        if (onOrOff.equals("on")) {
            return true;

        }
        return false;
    }


    public boolean setRainbowSnake() {
        if (snakeColor.equals("on")) {
            return true;
        }
        return false;
    }

    class ButtonBack implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Menu menu = new Menu();
            frame.setVisible(false);
            menu.menu();
            menu.jFrame.setVisible(true);

        }
    }

    class CheckBox implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (jCheckBox.isSelected()) {
                onOrOff = "on";
            } else {
                onOrOff = "off";
            }
        }
    }

    class CheckBox2 implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (jCheckBox2.isSelected()) {
                snakeColor = "on";
            } else {
                snakeColor = "off";
            }
        }
    }
}