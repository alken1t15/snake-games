import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    Setting setting;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 25;
    static final int Game_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[Game_UNITS];
    final int y[] = new int[Game_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    int bananaX;
    int bananaY;
    char direction = 'D';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        newBanana();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }


    public void draw(Graphics g) {

        setting = new Setting();
        if (running) {

            if (setting.setPole()) {
                for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                    g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
                }
            }


            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            g.setColor(Color.yellow);
            g.fillOval(bananaX, bananaY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    if (setting.setRainbowSnake()) {
                        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    }
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score : " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score : " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }


    }

    public void newApple() {
        appleX = random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

    }

    public void newBanana() {
        bananaX = random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bananaY = random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'W':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'S':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'A':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'D':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkBanana() {
        if ((x[0] == bananaX) && (y[0] == bananaY)) {
            bodyParts += 3;
            applesEaten++;
            newBanana();
        }
    }

    public void checkCollisions() {
        //checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //check if head touches left boreder
        if (x[0] < 0) {
            running = false;
        }
        //check if head touches right boreder
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //check if head touches top border
        //check if head touches left boreder
        if (y[0] < 0) {
            running = false;
        }
        //check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        this.setFocusable(false);
        this.setVisible(false);
        GameFrame.frame.setVisible(false);
        //   gameFrame.frame.setVisible(true);
        GameOver gameOver = new GameOver();
        gameOver.gameOver(applesEaten);

        //Score

       /* g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score : " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score : " + applesEaten))/2,g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
        playMusic();

        */


    }

    public void playMusic() {
        Sequencer player = null;
        try {
            player = MidiSystem.getSequencer();
            player.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track track = seq.createTrack();

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);

            player.setSequence(seq);

            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkBanana();
            checkCollisions();
        }
        repaint();
    }

    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 'D') {
                direction = 'D';
            } else if (e.getKeyCode() == 'S') {
                direction = 'S';
            } else if (e.getKeyCode() == 'A') {
                direction = 'A';
            } else if (e.getKeyCode() == 'W') {
                direction = 'W';
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'D') {
                        direction = 'A';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'A') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 's') {
                        direction = 'W';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'W') {
                        direction = 'S';
                    }
                    break;
            }
        }
    }
}