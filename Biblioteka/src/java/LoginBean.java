
import java.sql.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped

public class LoginBean {
    private String username;
    private String password;
    private String name;
    private String secName;
    private String surname;
    private String dbusername;
    private String dbpassword;
    private String choice;
    private String dbName;
    private String newName;
    private String year;
    private String kod;
    private String city;
    private String adress;
    private String miejscowosc;

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbSurname() {
        return dbSurname;
    }

    public void setDbSurname(String dbSurname) {
        this.dbSurname = dbSurname;
    }
    private String dbSurname;

     public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }
    
    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getDbusername() {
        return dbusername;
    }

    public void setDbusername(String dbusername) {
        this.dbusername = dbusername;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }
   
    

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    Connection con;
    Statement ps;
    ResultSet rs;
    String SQL_Str;
    PreparedStatement ps1;
    
    public void dbData(){
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");
           ps = con.createStatement();
           System.out.println("Polaczony z baza danych");
           
        }
        catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occur :" + ex);
        }
           
    }
    public void setFirstName()
    {
        setName(name);
        newName = name;
        System.out.println("Po secie:" + newName);
        
    }
    public String generate(){
        System.out.println("Przed:" + newName);
       return username = "damcios";
        
    }
    public String login(){
    //dbData();
    
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");
    System.out.println("Trwa logowanie...");
    SQL_Str = "Select * from biblioteka.czytelnik WHERE login like ('" + username +"')";
    
    ps1 = con.prepareStatement(SQL_Str);
    rs = ps1.executeQuery(SQL_Str); 
    
    rs.next();
    
    dbusername = rs.getString("login").toString();
    dbpassword = rs.getString("password").toString();
    
    }
    catch (Exception ex){
    ex.printStackTrace();
    System.out.println("Exception Occur :" + ex);
    }
    
    if(username.equalsIgnoreCase(dbusername))
    {
        if(password.equals(dbpassword))
            return "valid";
        else
            return "invalid";            
    }
    else
        return "invalid";
    }
    public String generateLogin(){
       // dbData();

       Connection conn = null;
       Statement stmt = null;
   try{
       System.out.println("Naciskam klawisz");
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT Imie, Nazwisko FROM biblioteka.czytelnik";
      ResultSet rs = stmt.executeQuery(sql);
      
        char user = name.charAt(0);
        char userSec = secName.charAt(0);
        
      while(rs.next()){
         String nameData = rs.getString("Imie");
         String surnameData = rs.getString("Nazwisko");
         
            
            
         if(name.equals(nameData) && surname.equals(surnameData)){           
          username = userSec + surname;
          username = user + username;
              
          break;
         }
         else
             username = user + surname;
            
      }
         
            username = username.toLowerCase();
            
            
   }
  
          catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Exception Occur :" + ex);
            System.out.println("BLAAAAAAAAAAD");
          }
         finally{
        return username;
   }
    }
         
    public String register(){
   //dbData();
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");
    SQL_Str = "INSERT INTO biblioteka.czytelnik(Imie, Nazwisko, Kod_pocztowy, Miejscowosc, Adres, Drugie_imie,login, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    ps1=con.prepareStatement(SQL_Str); 
   
    ps1.setString(1,name);
    ps1.setString(2,surname);
    ps1.setString(3,kod);
    ps1.setString(4,city);
    ps1.setString(5,adress);
    ps1.setString(6,secName); 
    ps1.setString(7,generateLogin()); 
    ps1.setString(8,password); 
    ps1.executeUpdate();
    
 
    }
    catch (Exception ex){
    ex.printStackTrace();
    System.out.println("Exception Occur :" + ex);
    }
   return "success";
    }
   /*  public static void main(String[] args) throws Exception {
     LoginBean login = new LoginBean();
     login.username = "admin";
     login.password = "admin";
     System.out.println(login.login());
     }*/
    /* Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Users","root","root");

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT name, surname FROM users";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
    /*  while(rs.next()){
         //Retrieve by column name
         String name = rs.getString("name");
         String surname = rs.getString("surname");

         //Display value
         System.out.print("name: " + name);
         System.out.println(", surname: " + surname);
         
      }*/
   /*   String dbName = "Damian";
      while(rs.next()){
         String name = rs.getString("name");
         String surname = rs.getString("surname");
         
         if(dbName.equals(name)){
             System.out.println("Istnieje juz takie imie");
   }
      }
   }
      catch(Exception ex){
           ex.printStackTrace();
    System.out.println("Exception Occur :" + ex);   
              
              }
*/
//  } 
    
    
}


