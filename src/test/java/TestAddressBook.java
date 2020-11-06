import org.junit.*;

import java.util.*;

public class TestAddressBook {
    @Test
    public void test1GivenAddressBook_WhenReadData_ShouldReturnSize()
    {
        Address address=new Address();
        Assert.assertEquals(9,address.readData().size());
    }
    @Test
    public void test2GivenAddressBook_WhenCheckUpdate_ShouldReturnField()
    {
        Address address=new Address();
        address.updateContact("address","l",2);
    }
    @Test
    public void test3GivenAddressBook_WhenCheckDoj_ShouldReturnCount()
    {
        Address address=new Address();
        Assert.assertEquals(3,address.findDoj("2019-01-01","2019-12-12"));
    }
    @Test
    public void test4GivenAddressBook_WhenCheckCity_ShouldReturnCount()
    {
        Address address=new Address();
        Assert.assertEquals(3,address.RetriveField("city","g"));
    }
    @Test
    public void test5GivenAddressBook_WhenCheckState()
    {
        Address address=new Address();
        Assert.assertEquals(2,address.RetriveField("state","l"));
    }

    @Test
    public void test6GivenAddressBook_WhenCheckInsert_ShouldReturnCount()
    {
        Address a=new Address();
        int count=a.readData().size();
        Contact contact=new Contact();
        contact.id=1;
        contact.first="a";
        contact.last="b";
        contact.address="contact";
        contact.city="d";
        contact.state="e";
        contact.phno="23456";
        contact.email="ab@cd";
        contact.zip="45678";
        contact.DOJ="2019-11-13";
        a.Insert(contact);
        Assert.assertEquals(count+1,a.readData().size());
    }
    @Test
    public void test7GivenAddressBook_WhenCheckMultipleInserts_ShouldReturnCount()
    {   Address a=new Address();
        int count=a.readData().size();
        ArrayList<Contact> arr=new ArrayList<>();
        Contact contact=new Contact();
        contact.id=1;
        contact.first="a";
        contact.last="b";
        contact.address="contact";
        contact.city="d";
        contact.state="e";
        contact.phno="23456";
        contact.email="ab@cd";
        contact.zip="45678";
        contact.DOJ="2019-11-13";
        Contact contact1=new Contact();
        contact1.id=1;
        contact1.first="d";
        contact1.last="e";
        contact1.address="f";
        contact1.city="g";
        contact1.state="h";
        contact1.phno="78901";
        contact1.email="de@gh";
        contact1.zip="98765";
        contact1.DOJ="2016-05-07";
        arr.add(contact1);
        arr.add(contact);
        a.insertMultiple(arr);
        Assert.assertEquals(count+2,a.readData().size());
    }
}
