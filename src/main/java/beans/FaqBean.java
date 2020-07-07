package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean used for the FAQ Page to provide the FAQ strings
 */
public class FaqBean {
    private List<Map<String, String>> faqs = new ArrayList<Map<String, String>>();
    private static final int numberOfQuestions = 10;

    public FaqBean(){
        Map<String, String> q1 = new HashMap<String, String>();
        q1.put("question", "Kostet die Benutzung des Services etwas?");
        q1.put("answer", "Nein, unser Service ist komplett kostenlos!");
        faqs.add(q1);

        Map<String, String> q2 = new HashMap<String, String>();
        q2.put("question", "Wie viele Mitglieder sind pro WG m&ouml;glich ?");
        q2.put("answer", "Prinzipiell sind unendlich viele Mitglieder m&ouml;glich, aber zur &Uuml;bersicht beschr&auml;nken wir die WG's auf 10 Personen!");
        faqs.add(q2);

        Map<String, String> q3 = new HashMap<String, String>();
        q3.put("question", "Warum verschwinden manche ToDos ?");
        q3.put("answer", "Wir zeigen alle unerledigten ToDos an und von den erledigten nur die, die für die letzten 7 Tage zu erledigen waren.");
        faqs.add(q3);

        Map<String, String> q4 = new HashMap<String, String>();
        q4.put("question", "Warum sind manche Felder im Putzplan schwarz und manche weiß ?");
        q4.put("answer", "Die weißen Felder sind die Felder, die du bereits bearbeitet hast. Die schwarzen Felder sind die normalen Felder, die du noch nciht bearbeitet hast.");
        faqs.add(q4);

        /*Map<String, String> q5 = new HashMap<String, String>();
        q5.put("question", "Kostet die Benutzung des Services etwas?");
        q5.put("answer", "Nein, unser Service ist komplett kostenlos!");
        faqs.add(q5);

        Map<String, String> q6 = new HashMap<String, String>();
        q6.put("question", "Kostet die Benutzung des Services etwas?");
        q6.put("answer", "Nein, unser Service ist komplett kostenlos!");
        faqs.add(q6);

        Map<String, String> q7 = new HashMap<String, String>();
        q7.put("question", "Kostet die Benutzung des Services etwas?");
        q7.put("answer", "Nein, unser Service ist komplett kostenlos!");
        faqs.add(q7);*/
    }

    public List<Map<String, String>> getFaqs() {
        return faqs;
    }
}
