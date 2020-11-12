package Sequencer;

public class StyleInNumberOfInstruments extends JudgeOfStyle {

    public StyleInNumberOfInstruments(){

    }

    public StyleInNumberOfInstruments(int countInstruments) {
        setStyleCount(countInstruments);
    }

    @Override
    public int getStyleCount() {
        return count;
    }

    @Override
    public void setStyleCount(int count) {
        this.count = count;
    }

    @Override
    protected int calculateStyle() {
        if (count != 0) {
            if (count <= 4){
                MusicHelper.playSound(DStyle).join();
            } else if (count >=5 && count <= 8){
                MusicHelper.playSound(CStyle).join();
            } else if (count >= 9 && count <= 12){
                MusicHelper.playSound(BStyle).join();
            } else if (count >= 13 && count <= 16){
                MusicHelper.playSound(SStyle).join();
            }
            return count;
        }
        return 0;
    }

    public void verdict(int countIns){
        if (this.count == 0)
            setStyleCount(countIns);
        calculateStyle();
    }

    public void verdict(){
        calculateStyle();
    }
}
