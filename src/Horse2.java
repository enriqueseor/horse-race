import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class Horse2 extends Thread {

    private final JProgressBar horse2;
    private final JLabel msg;
    private final JFrame frame;
    static int winnerHorse = 0;
    static boolean winner = false;

    public Horse2(JProgressBar horse2, JLabel msg, JFrame frame) {
        this.horse2 = horse2;
        this.msg = msg;
        this.frame = frame;
    }

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
                endRace(i);
            }
            try {
                Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void endRace(int i) {
        msg.setVisible(true);
        msg.setText(winnerHorse + " HAS WON THE RACE");
        msg.setFont(new Font("Calibri", Font.BOLD, 18));
        if (i == 100) {
            winner = true;
        }
        frame.getContentPane().add(msg);
    }
}
