import javax.swing.*;
import java.util.UUID;

public class Horse2 extends Thread {
    private final JProgressBar horse2;
    private final JLabel msg;
    private final JFrame frame;

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
        for (int i = 0; i <= 100; i++) {
            horse2.setValue(i);
            horse2.repaint();

            try {
                Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i == 100) {
                synchronized (HorseRace.class) {
                    if (!HorseRace.winner) {
                        HorseRace.winner = true;
                        HorseRace.endRace(i, 2, msg, frame);
                    }
                }
                break;
            }
        }
    }
}