import org.junit.*;

public class TestAddressBook {
    @Test
    public void test1CheckReadData()
    {
        Address a=new Address();
        Assert.assertEquals(5,a.readData().size());
    }
}
