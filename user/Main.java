package user;

import math.Keys;
import message.PrivateKeys;
import message.PublicKeys;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //keygen *username*
        //read *filename* *priv keys*
        //compose *filename* *pub keys*


        String mode;
        try {
            mode = args[0];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Enter one of: keygen, read, compose");
        }
        if (mode.equals("keygen")) {
            Setup.main(args);
        }
        else if (mode.equals("read")) {
            Read.main(args);

        }
        else if (mode.equals("compose")) {
            Compose.main(args);

        }
        else {
            throw new IllegalArgumentException("Enter one of: keygen, read, compse");
        }

    }
}
