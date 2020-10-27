package Sequencer;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    private JFrame mainFrame = new JFrame("Music");
    private ArrayList<JCheckBox> checkBoxesList; // for flags
    private JPanel panel;

    private String[] instrumentNames = {"Bass Drum", "Closed Hi - Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", // for the label's name
            "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", " Cowbell", " Vibraslap", "Low-mid Tom",
            "High Agogo", "Open Hi Conga"};

    private int [] instruments =  {35,42,46,38,49,39,50,60,70,72, 64, 56, 58, 58, 47, 67, 63};

    GUI(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel();
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkBoxesList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("start");
        start.addActionListener(new GUI.ButtonStart());
        buttonBox.add(start);

        JButton stop = new JButton("stop");
        stop.addActionListener(new GUI.ButtonStop());
        buttonBox.add(stop);

        JButton increaseTemp = new JButton("Temp");
        increaseTemp.addActionListener(new GUI.ButtonIncreaseTemp());
        buttonBox.add(increaseTemp);

        JButton lowerTemp = new JButton("Temp Low");
        lowerTemp.addActionListener(new GUI.ButtonLowerTemp());
        buttonBox.add(lowerTemp);

        Box boxName = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++)
            boxName.add(new Label(instrumentNames[i]));

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, boxName);

        mainFrame.getContentPane().add(background);


        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(1);
        panel = new JPanel(grid);
        background.add(BorderLayout.CENTER, panel);

        for (int i = 0; i < 256; i++ ){ // create 256 checkbox and set them false to be false(genius)
            JCheckBox check = new JCheckBox();
            check.setSelected(false);
            checkBoxesList.add(check);
            panel.add(check);
        }
        mainFrame.setBounds(50,50,500, 500);
        mainFrame.pack();
        mainFrame.setVisible(true);

        SequencerPlay.sequencerPlay(instruments, checkBoxesList);
    }
    public class ButtonStart implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                SequencerPlay.buildTrackAndStart();
            } catch (InvalidMidiDataException invalidMidiDataException) {
                invalidMidiDataException.printStackTrace();
            }

        }
    }

    public class ButtonLowerTemp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SequencerPlay.downPlay();

        }
    }

    public class ButtonIncreaseTemp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SequencerPlay.increasePlay();

        }
    }

    public class ButtonStop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SequencerPlay.stopPlay();
        }
    }


}
