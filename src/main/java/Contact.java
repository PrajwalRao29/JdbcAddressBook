import java.util.*;

public class Contact {
    Scanner sc = new Scanner(System.in);
    String first, last, address, city, state, zip, phno, email;
    public boolean equals(Contact o) {
        if (o.first.equalsIgnoreCase(this.first) && o.last.equalsIgnoreCase(this.last)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return this.first+ ", " + this.last+ ", " + this.address + ", " + this.city+ ", " + this.state + ", " + this.zip+ ", " + this.phno+ ", " + this.email;
    }

}
