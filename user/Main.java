package user;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        String option = IOUtils.prompt("Enter 'setup', 'compose', or 'read': ");
        if (option.equals("setup")){
            Setup.main(null);
        }
        else if (option.equals("compose")) {
            Compose.main(null);
        }
        else if (option.equals("read")){
            Read.main(null);
        }
        else {
            System.out.print("Invalid Option");
            main(null);
        }

    }
}
