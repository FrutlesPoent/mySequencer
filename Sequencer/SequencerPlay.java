package Sequencer;

import javax.sound.midi.*;
import javax.swing.*;
import java.io.File;
import java.io.*;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class SequencerPlay {

    private Track track;
    private Sequence seq;
    private int[] instrumentNames;
    private Sequencer sequencer;
    private ArrayList<JCheckBox> checkBoxesList;
    private final int numberOfFlags = 256;

    public void sequencerPlay(int[] instrumentNames, ArrayList<JCheckBox> checkBoxesList){
        try {
            this.checkBoxesList = checkBoxesList;
            this.instrumentNames = instrumentNames;
            this.sequencer = MidiSystem.getSequencer();
            this.sequencer.open();
            this.seq = new Sequence(Sequence.PPQ,4);
            this.track = seq.createTrack();
            this.sequencer.setTempoInBPM(120);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void buildTrackAndStart() throws InvalidMidiDataException {
        int[] trackList = null; // to save for every instrument (16)
        this.seq.deleteTrack(track); // deleting old one;
        this.track = this.seq.createTrack(); // creating new one

        for (int i = 0; i < 16; i++) {
            trackList = new int[16];
            int key = this.instrumentNames[i];
            for (int j = 0; j < 16; j++) {
                JCheckBox jCheckBox = (JCheckBox) this.checkBoxesList.get(j + (16 * i));
                if (jCheckBox.isSelected()) {
                    trackList[j] = key; //if "yes" then we place key into massive which represents tact;
                } else {
                    trackList[j] = 0; // instrument should not play if he equals zero;
                }
            }
            makeTracks(trackList);
            this.track.add(createMessage(176, 1, 127, 0, 16));
        }
        this.track.add(createMessage(192,9,1,0, 15)); // music could not complete all 15 tact's, so we have to be sure;
        try {
            sequencer.setSequence(seq);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY); // infinite cycle;
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeTracks(int[] list) throws InvalidMidiDataException {
        for (int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
                this.track.add(createMessage(144, 9, key, 100, i));
                this.track.add(createMessage(128,9, key, 100, i + 1));
            }
        }
    }

    public void stopPlay() {
        sequencer.stop();
    }

    public void increasePlay() {
        float tempFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor((float) (tempFactor * 1.03)); // increase by 3%;
    }

    public void downPlay() {
        float tempFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor((float) (tempFactor * .97)); // decrease by 3%;
    }

    public void restoreMusic(String musicName) throws InvalidMidiDataException {
        stopPlay();

        String impl = ".ser";
        if (musicName.indexOf(impl) != -1 ){
        }else
            musicName = musicName + ".ser";

        boolean[] checkboxes = null;

        try {
            FileInputStream stream = new FileInputStream(new File(musicName));
            ObjectInputStream os = new ObjectInputStream(stream);
            checkboxes = (boolean[]) os.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }

        for (int i = 0; i < numberOfFlags; i++) {
            JCheckBox check = (JCheckBox) checkBoxesList.get(i);
            if (checkboxes[i])
                check.setSelected(true);
            else
                check.setSelected(false);
        }
        sequencer.stop();
        buildTrackAndStart();
    }

    public void clearTrack() {
        for (int i = 0; i < numberOfFlags; i++)
            checkBoxesList.get(i).setSelected(false);
    }

    public void saveMusic(String musicName) {
        stopPlay();
        String impl = ".ser";
        if (musicName == null){
            musicName = "Music.ser";
        }
        if (musicName.indexOf(impl) != -1 ){
        }else
            musicName = musicName + ".ser";

        boolean[] checkboxes = new boolean[numberOfFlags];

        for (int i = 0; i < numberOfFlags; i++) {
            JCheckBox check = (JCheckBox) checkBoxesList.get(i);
            if (check.isSelected())
                checkboxes[i] = true;
        }

        try {
            FileOutputStream fileStream = new FileOutputStream(new File(musicName));
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(checkboxes);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private MidiEvent createMessage(int typeMessage, int channel, int one, int two, int moment) throws InvalidMidiDataException{
        ShortMessage message = new ShortMessage();
        message.setMessage(typeMessage,channel,one , two);
        MidiEvent noteOn = new MidiEvent(message, moment);
        return noteOn;
    }
}
