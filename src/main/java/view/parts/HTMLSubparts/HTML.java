package view.parts.HTMLSubparts;

import view.parts.ContentPart;
import view.parts.HTMLPart;

public class HTML extends HTMLPart {

    private Head head;
    private Body body;

    public HTML(Head head,Body body){
        this.head = head;
        this.body = body;
    }

    @Override
    public String generateThisPart(){
        String className = this.getClass().getSimpleName();

        String result = "";

        result += readTemplate("HTMLComponents",className+"_Top","html");

        result += head.generateThisPart();
        result += body.generateThisPart();

        head.clear();
        body.clear();

        result += readTemplate("HTMLComponents",className+"_Bottom","html");

        this.clear();

        return result;
    }
}
