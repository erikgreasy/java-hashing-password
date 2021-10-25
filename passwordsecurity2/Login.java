//////////////////////////////////////////////////////////////////////////
// TODO:                                                                //
// Uloha2: Upravte funkciu na prihlasovanie tak, aby porovnavala        //
//         heslo ulozene v databaze s heslom od uzivatela po            //
//         potrebnych upravach.                                         //
// Uloha3: Vlozte do prihlasovania nejaku formu oneskorenia.            //
//////////////////////////////////////////////////////////////////////////
package passwordsecurity2;

import java.io.IOException;
import java.time.LocalDateTime;

import passwordsecurity2.Database.MyResult;

public class Login {
    private static int pocetPokusov = 0;
    private static LocalDateTime lastAttempt = null;
    private static int waitingPeriod = 10;

    protected static MyResult prihlasovanie(String meno, String heslo) throws IOException, Exception{
        /*
        *   Delay je vhodne vytvorit este pred kontolou prihlasovacieho mena.
        */


        if( pocetPokusov >= 3 && lastAttempt.plusSeconds(waitingPeriod).isAfter(LocalDateTime.now()) ) {
            System.out.println("Moc skory pokus");
            return new MyResult(false, "Vela nespravnych pokusov. Pre dalsi pokus musis pockat " + waitingPeriod + " sekund od posledneho pokusu");
        }
        pocetPokusov++;
        lastAttempt = LocalDateTime.now();


        User user = User.find(meno);

        if( user == null ) {
            if( pocetPokusov >= 3 ) {
                waitingPeriod = waitingPeriod*2;
            }
            return new MyResult(false, "Nespravne meno.");
        }


        Hasher hasher = new Hasher();
        String enteredPassHash = hasher.hashPass(heslo, user.salt);

        boolean rightPassword = enteredPassHash.equals(user.password);

        if (!rightPassword) {
            if( pocetPokusov >= 3 ) {
                waitingPeriod = waitingPeriod*2;
            }
            return new MyResult(false, "Nespravne heslo.");
        }


        pocetPokusov = 0;
        waitingPeriod = 10;

        return new MyResult(true, "Uspesne prihlasenie.");
    }
}
