import org.junit.*;

public class TestAddressBook {
    @Test
    public void test1ReadData()
    {
        Address a=new Address();
        Assert.assertEquals(6,a.readData().size());
    }
    @Test
    public void test2CheckUpdate()
    {
        Address a=new Address();
        a.updateContact("address","h",2);
    }
    @Test
    public void test3CheckDoj()
    {
        Address a=new Address();
        Assert.assertEquals(5,a.findDoj("2015-01-01","2019-01-01"));
    }
    @Test
    public void test4CheckCity()
    {
        Address a=new Address();
        Assert.assertEquals(1,a.RetriveField("city","g"));
    }
    @Test
    public void test5CheckState()
    {
        Address a=new Address();
        Assert.assertEquals(3,a.RetriveField("state","m"));
    }
    @Test
    public void test6CheckInsert()
    {
        Address a=new Address();
        Contact c=new Contact();
        c.id=1;
        c.first="a";
        c.last="b";
        c.address="c";
        c.city="d";
        c.state="e";
        c.phno="23456";
        c.email="ab@cd";
        c.zip="45678";
        c.DOJ="2019-11-13";
        a.Insert(c);
        Assert.assertEquals(7,a.readData().size());
    }
}
