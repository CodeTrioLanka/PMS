/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code;
import com.mysql.cj.x.protobuf.MysqlxSql;
import java.sql.*;

/**
 *
 * @author ravin
 */
public class DBclass {
    
    private String user="root";
    private String pass="12345678";
    private String host="localhost:3306";
    private String db="mydb";
    
    private Connection con;
    
    public int insert(String sql) throws SQLException{
        Statement stmt=con.prepareCall(sql);
        int count=stmt.executeUpdate(sql);
        return  count;
    }
    
    public int update(String sql) throws SQLException{
        Statement stmt=con.prepareCall(sql);
        int count=stmt.executeUpdate(sql);
        return  count;
    }
    
    public int delete(String sql) throws SQLException{
        Statement stmt=con.prepareCall(sql);
        int count=stmt.executeUpdate(sql);
        return  count;
    }
    
    public ResultSet select(String sql) throws SQLException{
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        return  rs;
        
    }
    
    public void connect() throws SQLException{
        String url="jdbc:mysql://"+host+"/"+db;
        con=DriverManager.getConnection(url, user, pass);
        System.out.println("connect");
    }
    
    public void conclose() throws SQLException{
        con.close();
    }
    public ResultSet select2(String sql1) throws SQLException{
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(sql1);
        return  rs;
        
    }
    
     public ResultSet select3(String sql2) throws SQLException{
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(sql2);
        return  rs;
        
    }
}
