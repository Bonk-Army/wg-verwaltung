package beans;

import utilities.ErrorCodes;

import java.util.Date;

public class DemoBean {

    public String username = "Alpakaflüsterer";
    public int kontoguthaben = 450;
    public String vorname = "Max";
    public String nachname = "Mustermann";
    public String wg = "Alpakastall";
    public Date date;
    public String todo;
    public boolean check = true;

    public DemoBean(){
        date = new Date();
        todo = "4";
    }

    public String getDate(){
        return this.date.toString();
    }

    public ErrorCodes getStatus (){
        return ErrorCodes.FAILURE;
    }
}
