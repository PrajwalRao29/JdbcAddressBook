
import com.opencsv.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import java.util.stream.*;

public class Address {
    static Scanner sc = new Scanner(System.in);
    ConnectionRetriever con=new ConnectionRetriever();
    PreparedStatement Statement;

    public  List<Contact> readData(){
        String sql="select * from contact c, address a where c.contact_id=a.contact_id ;";
        List<Contact> arr=new ArrayList<Contact>();
        try
        {
            Connection connection=con.getConnection();
            Statement=connection.prepareStatement(sql);
            ResultSet result=Statement.executeQuery();
            while(result.next())
            {
                Contact c=new Contact();
                c.first=result.getString("first_name");
               c.last=result.getString("last_name");
               c.address=result.getString("address");
               c.email=result.getString("email");
               c.phno=result.getString("phone_no");
               c.city=result.getString("city");
                c.state=result.getString("state");
                c.zip=result.getString("zip");
                arr.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }

//    public void updateContact(String field,String data,int contact_id)
//    {
//        String sql="update contact c, address a set "+field+"=? where c.contact_id=a.contact_id and c.contact_id=?;";
//        try
//        {
//            Connection connection=con.getConnection();
//            Statement=connection.prepareStatement(sql);;
//            Statement.setString(1,data);
//            Statement.setInt(2,contact_id);
//            Statement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
    static boolean checkDuplicate(AddressBook adbook, Contact contact) {
        return (adbook.ar.stream().anyMatch(c -> c.equals(contact)));
    }

    static List<Contact> searchNameByCity(AddressBook adbook, String cityString) {
        return adbook.ar.stream().filter(c -> c.city.equals(cityString)).collect(Collectors.toList());
    }

    static List<Contact> searchNameByState(AddressBook adbook, String stateString) {
        return adbook.ar.stream().filter(c -> c.state.equals(stateString)).collect(Collectors.toList());
    }

    static void sortByName(AddressBook adbook) {
        adbook.ar.stream().sorted((c1, c2) -> c1.first.concat(c1.last).compareTo(c2.first.concat(c2.last))).forEach(System.out::println);

    }

    static void sortByCity(AddressBook adbook) {
        adbook.ar.stream().sorted((c1, c2) -> c1.city.compareTo(c2.city))
                .forEach(c -> System.out.println(c.first + ", " + c.last + "belongs to city " + c.city));

    }

    static void sortByState(AddressBook adbook) {
        adbook.ar.stream().sorted((c1, c2) -> c1.state.compareTo(c2.state))
                .forEach(c -> System.out.println(c.first + ", " + c.last + " belongs to state " + c.state));

    }

    static void sortByZip(AddressBook adbook) {
        adbook.ar.stream().sorted((c1, c2) -> c1.zip.compareTo(c2.zip))
                .forEach(c -> System.out.println(c.first + ", " + c.last + " belongs to zip " + c.zip));

    }

    static ArrayList<AddressBook> createAddressBook(ArrayList<AddressBook> adbook) {
        System.out.println("Enter the address book name to be created");
        String n = sc.next();
        int key = 0;
        for (int i = 0; i < adbook.size(); i++) {
            if (adbook.get(i).BookName.equalsIgnoreCase(n)) {
                key = 1;
                break;
            }
        }
        if (key == 0) {
            AddressBook b = new AddressBook();
            b.BookName = n;
            b.ar = new ArrayList<Contact>();
            adbook.add(b);
            return adbook;
        } else {
            System.out.println("Address Book Already Exists.");
            return createAddressBook(adbook);
        }
    }

    static boolean writeData(ArrayList<Contact> arr)
    {
        StringBuffer s=new StringBuffer();
        arr.stream().forEach(c ->
        {
            String con=c.toString().concat("\n");
            s.append(con);
        });
        try
        {
            Path p=Paths.get("C:\\Users\\Latha r rao\\eclipse-work\\AddressBook\\src\\DATA.txt");
            Files.write(p,s.toString().getBytes());
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    static boolean writeDataCSV(ArrayList <Contact> arr)
    {
        String filename = "C:\\Users\\Latha r rao\\eclipse-work\\AddressBook\\src\\DATAcsv.csv";
        File outputFile = new File(filename);
        try (FileWriter outputFileWriter = new FileWriter(outputFile);
             CSVWriter outputCSVWriter = new CSVWriter(outputFileWriter)){
            String[] header = {"First Name", "Last Name", "Address", "City", "State", "PhoneNumber", "Email", "Zip" };
            outputCSVWriter.writeNext(header);
            for(Contact contact : arr) {
                String[] rowData = {contact.first, contact.last,
                        contact.address, contact.city, contact.state,
                        contact.phno, contact.email,contact.zip};
                outputCSVWriter.writeNext(rowData);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    static ArrayList<AddressBook> accessAddressBook(ArrayList<AddressBook> adbook) {
        int key = 0;
        System.out.println("Enter the address book name to be accessed");
        String b = sc.next();
        for (int j = 0; j < adbook.size(); j++) {
            if (adbook.get(j).BookName.equalsIgnoreCase(b)) {
                key = 1;
                int x = 0;
                while (x != 9) {
                    System.out.println("1.ADD A CONTACT");
                    System.out.println("2.EDIT A CONTACT BASED ON NAME");
                    System.out.println("3.VIEW CONTACT DETAILS BASED ON NAME");
                    System.out.println("4.DELETE A CONTACT");
                    System.out.println("5.SORT ADDRESS BOOK BASED ON NAME");
                    System.out.println("6.SORT ADDRESS BOOK BASED ON CITY");
                    System.out.println("7.SORT ADDRESS BOOK BASED ON STATE");
                    System.out.println("8.SORT ADDRESS BOOK BASED ON ZIP");
                    System.out.println("9.EXIT CURRENT ADDRESS BOOK");
                    x = sc.nextInt();
                    Contact a;
                    if (x == 1) {
                        a = new Contact();
                        a.addContact();
                        if (checkDuplicate(adbook.get(j), a)) {
                            System.out.println("Contact Details Already Exists");
                        } else {
                            adbook.get(j).ar.add(a);
                        }
                    } else if (x == 2) {
                        System.out.println("Enter First Name");
                        String f = sc.next();
                        System.out.println("Enter Last Name");
                        String l = sc.next();
                        int k = 0, i = 0;
                        for (i = 0; i < adbook.get(j).ar.size(); i++) {
                            if (adbook.get(j).ar.get(i).first.equalsIgnoreCase(f)
                                    && adbook.get(j).ar.get(i).last.equalsIgnoreCase(l)) {
                                k = 1;
                                break;
                            }
                        }
                        if (k == 0) {
                            System.out.println("******No Contact found******");
                        } else {
                            adbook.get(j).ar.get(i).editContact();
                        }
                    } else if (x == 3) {
                        System.out.println("Enter First Name");
                        String f = sc.next();
                        System.out.println("Enter Last Name");
                        String l = sc.next();
                        int k = 0, i = 0;
                        for (i = 0; i < adbook.get(j).ar.size(); i++) {
                            if (adbook.get(j).ar.get(i).first.equalsIgnoreCase(f)
                                    && adbook.get(j).ar.get(i).last.equalsIgnoreCase(l)) {
                                k = 1;
                                break;
                            }
                        }
                        if (k == 0) {
                            System.out.println("******No Contact found******");
                        } else {
                            adbook.get(j).ar.get(i).viewContact();
                        }
                    } else if (x == 4) {
                        System.out.println("Enter First Name");
                        String f = sc.next();
                        System.out.println("Enter Last Name");
                        String l = sc.next();
                        int k = 0;
                        for (int i = 0; i < adbook.get(j).ar.size(); i++) {
                            if (adbook.get(j).ar.get(i).first.equalsIgnoreCase(f)
                                    && adbook.get(j).ar.get(i).last.equalsIgnoreCase(l)) {
                                k = 1;
                                adbook.get(j).ar.remove(i);
                                System.out.println("Contact Deleted");
                                break;
                            }
                        }
                        if (k == 0) {
                            System.out.println("******No Contact found******");

                        }
                    } else if (x == 5) {
                        sortByName(adbook.get(j));
                        break;
                    }
                    else if (x == 6) {
                        sortByCity(adbook.get(j));
                        break;
                    }
                    else if (x == 7) {
                        sortByState(adbook.get(j));
                        break;
                    }
                    else if (x == 8) {
                        sortByZip(adbook.get(j));
                        break;
                    }
                }
            }
            writeData(adbook.get(j).ar);
            writeDataCSV(adbook.get(j).ar);
        }
        if (key == 0) {
            System.out.println("******Address Book does not exist*******");
        }
        return adbook;
    }


    static void accessByCity(ArrayList<AddressBook> adbook) {
        System.out.println("Enter the city ");
        String city = sc.next();
        List<Contact> l = new ArrayList();
        for (int k = 0; k < adbook.size(); k++) {
            l.addAll(searchNameByCity(adbook.get(k), city));
        }
        if (l.size() == 0) {
            System.out.println("No Contacts Found");
        } else {
            for (int k = 0; k < adbook.size(); k++) {
                System.out.println(l.get(k).first + " " + l.get(k).last);
            }
            System.out.println("The number of persons living in city " + city + " are " + l.size());
        }
    }

    static void accessByState(ArrayList<AddressBook> adbook) {
        System.out.println("Enter the State ");
        String state = sc.next();
        List<Contact> l = new ArrayList<Contact>();
        for (int k = 0; k < adbook.size(); k++) {
            l.addAll(searchNameByState(adbook.get(k), state));
        }
        if (l.size() == 0) {
            System.out.println("No Contacts Found");
        } else {
            for (int k = 0; k < adbook.size(); k++) {
                System.out.println(l.get(k).first + " " + l.get(k).last);
            }
            System.out.println("The number of persons living in state " + state + " are " + l.size());
        }
    }

    public static void main(String args[]) {
        System.out.println("Welcome to Address Book program");
        ArrayList<AddressBook> adbook = new ArrayList<AddressBook>();
        int r = 0;
        while (r != 6) {
            System.out.println("1.CREATE ADDRESSBOOK");
            System.out.println("2.ACCESS ADDRESSBOOK");
            System.out.println("3.CHECK CONTACTS IN CITY");
            System.out.println("4.CHECK CONTACTS IN STATE");
            System.out.println("5.PRINT DATA");
            System.out.println("6.EXIT APPLICATION");
            r = sc.nextInt();
            switch (r)
            {
                case 1: {
                    adbook = createAddressBook(adbook);
                    break;
                }
                case 2: {
                    adbook = accessAddressBook(adbook);
                    break;
                }
                case 3: {
                    accessByCity(adbook);
                    break;
                }
                case 4: {
                    accessByState(adbook);
                    break;
                }
                case 5: {
                    try {
                        FileReader f=new FileReader("C:\\Users\\Latha r rao\\eclipse-work\\AddressBook\\src\\DATA.txt");
                        int i;
                        while((i=f.read())!=-1)
                            System.out.print((char)i);
                    }
                    catch(Exception e) {

                    }
                    break;
                }
            }
        }
    }
}
