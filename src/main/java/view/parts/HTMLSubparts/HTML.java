package view.parts.HTMLSubparts;

import view.parts.HTMLPart;

public class HTML extends HTMLPart {

    /**
     * an HTML Object consists of an Head and an Body
     */
    private Head head;
    private Body body;

    public HTML(Head head,Body body){
        this.head = head;
        this.body = body;
    }

    /**
     * Overriding the generateThisPart to add body and head to the output
     * @return
     */
    @Override
    public String generateThisPart(){
        String className = this.getClass().getSimpleName();

        String result = "";

        result += readHTMLTemplate(className+"_Top","html");

        result += head.generateThisPart();
        result += body.generateThisPart();

        head.clear();
        body.clear();

        result += readHTMLTemplate(className+"_Bottom","html");

        this.clear();

        return result;
    }
}
