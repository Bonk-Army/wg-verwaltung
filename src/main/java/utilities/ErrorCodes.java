package utilities;

/**
 * Enum that holds all error codes that may occur so we can give the user detailed Information about what exactly
 * went wrong
 */
public enum ErrorCodes {
    //Something was done successfully
    SUCCESS("Die NLA (National Llama Association) ist stolz auf dich.", "Erfolg"),
    //Something failed server-side
    FAILURE("Das Lama, das heute Serveraufsicht hat, ist wohl gerade eine rauchen. Bitte versuche es in ein paar Minuten nochmal.", "Serverfehler"),
    //Something that has been entered was wrong
    WRONGENTRY("Eine deiner Eingaben ist nicht richtig, sagt unser Datenbank-Lama", "Falsche Eingabe"),
    //The entered username was wrong
    WRONGUNAME("Unserem Türsteher-Lama ist der eingegebene Benutzername leider nicht bekannt.", "Falscher Benutzername"),
    //The entered password was wrong
    WRONGPASSWORD("Dieses Passwort passt nicht zum eingegebenen Benutzernamen. Das behauptet zumindest unser Hashing-Lama.", "Falsches Passwort"),
    //The entered email address was wrong
    WRONGEMAIL("Unser Post-Lama behauptet, dass diese E-Mail-Addresse nicht funktioniert. Vielleicht müssen wir aber auch auf Brieftauben umstellen.", "Falsche E-Mail-Adresse"),
    //The user should simply try it again
    TRYAGAIN("Das Runtime-Lama sagt, dass du es einfach nochmal versuchen musst. Hör auf das Runtime-Lama. Es ist ein gutes Lama.", "Erneut versuchen"),
    //Authentication error, must log in again
    AUTHFAIL("Das Türsteher-Lama hat deinen Ausweis verschlampt. Bitte melde dich nochmal an.", "Authentifikation fehlgeschlagen"),
    // There is no WG associated with the current user
    NOWGFOUND("Das Boss-Lama sagt, dass du in keiner WG bist. Einsame Alpakas sind schwach, sei kein einsames Alpaka!", "Keine WG"),
    // The during registration process entered username is already in use
    DUPLICATEUNAME("Dieser Benutzername wird leider bereits von einem anderen Nutzer verwendet, sagt unser Gästebuch-Lama. Bitte versuche es mit einem anderen Namen.", "Benutzername bereits vergeben"),
    // The during registration process entered email address is already in use
    DUPLICATEEMAIL("Unser Post-Lama sagt, dass es diese E-Mail-Adresse schon im Verzeichnis gibt und es dann nicht wüsste, an wen es die Post jetzt ausliefern soll. Bitte versuche es mit einer anderen Addresse.",
            "E-Mail-Addresse bereits verwendet"),
    // The wg that the user wants to join has reached its maximum capacity
    WGFULL("Das Verwalter-Lama sagt, dass diese WG leider schon voll ist. Bitte tritt einer anderen WG bei oder wende dich an den entsprechenden WG-Administrator.", "Fehler beim Beitreten");

    private String message;
    private String header;

    ErrorCodes(String message, String header) {
        this.message = message;
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public String getHeader() {
        return header;
    }
}
