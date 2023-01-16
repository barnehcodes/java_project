import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class database {
    String database, host, user, password;
    int port;
    Connection cnx;

public database() throws ClassNotFoundException, SQLException{
    database="supmti";
    host="localhost";
    port=3306;
    user="root";
    password="";
    Class.forName("com.mysql.cj.jdbc.Driver");
    cnx= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+ "/"+database+
    "?serverTimezone=UTC",user,password);


    
}

public Vector<User> getUsers(){
    Vector<User>users = new Vector<>();
    try{
        Statement stm =cnx.createStatement();
        ResultSet resultat = stm.executeQuery("SELECT * FROM user");
        String username, password;
        while(resultat.next()){
            username = resultat.getString("username");
            password = resultat.getString("password");
            users.add(new User(username, password));

        }
    }catch(SQLException e){
        e.printStackTrace();
    }
    return users;
}
public static void main(String[] args)throws ClassNotFoundException,SQLException{

    System.out.println(new database().getUsers());
    
}
}



