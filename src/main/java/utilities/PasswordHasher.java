package utilities;

import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

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
        String salt = "1234";
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
    public static String hashPassword(String password, String saltString) {
        byte[] salt = null;
        try {
            salt = Hex.decodeHex(saltString);
        } catch (DecoderException e) {
            return null;
        }

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 512);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            String hashedString = Hex.encodeHexString(hash);
            return hashedString;
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
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        String saltString = Hex.encodeHexString(salt);

        return saltString;
    }
}
