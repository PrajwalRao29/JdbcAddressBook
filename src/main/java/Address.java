import java.sql.*;
import java.util.*;


public class Address {
    static Scanner sc = new Scanner(System.in);
    ConnectionRetriever con = new ConnectionRetriever();
    PreparedStatement Statement;

    public List<Contact> readData() {
        String sql = "select * from contact c, address a where c.contact_id=a.contact_id ;";
        List<Contact> arr = new ArrayList<Contact>();
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            ResultSet result = Statement.executeQuery();
            while (result.next()) {
                Contact c = new Contact();
                c.first = result.getString("first_name");
                c.last = result.getString("last_name");
                c.address = result.getString("address");
                c.email = result.getString("email");
                c.phno = result.getString("phone_no");
                c.city = result.getString("city");
                c.state = result.getString("state");
                c.zip = result.getString("zip");
                arr.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }

    public void updateContact(String field, String data, int contact_id) {
        String sql = "update contact c, address a set " + field + "=? where c.contact_id=a.contact_id and c.contact_id=?;";
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            ;
            Statement.setString(1, data);
            Statement.setInt(2, contact_id);
            Statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int findDoj(String start, String end) {
        int count = 0;
        String sql = "select * from address where DOJ between ? and ?;";
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            ;
            Statement.setString(1, start);
            Statement.setString(2, end);
            ResultSet r = Statement.executeQuery();
            while (r.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int RetriveField(String field,String data)
    {
        int count = 0;
        String sql = "select * from address where "+field+"=?";
        try {
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            ;
            Statement.setString(1, data);
            ResultSet r = Statement.executeQuery();
            while (r.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}