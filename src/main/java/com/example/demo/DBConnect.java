package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class DBConnect {
    private static DBConnect instance;
    private DBConnect() {
    }

    public static DBConnect getInstance() {
        if (instance == null) {
            synchronized (DBConnect.class) {
                if (instance == null) {
                    instance = new DBConnect();
                }
            }
        }
        return instance;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM electromag.users", (rs, rowNum) ->
                new User(rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("type"))
        );
    }

    public void createNewUser(User newUser) {
        String insertQuery = "INSERT INTO electromag.users VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery, newUser.getUsername(), newUser.getPassword(), newUser.getName(), newUser.getType());
    }

    public void deleteUser(User userToDelete) {
        String deleteQuery = "DELETE FROM electromag.users WHERE username = ?";
        jdbcTemplate.update(deleteQuery, userToDelete.getUsername());
    }

    public void changePassword(User currentUser, String newPassword) {
        String changePasswordQuery = "UPDATE electromag.users SET password = ? WHERE username = ?";
        jdbcTemplate.update(changePasswordQuery, newPassword, currentUser.getUsername());
    }

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("SELECT * FROM electromag.productsdata", (rs, rowNum) ->
                new Product(rs.getInt("id"), rs.getString("category"), rs.getString("name"),
                        rs.getFloat("price"), rs.getString("description"), rs.getInt("stock"), rs.getString("postedBy"))
        );
    }

    public void addNewProduct(Product product) {
        String insertQuery = "INSERT INTO electromag.productsdata VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery, product.getId(), product.getCategory(), product.getName(),
                product.getPrice(), product.getDescription(), product.getStock(), product.getPostedBy());
    }

    public void updateStock(Product product, int newStock) {
        String updateStockQuery = "UPDATE electromag.productsdata SET stock = ? WHERE id = ?";
        jdbcTemplate.update(updateStockQuery, newStock, product.getId());
    }

    public void editPrice(Product product, float newPrice) {
        String updatePriceQuery = "UPDATE electromag.productsdata SET price = ? WHERE id = ?";
        jdbcTemplate.update(updatePriceQuery, newPrice, product.getId());
    }

    public void deleteProduct(Product productToDelete) {
        String deleteQuery = "DELETE FROM electromag.productsdata WHERE id = ?";
        jdbcTemplate.update(deleteQuery, productToDelete.getId());
    }

    public List<Chat> generateAllChat() {
        return jdbcTemplate.query("SELECT * FROM electromag.chat", (rs, rowNum) ->
                new Chat(rs.getString("user1"), rs.getString("user2"), rs.getString("message"))
        );
    }

    public List<Chat> generateChat(User user1, User user2) {
        String sql = "SELECT * FROM electromag.chat WHERE (user1 = ? AND user2 = ?) OR (user1 = ? AND user2 = ?) ORDER BY id";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        new Chat(rs.getString("user1"), rs.getString("user2"), rs.getString("message")),
                user1.getUsername(), user2.getUsername(), user2.getUsername(), user1.getUsername());
    }

    public void addMessage(Chat chat) {
        String insertQuery = "INSERT INTO electromag.chat VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery, chat.getId(), chat.getUser1(), chat.getUser2(), chat.getMessage());
    }

    public String fetchDataFromDatabase() {
        System.out.println("Bingo");
        getAllUsers();
        getAllProducts();
        generateAllChat();

        StringBuilder result = new StringBuilder();

        String sql = "SELECT name FROM electromag.users";
        List<String> names = jdbcTemplate.queryForList(sql, String.class);

        for (String name : names) {
            result.append(name).append("\n");
        }

        return result.toString();
    }
}


//package com.example.demo;
//
//import java.io.IOException;
//import java.sql.*;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DBConnect {
//    private static String url = "jdbc:mysql://localhost:3306/electromag";
//    private static String user = "root";
//    private static String pass = "vlad123";
//    private static DBConnect instance;
//    private DBConnect() {
//    }
//
//    public static DBConnect getInstance() {
//        if (instance == null) {
//            synchronized (DBConnect.class) {
//                if (instance == null) {
//                    instance = new DBConnect();
//                }
//            }
//        }
//        return instance;
//    }
//
//    ///USER
//    public static void getAllUsers() {
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            ResultSet rs = myStatement.executeQuery("SELECT * FROM electromag.users");
//
//            List<User> userList = new ArrayList<>();
//
//            while (rs.next()) {
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//                String name = rs.getString("name");
//                String type = rs.getString("type");
//                userList.add(new User(username, password, name, type));
//            }
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(new File("D:\\Vlad\\Teme\\IS\\demo\\src\\main\\resources\\static\\JSONs\\user.json"), userList);
//
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void createNewUser(User newUser){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            String insertQuery = "INSERT INTO electromag.users VALUES (" + newUser.getUser() + ")";
//            int rowsAffected = myStatement.executeUpdate(insertQuery);
//
//            if (rowsAffected > 0) {
//                System.out.println("User inserted successfully!");
//            } else {
//                System.out.println("Failed to insert user.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void deleteUser(User userToDelete){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            String deleteQuery = "DELETE FROM electromag.users WHERE ( `username`='" + userToDelete.getUsername() + "')";
//            int rowsAffected = myStatement.executeUpdate(deleteQuery);
//
//            if (rowsAffected > 0) {
//                System.out.println("User deleted successfully!");
//            } else {
//                System.out.println("Failed to delete user.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void changePassword(User currentUser, String newPassword){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            System.out.println("UPDATE electromag.users SET `password` = '"+ newPassword +"' WHERE ( `username`='" + currentUser.getUsername() + "')");
//            String changePasswordQuery = "UPDATE electromag.users SET `password` = '"+ newPassword +"' WHERE ( `username`='" + currentUser.getUsername() + "')";
//            int rowsAffected = myStatement.executeUpdate(changePasswordQuery);
//
//            if (rowsAffected > 0) {
//                System.out.println("Users password changed succesfully successfully!");
//            } else {
//                System.out.println("Failed to change password.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    ///CHAT
//    public static void generateAllChat() {
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            ResultSet rs = myStatement.executeQuery("SELECT * FROM electromag.chat");
//
//            List<Chat> chatList = new ArrayList<>();
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String user1_2 = rs.getString("user1");
//                String user2_2 = rs.getString("user2");
//                String message = rs.getString("message");
//                chatList.add(new Chat(user1_2, user2_2, message));
//            }
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(new File("D:\\Vlad\\Teme\\IS\\demo\\src\\main\\resources\\static\\JSONs\\chat.json"), chatList);
//
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void generateChat(User user1, User user2) {
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            ResultSet rs = myStatement.executeQuery("SELECT * FROM electromag.chat WHERE (user1 = '" + user1.getUsername() + "' and user2 = '" + user2.getUsername() + "') or (user1 = '" + user2.getUsername() + "' and user2 = '" + user1.getUsername() + "') ORDER BY id;");
//
//            List<Chat> chatList = new ArrayList<>();
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String user1_2 = rs.getString("user1");
//                String user2_2 = rs.getString("user2");
//                String message = rs.getString("message");
//                chatList.add(new Chat(user1_2, user2_2, message));
//            }
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(new File("D:\\Vlad\\Teme\\IS\\demo\\src\\main\\resources\\static\\JSONs\\chat.json"), chatList);
//
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void addMessage(Chat chat){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            System.out.println("INSERT INTO electromag.chat VALUES (" + chat.getChat() + ")");
//            String insertQuery = "INSERT INTO electromag.chat VALUES (" + chat.getChat() + ")";
//            int rowsAffected = myStatement.executeUpdate(insertQuery);
//
//            if (rowsAffected > 0) {
//                System.out.println("User inserted successfully!");
//            } else {
//                System.out.println("Failed to insert user.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    ///PRODUCTS
//    public static void getAllProducts(){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            ResultSet rs = myStatement.executeQuery("SELECT * FROM electromag.productsdata");
//
//            List<Product> productList = new ArrayList<>();
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String category = rs.getString("category");
//                String name = rs.getString("name");
//                float price = rs.getFloat("price");
//                String description = rs.getString("description");
//                int stock = rs.getInt("stock");
//                String postedBy = rs.getString("postedBy");
//                productList.add(new Product(id, category, name, price, description, stock, postedBy));
//            }
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(new File("D:\\Vlad\\Teme\\IS\\demo\\src\\main\\resources\\static\\JSONs\\products.json"), productList);
//
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void addNewProduct(Product product){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            String insertQuery = "INSERT INTO electromag.productsdata VALUES (" + product.getProduct() + ")";
//            int rowsAffected = myStatement.executeUpdate(insertQuery);
//
//            if (rowsAffected > 0) {
//                System.out.println("Product inserted successfully!");
//            } else {
//                System.out.println("Failed to insert product.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void updateStock(Product product, int newStock){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            String updateStockQuery = "UPDATE electromag.productsdata SET `stock` = '"+ newStock +"' WHERE ( `id`='" + product.getId() + "')";
//            int rowsAffected = myStatement.executeUpdate(updateStockQuery);
//
//            if (rowsAffected > 0) {
//                System.out.println("Product stock updated successfully!");
//            } else {
//                System.out.println("Failed to update stock password.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void editPrice(Product product, float newPrice){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            String updateStockQuery = "UPDATE electromag.productsdata SET `price` = '"+ newPrice +"' WHERE ( `id`='" + product.getId() + "')";
//            int rowsAffected = myStatement.executeUpdate(updateStockQuery);
//
//            if (rowsAffected > 0) {
//                System.out.println("Product price updated successfully!");
//            } else {
//                System.out.println("Failed to update price password.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void deleteProduct(Product productToDelete){
//        try {
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//            String deleteQuery = "DELETE FROM electromag.productsdata WHERE ( `id`='" + productToDelete.getId() + "')";
//            int rowsAffected = myStatement.executeUpdate(deleteQuery);
//
//            if (rowsAffected > 0) {
//                System.out.println("Product deleted successfully!");
//            } else {
//                System.out.println("Failed to delete product.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    ///ORDERS?
//
//    String fetchDataFromDatabase() {
//        StringBuilder result = new StringBuilder();
//
//        // Database connection details
//        String url = "jdbc:mysql://localhost:3306/electromag";
//        String user = "root";
//        String pass = "vlad123";
//
//        try {
//            // Establish database connection
//            Connection myConn = DriverManager.getConnection(url, user, pass);
//            Statement myStatement = myConn.createStatement();
//
//            // Execute SQL query
//            String sql = "select * from electromag.users";
//            ResultSet rs = myStatement.executeQuery(sql);
//
//            // Process the result set
//            while (rs.next()) {
//                result.append(rs.getString("name")).append("\n");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result.toString();
//    }
//
//    public static void main(String[] args) {
////        getAllUsers();
////        deleteUser(new User("aaa","aaa","aaa","aaa"));
////        changePassword(new User("cristian_dumitru", "pass123", "Cristian Dumitru", "seller"), "vladafostaici");
////        getAllProducts();
////        deleteProduct(new Products(20));
////        addNewProduct(new Products(20, "aaa","sas", (float)99.99, "sasas", 36, "roxana_petre"));
////        updateStock(new Products(20), 69);
////        editPrice(new Products(20),(float)21212.0);
////        generateChat(new User("ana_popescu"), new User("ion_mihai"));
////        addMessage(new Chat("ana_popescu","ion_mihai", "Hai sa iesim la suc bo$$"));
//        generateAllChat();
//    }
//}