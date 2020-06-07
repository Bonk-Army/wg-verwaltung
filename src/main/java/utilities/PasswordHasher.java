package utilities;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Used to hash the passwords before storing them in SQL
 */
public class PasswordHasher {

    public static void main(String[] args) {
        //byte[] salt = generateSalt();
        //System.out.println(salt);
        byte[] salt = "[B@66a29884".getBytes();
        System.out.println(hashPassword("HelloWorld!12334534564567456234534", salt));
        System.out.println(hashPassword("HelloWorld!12334534564567456234534", salt));
        System.out.println(hashPassword("HelloWorld!12334534564567456234535", salt));
    }

    /**
     * Hashes the given password with PBKDF2
     *
     * @param password The password to be hashed
     * @return The hash. If null is returned, the registration process MUST be interrupted!!!
     */
    public static String hashPassword(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 512);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return new String(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Generate a random salt used to salt the hash of the password
     * @return The salt as a byte array
     */
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
}
