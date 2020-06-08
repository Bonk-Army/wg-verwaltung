package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Login  extends ContentPart {

    boolean status;
    String uid;

    public Login(){
        this(false,"");
    }

    public Login (boolean status,String uid){
        this.status = status;
        this.uid = uid;
        generateContent();
    }

    private void generateContent() {
        this.content = readContentTemplate("GUIElements","Login","html");
        if(status){
            this.content.replaceAll("REPLACEME",("You succesfully logged in, your UID is "+uid));
        }else{
            this.content.replaceAll("REPLACEME","You failed logging logged in");
        }
        System.out.println("Hi");
    }


}
