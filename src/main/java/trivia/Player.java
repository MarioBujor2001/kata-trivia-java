package trivia;

public class Player {
    private String name;
    private int place;
    private int purse;
    private boolean isInPenaltyBox;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPurse() {
        return purse;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        isInPenaltyBox = inPenaltyBox;
    }

    @Override
    public String toString() {
        return name;
    }

    public void move(int roll) {
        place += roll;
        if(place > 11){
            place -= 12;
        }
    }
}
