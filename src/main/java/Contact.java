import java.util.*;

public class Contact {
    Scanner sc = new Scanner(System.in);
    String first, last, address, city, state, zip, phno, email,DOJ;
    int id;
    public boolean equals(Contact Object) {
        if (Object.first.equalsIgnoreCase(this.first) && Object.last.equalsIgnoreCase(this.last)) {
            return true;
        } else {
            return false;
        }
    }

}
