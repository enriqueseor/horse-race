import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class HorseRace {

    JFrame frame;
    JProgressBar horse1 = new JProgressBar(0, 100);
    JProgressBar horse2 = new JProgressBar(0, 100);
    JProgressBar horse3 = new JProgressBar(0, 100);
    JProgressBar horse4 = new JProgressBar(0, 100);
    JLabel msg = new JLabel("");
    static boolean startRaceIsPressed = false;
    static boolean resetRaceIsPressed = false;
    static int winnerHorse = 0;
    static boolean winner = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HorseRace window = new HorseRace();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HorseRace() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        horse1.setStringPainted(true);
        horse1.setForeground(Color.BLACK);
        horse1.setBounds(150, 100, 300, 30);
        frame.getContentPane().add(horse1);

        horse2.setStringPainted(true);
        horse2.setForeground(Color.BLUE);
        horse2.setBounds(150, 150, 300, 30);
        frame.getContentPane().add(horse2);

        horse3.setStringPainted(true);
        horse3.setForeground(Color.GRAY);
        horse3.setBounds(150, 200, 300, 30);
        frame.getContentPane().add(horse3);

        horse4.setStringPainted(true);
        horse4.setForeground(Color.RED);
        horse4.setBounds(150, 250, 300, 30);
        frame.getContentPane().add(horse4);

        msg.setBounds(150, 50, 300, 30);
        msg.setVisible(false);
        frame.getContentPane().add(msg);

        JButton btnStart = new JButton("START");
        btnStart.setFont(new Font("Calibri", Font.BOLD, 18));
        btnStart.addActionListener(new StartRace());
        btnStart.setBounds(150, 300, 125, 40);
        frame.getContentPane().add(btnStart);

        JButton btnReset = new JButton("RESET");
        btnReset.setFont(new Font("Calibri", Font.BOLD, 18));
        btnReset.addActionListener(new ResetRace());
        btnReset.setBounds(325, 300, 125, 40);
        frame.getContentPane().add(btnReset);
    }

    class StartRace implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!startRaceIsPressed) {
                msg.setVisible(false);
                resetRaceIsPressed = false;
                startRaceIsPressed = true;
                Horse1 h1 = new Horse1();
                h1.start();
                Horse2 h2 = new Horse2();
                h2.start();
                horse3 h3 = new horse3();
                h3.start();
                horse4 h4 = new horse4();
                h4.start();
            }
        }
    }

    public synchronized void EndRace(int i) {
        msg.setVisible(true);
        msg.setText(winnerHorse + " HAS WON THE RACE");
        msg.setFont(new Font("Calibri", Font.BOLD, 18));
        if (i == 100) {
            winner = true;
        }
        frame.getContentPane().add(msg);
    }

    class ResetRace implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!resetRaceIsPressed) {
                msg.setVisible(false);
                resetRaceIsPressed = true;
                startRaceIsPressed = false;
                winner = false;
                Horse1 h1 = new Horse1();
                h1.reset();
                Horse2 h2 = new Horse2();
                h2.reset();
                horse3 h3 = new horse3();
                h3.reset();
                horse4 h4 = new horse4();
                h4.reset();
            }
        }
    }

    class Horse1 extends Thread {
        public void reset() {
            horse1.setValue(0);
            horse1.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                horse1.setValue(i);
                horse1.repaint();
                if (i == 100) {
                    winnerHorse = 1;
                    EndRace(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Horse2 extends Thread {
        public void reset() {
            horse2.setValue(0);
            horse2.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                horse2.setValue(i);
                horse2.repaint();
                if (i == 100) {
                    winnerHorse = 2;
                    EndRace(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class horse3 extends Thread {
        public void reset() {
            horse3.setValue(0);
            horse3.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                horse3.setValue(i);
                horse3.repaint();
                if (i == 100) {
                    winnerHorse = 3;
                    EndRace(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class horse4 extends Thread {
        public void reset() {
            horse4.setValue(0);
            horse4.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                horse4.setValue(i);
                horse4.repaint();
                if (i == 100) {
                    winnerHorse = 4;
                    EndRace(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}