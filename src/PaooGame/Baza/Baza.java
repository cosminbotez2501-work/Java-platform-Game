package PaooGame.Baza;
import PaooGame.Meniu.MyFrame;
import PaooGame.RefLinks;

import javax.swing.*;
import java.sql.*;
import java.sql.Array;
//import oracle.sql.ARRAY;



public class Baza {

    public synchronized static void createDB(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GameDB.db");
            //c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS SAVEGAME " +
                    "(Id INT NOT NULL,"+
                    "CrtDate TEXT NOT NULL, "+
                    "MapID INT NOT NULL,"+
                    "Life INT NOT NULL, "+
                    "xHero INT NOT NULL, "+
                    "yHero INT NOT NULL, "+
                    "ITEME TEXT NOT NULL, "+
                    "Chest INT NOT NULL, "+
                    "Score INT NOT NULL, "+
                    "Enemy TEXT NOT NULL)";


            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS SCOREBOARD " +
                    "(CrtDate TEXT NOT NULL, "+
                    "Score INT NOT NULL)";
            //"Time INT NOT NULL)";
            stmt.execute(sql);

            stmt.close();

            c.close();

        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public synchronized static void SaveState(RefLinks ref,int op){
        if(op==0)
            DeleteSave(op);
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GameDB.db");
            c.setAutoCommit(false);
//            stmt = c.prepareStatement("DELETE FROM SAVEGAME;");
//            stmt.executeUpdate();
//            stmt.close();
            String sql;

            sql = "INSERT INTO SAVEGAME (Id,CrtDate,MapID,Life,xHero,yHero,ITEME,Chest,Score,Enemy) "+
                    "VALUES (?,?,?,?,?,?,?,?,?,?);";
            stmt = c.prepareStatement(sql);
            //System.out.println("Stmt initiat");
            stmt.setInt(1,op+1);
            stmt.setObject(2,java.time.LocalDate.now());
            //System.out.println("param 1");
            stmt.setInt(3,ref.GetMap().getId());
            //System.out.println("param 2");
            stmt.setInt(4,ref.GetHero().GetLife());
            stmt.setFloat(5,ref.GetHero().GetX());
            stmt.setFloat(6,ref.GetHero().GetY());
            stmt.setString(7,ref.GetItemManager().getItem());
            stmt.setInt(8,ref.GetInteraction().isOpenChest());
            stmt.setInt(9,ref.getStatusPanel().getScore());
            stmt.setString(10,ref.GetHero().GetEnemy());
            stmt.executeUpdate();
            c.commit();
            stmt.close();

            c.close();
        }catch (SQLException e){
            System.err.println("EroareSQL: "+ e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        catch ( Exception e ) {
            System.err.println("Eroare necunoascuta"+ e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
//    public static void main(String[] args){
//        Connection c = null;
//        Statement stmt = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:GameDB.db");
//            //c.setAutoCommit(false);
//            stmt = c.createStatement();
//            String sql;
//
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM SAVEGAME;" );
//            while ( rs.next() ) {
//                String M1 = rs.getString("CrtDate");
//                int M2 = rs.getInt("MapID");
//
//                System.out.println(M1+" "+M2);
//            }
//            rs.close();
//
//
//
//            stmt.close();
//
//            c.close();
//        }catch ( Exception e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }
//    }

    public synchronized static String LoadState(){

        String result = null;
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:GameDB.db");
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEGAME WHERE Id = (SELECT MAX(Id) FROM SAVEGAME);")) {

            if (rs.next()) {
//                int id = rs.getInt("Id");
                String M1 = rs.getString("CrtDate");
                int M2 = rs.getInt("MapID");
                int M3 = rs.getInt("Life");
                float M4 = rs.getFloat("xHero");
                float M5 = rs.getFloat("yHero");
                String M9 = rs.getString("ITEME");
                int M6 = rs.getInt("Score");
                int M7 = rs.getInt("Chest");
                String M8 = rs.getString("Enemy");
                result = (M1 + " " + M2 + " " + M3 + " " + M4 + " " + M5+ " " + M9 + " " + M6 + " " + M7 + " " + M8 );
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return result;
    }
    public synchronized static void DeleteSave(int op){

        try(Connection c = DriverManager.getConnection("jdbc:sqlite:GameDB.db");
            Statement stmt = c.createStatement();) {

            c.setAutoCommit(false);

            stmt.executeUpdate("DELETE FROM SAVEGAME WHERE Id = (SELECT MAX(Id) FROM SAVEGAME)");


            c.commit();

        }catch (SQLException e){
            System.err.println("EroareSQL: "+ e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public synchronized static void LoadScore(MyFrame menu){

        //String result = null;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:GameDB.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCOREBOARD ORDER BY Score DESC;");
            int id =0;
            while (rs.next()) {
                id++;
                String M1 = rs.getString("CrtDate");
                int M2 = rs.getInt("Score");
                //String M3 = rs.getString("Time");
                //System.out.println(M1 + " " + M2);
                menu.setScore(M2,id);
            }

        }catch(NullPointerException | SQLException e){
            JOptionPane.showMessageDialog(null,"Nu exista scor salvat anterior!");
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
    public synchronized static void SaveScore(RefLinks ref){
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GameDB.db");
            c.setAutoCommit(false);
//            stmt = c.prepareStatement("DELETE FROM SCOREBOARD;");
//            stmt.executeUpdate();
//            stmt.close();
            String sql;

            sql = "INSERT INTO SCOREBOARD (CrtDate,Score) "+
                    "VALUES (?,?);";
            stmt = c.prepareStatement(sql);
            //System.out.println("Stmt initiat");
            stmt.setObject(1,java.time.LocalDate.now());
            //System.out.println("param 1");

            stmt.setInt(2,ref.getStatusPanel().getScore());
            //stmt.setString(3,ref.GetHero().GetEnemy());
            stmt.executeUpdate();
            c.commit();
            stmt.close();

            c.close();
        }catch (SQLException e){
            System.err.println("EroareSQL: "+ e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        catch ( Exception e ) {
            System.err.println("Eroare necunoascuta"+ e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
