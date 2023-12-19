import javax.swing.*;
import java.util.UUID;

public class Horse4 extends Thread {
    private final JProgressBar horse4;
    private final JLabel msg;
    private final JFrame frame;

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
        for (int i = 0; i <= 100; i++) {
            horse4.setValue(i);
            horse4.repaint();

            try {
                Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i == 100) {
                synchronized (HorseRace.class) {
                    if (!HorseRace.winner) {
                        HorseRace.winner = true;
                        HorseRace.endRace(i, 4, msg, frame);
                    }
                }
                break;
            }
        }
    }
}