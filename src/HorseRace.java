import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorseRace {

    JFrame frame;
    JProgressBar horse1 = new JProgressBar(0, 100);
    JProgressBar horse2 = new JProgressBar(0, 100);
    JProgressBar horse3 = new JProgressBar(0, 100);
    JProgressBar horse4 = new JProgressBar(0, 100);
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
                Horse1 h1 = new Horse1(horse1, msg, frame);
                h1.start();
                Horse2 h2 = new Horse2(horse2, msg, frame);
                h2.start();
                Horse3 h3 = new Horse3(horse3, msg, frame);
                h3.start();
                Horse4 h4 = new Horse4(horse4, msg, frame);
                h4.start();
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
                Horse1 h1 = new Horse1(horse1, msg, frame);
                h1.reset();
                Horse2 h2 = new Horse2(horse2, msg, frame);
                h2.reset();
                Horse3 h3 = new Horse3(horse3, msg, frame);
                h3.reset();
                Horse4 h4 = new Horse4(horse4, msg, frame);
                h4.reset();
            }
        }
    }
}