import org.junit.*;

public class TestAddressBook {
    @Test
    public void test1ReadData()
    {
        Address a=new Address();
        Assert.assertEquals(5,a.readData().size());
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
        Assert.assertEquals(4,a.findDoj("2015-01-01","2019-01-01"));
    }
}
