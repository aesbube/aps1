package labs.aps.lab7.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;


public class Lozinki {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        for (int i = 1; i <= N; i++) {
            String imelozinka = br.readLine();
            String[] pom = imelozinka.split(" ");
            hashtable.put(pom[0], pom[1]);
        }

        while (true) {
            String ip = br.readLine();
            if (ip.equals("KRAJ")) break;

            String[] user = ip.split(" ");
            if (hashtable.containsKey(user[0]) && hashtable.get(user[0]).equals(user[1])) {
                System.out.println("Najaven");
                break;
            }
            System.out.println("Nenajaven");
        }


    }
}
