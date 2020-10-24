import javax.sound.midi.*;

abstract public class SequencerPlay {

    public static void sequencerPlay(int instrument, int note){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();
            MidiEvent even = null;
            track.add(Message.createMessage(192,1,instrument,0,1));
            track.add(Message.createMessage(144, 1, note, 100, 1));
            track.add(Message.createMessage(128,1, note, 100, 16));
            sequencer.setSequence(seq);
            sequencer.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
}
