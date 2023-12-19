import javax.swing.*;
import java.util.UUID;

public class Horse3 extends Thread {

    private final JProgressBar horse3;
    private final JLabel msg;
    private final JFrame frame;
    static int winnerHorse = 0;
    static boolean winner = false;

    public Horse3(JProgressBar horse3, JLabel msg, JFrame frame) {
        this.horse3 = horse3;
        this.msg = msg;
        this.frame = frame;
    }

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
            }
            try {
                Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}