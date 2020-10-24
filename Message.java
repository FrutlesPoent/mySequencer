import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.*;

abstract public class Message {

    public static MidiEvent createMessage(int typeMessage, int channel, int note, int speed, int moment) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(typeMessage,channel,note,speed);
        MidiEvent noteOn = new MidiEvent(message, moment);
        return noteOn;
    }

}
