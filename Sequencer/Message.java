package Sequencer;

import java.lang.*;
import java.util.*;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.*;

abstract public class Message {

    public static MidiEvent createMessage(int typeMessage, int channel, int one, int two, int moment) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(typeMessage,channel,one , two);
        MidiEvent noteOn = new MidiEvent(message, moment);
        return noteOn;
    }

//    public static String nameYourMusic(){ // console Input
//        String name = null;
//        Scanner in = new Scanner(System.in);
//        name = in.nextLine();
//        name = name + ".ser";
//        if (name != null)
//            return name;
//        return "Music.ser";
//    }

}
