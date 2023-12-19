import javax.swing.*;
import java.util.UUID;

public class Horse1 extends Thread {
    private final JProgressBar horse1;
    private final JLabel msg;
    private final JFrame frame;

    public Horse1(JProgressBar horse1, JLabel msg, JFrame frame) {
        this.horse1 = horse1;
        this.msg = msg;
        this.frame = frame;
    }

    public void reset() {
        horse1.setValue(0);
        horse1.repaint();
    }

    public void run() {
        for (int i = 0; i <= 100; i++) {
            horse1.setValue(i);
            horse1.repaint();

            try {
                Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i == 100) {
                synchronized (HorseRace.class) {
                    if (!HorseRace.winner) {
                        HorseRace.winner = true;
                        HorseRace.endRace(i, 1, msg, frame);
                    }
                }
                break;
            }
        }
    }
}