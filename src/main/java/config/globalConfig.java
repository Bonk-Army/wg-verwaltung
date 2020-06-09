package config;

public class globalConfig {
    //Bitte vorsichtig mit den Configurationen! Sparsam!!

    /**
     * Tries to read value from environment variable. If variable is not known (so we are not on Azure), it returns
     * true. If the env var exists (so we are on azure), it returns true
     *
     * @return If this is a testing environment
     */
    public static boolean isTest() {
        if (System.getenv("IS_AZURE") == null) {
            return true;
        }

        return false;
    }

    public globalConfig() {
    }
}
