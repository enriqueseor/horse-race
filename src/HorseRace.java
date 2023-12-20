import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class HorseRace {

    JFrame frame;
    JProgressBar[] horses = new JProgressBar[4];
    Color[] horseColors = {Color.BLACK, Color.BLUE, Color.GRAY, Color.RED};
    JLabel msg = new JLabel("");
    static boolean startRaceIsPressed = false;
    static boolean resetRaceIsPressed = false;
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
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        for (int i = 0; i < horses.length; i++) {
            horses[i] = new JProgressBar(0, 100);
            horses[i].setStringPainted(true);
            horses[i].setForeground(horseColors[i]);
            horses[i].setBounds(150, 100 + i * 50, 300, 30);
            frame.getContentPane().add(horses[i]);
        }

        msg.setBounds(150, 50, 300, 30);
        msg.setVisible(false);
        frame.getContentPane().add(msg);

        JButton btnStart = new JButton("START");
        btnStart.setFont(new Font("Arial", Font.BOLD, 18));
        btnStart.addActionListener(new StartRace());
        btnStart.setBounds(150, 300, 125, 40);
        frame.getContentPane().add(btnStart);

        JButton btnReset = new JButton("RESET");
        btnReset.setFont(new Font("Arial", Font.BOLD, 18));
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
                for (int i = 0; i < horses.length; i++) {
                    new Horse(horses[i], msg, frame, i + 1).start();
                }
            }
        }
    }

    public static synchronized void endRace(int i, int winnerHorse, JLabel msg, JFrame frame) {
        msg.setVisible(true);
        msg.setText(winnerHorse + " HAS WON THE RACE");
        msg.setFont(new Font("Arial", Font.BOLD, 18));
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
                for (int i = 0; i < horses.length; i++) {
                    new Horse(horses[i], msg, frame, i + 1).reset();
                }
            }
        }
    }

    static class Horse extends Thread {

        private final JProgressBar horse;
        private final JLabel msg;
        private final JFrame frame;
        private final int horseNumber;

        public Horse(JProgressBar horse, JLabel msg, JFrame frame, int horseNumber) {
            this.horse = horse;
            this.msg = msg;
            this.frame = frame;
            this.horseNumber = horseNumber;
        }

        public void reset() {
            horse.setValue(0);
            horse.repaint();
        }

        public void run() {
            for (int i = 0; i <= 100; i++) {
                horse.setValue(i);
                horse.repaint();

                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (HorseRace.class) {
                    if (HorseRace.winner) {
                        break;
                    }

                    if (i == 100) {
                        HorseRace.winner = true;
                        HorseRace.endRace(100, horseNumber, msg, frame);
                    }
                }
            }
        }
    }
}