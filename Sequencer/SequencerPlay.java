package Sequencer;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

abstract public class SequencerPlay {
    private static Track track;
    private static Sequence seq;
    private static int[] instrumentNames;
    private static Sequencer sequencer;
    private static ArrayList<JCheckBox> checkBoxesList;

    public static void sequencerPlay(int[] instrumentNames, ArrayList<JCheckBox> checkBoxesList){
        try {
            SequencerPlay.checkBoxesList = checkBoxesList;
            SequencerPlay.instrumentNames = instrumentNames;
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            seq = new Sequence(Sequence.PPQ,4);
            track = seq.createTrack();
            sequencer.setTempoInBPM(120);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void buildTrackAndStart() throws InvalidMidiDataException {
        int[] trackList = null; // to save for every instrument (16)
        seq.deleteTrack(track); // deleting old one;
        track = seq.createTrack(); // creating new one

        for (int i = 0; i < 16; i++) {
            trackList = new int[16];
            int key = SequencerPlay.instrumentNames[i];
            for (int j = 0; j < 16; j++) {
                JCheckBox jCheckBox = (JCheckBox) SequencerPlay.checkBoxesList.get(j + (16 * i));
                if (jCheckBox.isSelected()) {
                    trackList[j] = key; //if "yes" then we place key into massive which represents tact;
                } else {
                    trackList[j] = 0; // instrument should not play if he equals zero;
                }
            }
            makeTracks(trackList);
            track.add(Message.createMessage(176, 1, 127, 0, 16));
        }
        track.add(Message.createMessage(192,9,1,0, 15)); // music could not complete all 15 tact's, so we have to be sure;
        try {
            sequencer.setSequence(seq);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY); // infinite cycle;
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void makeTracks(int[] list) throws InvalidMidiDataException {
        for (int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
                track.add(Message.createMessage(144, 9, key, 100, i));
                track.add(Message.createMessage(128,9, key, 100, i + 1));
            }
        }
    }

    public static void stopPlay() {
        sequencer.stop();
    }

    public static void increasePlay() {
        float tempFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor((float) (tempFactor * 1.03)); // we can increase by 3%;
    }

    public static void downPlay() {
        float tempFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor((float) (tempFactor * .97)); // decrease by 3%;
    }
}
