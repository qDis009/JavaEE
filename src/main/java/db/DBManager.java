package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?useUnicode=true&serverTimezone=UTC","root","General12345!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean addItem(Items item){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "insert into items(id,item_name,price,amount,manufacturer_id) "+
                    "values (null,?,?,?,?)"+
                    "");
            statement.setString(1,item.getName());
            statement.setInt(2,item.getPrice());
            statement.setInt(3,item.getAmount());
            statement.setLong(4,item.getManufacturer().getId());
            rows=statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static ArrayList<Items> getItems(){
        ArrayList<Items> items=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "select it.id, it.item_name, it.price,it.amount, it.manufacturer_id, c.name as countryName,c.short_name " +
                    "from items it " +
                    "inner join countries c on it.manufacturer_id = c.id "+
                    "order by it.price DESC ");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                items.add(new Items(
                        resultSet.getLong("id"),
                        resultSet.getString("item_name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("amount"),
                        new Countries(
                                resultSet.getLong("manufacturer_id"),
                                resultSet.getString("countryName"),
                                resultSet.getString("short_name")
                        )
                        )
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }
    public static Items getItem(long id){
        Items item=null;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "select it.id, it.item_name, it.price,it.amount, it.manufacturer_id, c.name as countryName,c.short_name " +
                    "from items it " +
                    "inner join countries c on it.manufacturer_id = c.id "+
                    "where it.id=?");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                item=new Items(
                        resultSet.getLong("id"),
                        resultSet.getString("item_name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("amount"),
                        new Countries(
                                resultSet.getLong("manufacturer_id"),
                                resultSet.getString("countryName"),
                                resultSet.getString("short_name")
                        )
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }
    public static boolean saveItem(Items item){
        int rows=0;
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    "update items set item_name = ?, price = ?,amount =?,manufacturer_id=? "+
                    "where id = ?");
            statement.setString(1,item.getName());
            statement.setInt(2,item.getPrice());
            statement.setInt(3,item.getAmount());
            statement.setLong(4,item.getManufacturer().getId());
            statement.setLong(5,item.getId());
            rows=statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static boolean deleteItem(Items item){
        int rows=0;
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    "delete from items where id = ?");
            statement.setLong(1,item.getId());
            rows=statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static ArrayList<Countries> getCountries(){
        ArrayList<Countries> countries=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "select * from countries");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                countries.add(
                        new Countries(resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("short_name"))
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return countries;
    }
    public static Countries getCountry(long id){
        Countries country=null;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "select * from countries where id=? LIMIT 1");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                country= new Countries(resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("short_name"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return country;
    }
}
