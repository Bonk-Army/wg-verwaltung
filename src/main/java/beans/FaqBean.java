package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean used for the FAQ Page to provide the FAQ strings
 */
public class FaqBean {
    private boolean isLoggedIn = false;

    public FaqBean() {
    }

    /*
      /$$$$$$              /$$     /$$                                               /$$        /$$$$$$              /$$     /$$
     /$$__  $$            | $$    | $$                                              /$$/       /$$__  $$            | $$    | $$
    | $$  \__/  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$   /$$$$$$$           /$$/       | $$  \__/  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$   /$$$$$$$
    | $$ /$$$$ /$$__  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$ /$$_____/          /$$/        |  $$$$$$  /$$__  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$ /$$_____/
    | $$|_  $$| $$$$$$$$  | $$    | $$    | $$$$$$$$| $$  \__/|  $$$$$$          /$$/          \____  $$| $$$$$$$$  | $$    | $$    | $$$$$$$$| $$  \__/|  $$$$$$
    | $$  \ $$| $$_____/  | $$ /$$| $$ /$$| $$_____/| $$       \____  $$        /$$/           /$$  \ $$| $$_____/  | $$ /$$| $$ /$$| $$_____/| $$       \____  $$
    |  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/|  $$$$$$$| $$       /$$$$$$$/       /$$/           |  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/|  $$$$$$$| $$       /$$$$$$$/
     \______/  \_______/   \___/   \___/   \_______/|__/      |_______/       |__/             \______/  \_______/   \___/   \___/   \_______/|__/      |_______/
    */

    // Getters and Setters for use with JSPs

    public List<Map<String, String>> getFaqs() {
        List<Map<String, String>> faqs = new ArrayList<Map<String, String>>();

        Map<String, String> q1 = new HashMap<String, String>();
        q1.put("question", "Kostet die Benutzung des Services etwas?");
        q1.put("answer", "Nein, unser Service ist komplett kostenlos!");
        q1.put("number", "1");
        faqs.add(q1);

        Map<String, String> q2 = new HashMap<String, String>();
        q2.put("question", "Wie viele Mitglieder sind pro WG möglich?");
        q2.put("answer", "Prinzipiell sind unendlich viele Mitglieder m&ouml;glich, aber zur Übersicht beschränken wir die WG's auf 10 Personen!");
        q2.put("number", "2");
        faqs.add(q2);

        Map<String, String> q3 = new HashMap<String, String>();
        q3.put("question", "Warum verschwinden manche ToDos?");
        q3.put("answer", "Wir zeigen alle unerledigten ToDos an und von den erledigten nur die, die für die letzten 7 Tage zu erledigen waren.");
        q3.put("number", "3");
        faqs.add(q3);

        Map<String, String> q4 = new HashMap<String, String>();
        q4.put("question", "Warum sind manche Felder im Putzplan schwarz und manche weiß?");
        q4.put("answer", "Die weißen Felder sind die Felder, die du bereits bearbeitet hast. Die schwarzen Felder sind die normalen Felder, die du noch nciht bearbeitet hast.");
        q4.put("number", "4");
        faqs.add(q4);

        Map<String, String> q5 = new HashMap<String, String>();
        q5.put("question", "Warum ist das erste Element im Dropdown-Menü schwarz und der Rest weiß?");
        q5.put("answer", "Das erste Element, also das schwarz hinterlegte, ist das aktuell gespeicherte Element.");
        q5.put("number", "5");
        faqs.add(q5);

        Map<String, String> q6 = new HashMap<String, String>();
        q6.put("question", "Wie kann ich Änderungen im Putzplan speichern?");
        q6.put("answer", "Wähle deine Änderung im Putzplan aus und drücke dann auf den Speicherbutton oben auf der Seite");
        q6.put("number", "6");
        faqs.add(q6);

        Map<String, String> q7 = new HashMap<String, String>();
        q7.put("question", "Wie melde ich mich an?");
        q7.put("answer", "<a href=\"/\">Klicke hier und gib deinen Benutzername und dein Passwort ein</a>");
        q7.put("number", "7");
        if (!isLoggedIn) {
            faqs.add(q7);
        }

        return faqs;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
