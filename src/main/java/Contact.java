import java.util.*;

public class Contact {
    Scanner sc = new Scanner(System.in);
    String first, last, address, city, state, zip, phno, email;
    int adbook_id,contact_id,contact_type,type_id;
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

    public void addContact() {

        System.out.println("Enter First Name");
        this.first = sc.next();
        System.out.println("Enter Last Name");
        this.last = sc.next();
        System.out.println("Enter Address");
        this.address = sc.next();
        System.out.println("Enter city");
        this.city = sc.next();
        System.out.println("Enter state");
        this.state = sc.next();
        System.out.println("Enter zip code");
        this.zip = sc.next();
        System.out.println("Enter phone number");
        this.phno = sc.next();
        System.out.println("Enter email");
        this.email = sc.next();
    }

    public void editContact() {
        System.out.println("Enter Address");
        this.address = sc.next();
        System.out.println("Enter city");
        this.city = sc.next();
        System.out.println("Enter state");
        this.state = sc.next();
        System.out.println("Enter zip code");
        this.zip = sc.next();
        System.out.println("Enter phone number");
        this.phno = sc.next();
        System.out.println("Enter email");
        this.email = sc.next();
    }

    public void viewContact() {
        System.out.println("ADDRESS = " + this.address);
        System.out.println("CITY = " + this.city);
        System.out.println("STATE = " + this.state);
        System.out.println("ZIP = " + this.zip);
        System.out.println("PHONE NUMBER =" + this.phno);
        System.out.println("EMAIL =" + this.email);
    }
}
