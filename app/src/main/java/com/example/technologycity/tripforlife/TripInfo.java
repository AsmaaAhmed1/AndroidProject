package com.example.technologycity.tripforlife;


public class TripInfo {
    String name;
    String start;
    String end;
    int id;
    String date;
    String time;
    String start_lang;
    String strat_alt;
    String end_lang;
    String end_alt;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPast() {
        return past;
    }

    public void setPast(String past) {
        this.past = past;
    }

    public String getRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(String roundTrip) {
        this.roundTrip = roundTrip;
    }

    String note;
    String past;
    String roundTrip;


    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
    public String getStart_lang() {
        return start_lang;
    }

    public void setStart_lang(String start_lang) {
        this.start_lang = start_lang;
    }

    public String getStrat_alt() {
        return strat_alt;
    }

    public void setStrat_alt(String strat_alt) {
        this.strat_alt = strat_alt;
    }

    public String getEnd_lang() {
        return end_lang;
    }

    public void setEnd_lang(String end_lang) {
        this.end_lang = end_lang;
    }

    public String getEnd_alt() {
        return end_alt;
    }

    public void setEnd_alt(String end_alt) {
        this.end_alt = end_alt;
    }
}
