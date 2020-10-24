import javax.sound.midi.*;

public class MusicTest1 {

    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("Нужны аргументы для инструмента и ноты");
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            Sequencer.sequencerPlay(instrument, note);
        }
    }
}
