package Sequencer;

import javax.sound.midi.*;
import javax.swing.*;

public class MusicTest1 {
    private static JFrame frame = new JFrame("Music");
    private static myDraw draw;

    public static void main(String[] args) {
        MusicTest1 app = new MusicTest1();
        app.startWork();
    }

    public void startWork(){
        setUpGUIInterface();
        SequencerPlay start = new SequencerPlay();
        start.sequencerPlay(draw);

    }

    public void setUpGUIInterface() {
        draw = new myDraw();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30,30,500, 500);
        frame.setContentPane(draw);
        frame.setVisible(true);
    }
}
