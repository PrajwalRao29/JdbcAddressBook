import com.mysql.cj.protocol.*;

import java.sql.*;
import java.util.*;


public class Address {
    static Scanner scanner = new Scanner(System.in);
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
                Contact contact = new Contact();
                contact.first = result.getString("first_name");
                contact.last = result.getString("last_name");
                contact.address = result.getString("address");
                contact.email = result.getString("email");
                contact.phno = result.getString("phone_no");
                contact.city = result.getString("city");
                contact.state = result.getString("state");
                contact.zip = result.getString("zip");
                arr.add(contact);
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
            Statement.setString(1, data);
            ResultSet resultSet = Statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public void Insert(Contact contact)
    {
        String sql="insert into contact (id,first_name,last_name,phone_no,email) values (?,?,?,?,?);";
        try {
            int id=0;
            Connection connection = con.getConnection();
            Statement = connection.prepareStatement(sql);
            Statement.setInt(1, contact.id);
            Statement.setString(2,contact.first);
            Statement.setString(3,contact.last);
            Statement.setString(4,contact.phno);
            Statement.setString(5,contact.email);
            Statement.executeUpdate();
            //getting the contact id which is assigned by auto increment
            Statement = connection.prepareStatement("select contact_id from contact where phone_no=?");
            Statement.setString(1,contact.phno);
            ResultSet resultSet=Statement.executeQuery();
            while(resultSet.next())
            {
                id=resultSet.getInt(1);
                break;
            }
            //using contact id to assign address table
            Statement = connection.prepareStatement("insert into address (contact_id,address,city,state,zip,DOJ) values (?,?,?,?,?,?);");
            Statement.setInt(1,id);
            Statement.setString(2,contact.address);
            Statement.setString(3,contact.city);
            Statement.setString(4,contact.state);
            Statement.setString(5,contact.zip);
            Statement.setString(6,contact.DOJ);
            Statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertMultiple(ArrayList<Contact> arr) {
        for(Contact contact:arr) {
            Runnable task = () -> {
               Insert(contact);
            };
            Thread thread = new Thread(task);
            thread.start();
            try {
                thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}