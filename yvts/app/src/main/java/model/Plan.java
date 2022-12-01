package model;

public class Plan {
    public String toDo;
    public String dateTime;
    public int state;
    public int itemID;

    public int getState() {
        return  state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
