package passwordsecurity2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Hasher {
    public String getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < salt.length; i++) {
            sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
        }
        String stringSalt = sb.toString();

        return stringSalt;
    }

    public String hashPass(String password, String salt) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());

        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();

        return generatedPassword;
    }


}
