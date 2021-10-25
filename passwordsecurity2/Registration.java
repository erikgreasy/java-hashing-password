//////////////////////////////////////////////////////////////////////////
// TODO:                                                                //
// Uloha1: Do suboru s heslami ulozit aj sal.                           //
// Uloha2: Pouzit vytvorenu funkciu na hashovanie a ulozit heslo        //
//         v zahashovanom tvare.                                        //
//////////////////////////////////////////////////////////////////////////
package passwordsecurity2;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import passwordsecurity2.Database.MyResult;


public class Registration {
    protected static MyResult registracia(String meno, String heslo) throws NoSuchAlgorithmException, Exception{

        List<User> users = User.all();

        for(User user : users) {
            if (user.name.equals(meno)) {
                System.out.println("Meno je uz zabrate.");
                return new MyResult(false, "Meno je uz zabrate.");
            }
        }


        Hasher hasher = new Hasher();
        String salt = hasher.getSalt();

        String hashedPass = hasher.hashPass(heslo, salt);

        User user = new User(meno, hashedPass, salt);

        User.create(user);

        return new MyResult(true, "");
    }
    
}
