import org.junit.*;

public class TestAddressBook {
    @Test
    public void test1ReadData()
    {
        Address a=new Address();
        Assert.assertEquals(5,a.readData().size());
    }
    @Test
    public void test1CheckUpdate()
    {
        Address a=new Address();
        a.updateContact("address","h",2);
    }
}
