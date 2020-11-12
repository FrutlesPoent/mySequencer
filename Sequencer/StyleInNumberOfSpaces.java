package Sequencer;

public class StyleInNumberOfSpaces extends JudgeOfStyle {

    public StyleInNumberOfSpaces(){

    }

    public StyleInNumberOfSpaces(int countSpaces){
        setStyleCount(countSpaces);
    }

    @Override
    public int getStyleCount() {
        return count;
    }

    @Override
    public void setStyleCount(int countSpaces) {
        this.count = countSpaces;
    }

    @Override
    protected int calculateStyle() {
        if (count != 0){
            if (count <= Math.pow(2,6))
                MusicHelper.playSound(DStyle).join();
            else if ( count >= (Math.pow(2,6) + 1) && count <= Math.pow(2,7))
                MusicHelper.playSound(CStyle).join();
            else if (count >= Math.pow(2,7) + 1 && count <= 192)
                MusicHelper.playSound(BStyle).join();
            else if (count >= 192 && count <= Math.pow(2,8))
                MusicHelper.playSound(SStyle).join();

        }
        return 0;
    }

    public void verdict(int countSpaces){
        if (this.count == 0)
            setStyleCount(countSpaces);
        calculateStyle();
    }

    public void verdict(){
        calculateStyle();
    }

}
