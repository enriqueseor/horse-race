import javax.swing.*;
import java.util.UUID;

public class Horse3 extends Thread {
    private final JProgressBar horse3;
    private final JLabel msg;
    private final JFrame frame;

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
        for (int i = 0; i <= 100; i++) {
            horse3.setValue(i);
            horse3.repaint();

            try {
                Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (HorseRace.winner) {
                break;
            }

            if (i == 100) {
                synchronized (HorseRace.class) {
                    if (!HorseRace.winner) {
                        HorseRace.winner = true;
                        HorseRace.endRace(100, 3, msg, frame);
                    }
                }
                break;
            }
        }
    }
}