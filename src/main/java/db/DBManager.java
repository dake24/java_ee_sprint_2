package db;

import model.Laptop;
import model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static Connection con;
    private static PreparedStatement statement;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/marketplace", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Laptop> getLaptops() throws SQLException {
        statement = con.prepareStatement("SELECT * from laptop");
        ResultSet rs = statement.executeQuery();

        List<Laptop> laptops = new ArrayList<>();

        while (rs.next()) {
            Laptop l = new Laptop(rs.getInt("id"),
                    rs.getString("model"),
                    rs.getInt("price"),
                    rs.getInt("count"));

            laptops.add(l);
        }

        return laptops;
    }

    public static Laptop getLaptop(int id) throws SQLException {
        statement = con.prepareStatement("SELECT * from laptop where id=?");
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        Laptop l = null;
        while (rs.next()) {
            l = new Laptop(rs.getInt("id"),
                    rs.getString("model"),
                    rs.getInt("price"),
                    rs.getInt("count"));
        }

        return l;
    }

    public static void addProduct(Laptop laptop) throws SQLException {
        statement = con.prepareStatement("INSERT INTO laptop values (null, ?, ?, ?, ?)");
        statement.setString(1, laptop.getModel());
        statement.setInt(2, laptop.getPrice());
        statement.setInt(3, laptop.getCount());

        statement.executeUpdate();
    }

    public static int deleteProduct(int id) throws SQLException {
        statement = con.prepareStatement("DELETE FROM laptop WHERE id=" + id);
        return statement.executeUpdate();
    }

    public static Users getUser(String email) throws SQLException {
        statement = con.prepareStatement("SELECT * from users where email=?");
        statement.setString(1, email);

        ResultSet rs = statement.executeQuery();

        Users u = null;
        if (rs.next()) {
            u = new Users(
                    rs.getLong("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("fullname")
            );
        }

        return u;
    }
}
