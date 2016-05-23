package com.example.giacomo.farmalista;

/**
 * Created by Utente on 19/05/2016.
 */
public class Medicine {


    private String name;
    private String hour;
    private String finishDate;
    private int mId;

    public Medicine() {
    }

    public Medicine(String name, String hour, String finishDate) {
        this.name = name;
        this.hour = hour;
        this.finishDate = finishDate;
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }


}
