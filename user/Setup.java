package user;

import math.Keys;
import message.PrivateKeys;
import message.PublicKeys;

public class Setup {
    public static void main(String[] args){
        String username;
        try {
            username = args[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("You must give a username during key generation");
        }
        Keys usersKeys = new Keys(617);
        PrivateKeys prKeys = new PrivateKeys(usersKeys);
        PublicKeys puKeys = new PublicKeys(prKeys);
        ObjectWriter keysWriter = new ObjectWriter(username + ".priv");
        keysWriter.writeObject(prKeys);
        ObjectWriter contactWriter = new ObjectWriter(username + ".pub");
        contactWriter.writeObject(puKeys);
    }
}
