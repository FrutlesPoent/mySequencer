package Sequencer;

import javax.sound.midi.*;

public class SequencerPlay implements ControllerEventListener {

    public void sequencerPlay(myDraw draw){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(draw, new int[] {127});
            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();
            MidiEvent even = null;
            int r = 0;
            for (int i = 5; i < 60; i +=4 ) {
                r = (int) ((Math.random() * 50) + 1);
                track.add(Message.createMessage(144, 1, r, 100, i));
                track.add(Message.createMessage(176, 1, 127, 0, i));
                track.add(Message.createMessage(128, 1,r, 100, i + 2));
            }
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void controlChange(ShortMessage event) {
            System.out.println("Ля");
    }
}
