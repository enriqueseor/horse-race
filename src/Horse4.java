import javax.swing.*;
import java.util.UUID;

public class Horse4 extends Thread {

    private final JProgressBar horse4;
    private final JLabel msg;
    private final JFrame frame;
    static int winnerHorse = 0;
    static boolean winner = false;

    public Horse4(JProgressBar horse4, JLabel msg, JFrame frame) {
        this.horse4 = horse4;
        this.msg = msg;
        this.frame = frame;
    }

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
            }
            try {
                Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
