package passwordsecurity2;

import javax.swing.JFrame;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class PasswordSecurity2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        byte[] salt = getSalt();
        String password1 = hashPass("Password", salt);
        String password2 = hashPass("Password", salt);
        System.out.println(" Password 1 -> " + password1);
        System.out.println(" Password 2 -> " + password2);
        if (password1.equals(password2)) {
            System.out.println("passwords are equal");
        } else {
            System.out.println("passwords not equal");
        }
//        GUI okno = new GUI();
//        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        okno.setVisible(true);
//        okno.setResizable(false);
//        okno.setLocationRelativeTo(null);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private static String hashPass(String password, byte[] salt) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();

        return generatedPassword;
    }
    
}
