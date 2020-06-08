package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Login  extends ContentPart {

    boolean status;
    String uid;

    public Login(){
        this.status = false;
        this.uid = "";
    }

    public Login (boolean status,String uid){
        this.status = status;
        this.uid = uid;
    }

    @Override
    public String toString() {
        if (status){
            //add fancy shit here
            return "<h1>Successfully logged you in, your ID is " + uid+"</h1>";
        }
        else{
            return "<h1>Failure!</h1>";
        }
    }
}
