package Sequencer;

abstract public class JudgeOfStyle extends SequencerPlay {


    protected String SStyle = "src/Sequencer/Music_DMC/S.wav";
    protected String BStyle = "src/Sequencer/Music_DMC/B.wav";
    protected String CStyle = "src/Sequencer/Music_DMC/C.wav";
    protected String DStyle = "src/Sequencer/Music_DMC/D.wav";
    protected int count;

    public abstract int getStyleCount();
    public abstract void setStyleCount(int count);
    protected abstract int calculateStyle();
    public abstract void verdict(int countIns);
    public abstract void verdict();


}
