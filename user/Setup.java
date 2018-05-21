package user;

import math.Keys;
import message.PrivateKeys;
import message.PublicKeys;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Setup {
    public static void main(String[] args){
        String username;
        try {
            username = args[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("You must give a username during key generation");
        }
        Keys usersKeys = new Keys(200);
        PrivateKeys prKeys = new PrivateKeys(usersKeys);
        PublicKeys puKeys = new PublicKeys(prKeys);
        ObjectWriter keysWriter = new ObjectWriter(username + ".priv");
        keysWriter.writeObject(prKeys);
        ObjectWriter contactWriter = new ObjectWriter(username + ".pub");
        contactWriter.writeObject(puKeys);
    }
}
