package Sequencer;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import java.awt.*;

public class myDraw extends JPanel implements ControllerEventListener {
    private int x = 20;
    private int y = 50;
    private boolean msg = false;

    @Override
    public void paintComponent(Graphics g){
        if (msg) {
            Graphics2D g2 = (Graphics2D) g;
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);

            g.setColor(new Color(red,green,blue));

            int height = (int) ((Math.random() * 120) + 10 );
            int width = (int) ((Math.random()* 120) + 10);

            int x = (int) ((Math.random() * 40) + 10);
            int y = (int) ((Math.random() * 40) + 10);

            g.fillRect(x,y,height,width);
            msg = false;
        }
    }

    @Override
    public void controlChange(ShortMessage event) {
        msg = true;
        repaint();
    }
}
