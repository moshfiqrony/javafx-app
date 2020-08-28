
package fxapp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXAPP2 extends Application {
    Statement statement;
    int id = 0;
    
    public Text msg = new Text("");
    public Text ut = new Text("Username");
    public Text pt = new Text("Password");
    public Text vu = new Text("");
    public Text vp = new Text("");
    
    public TextField uf = new TextField();
    public TextField pf = new TextField();
    public TextField sf = new TextField();
    
    Button bl = new Button("Registration");
    Button bv = new Button("View");
    Button be = new Button("Edit");
    Button bu = new Button("Update");
    Button bd = new Button("Delete");
    
    @Override
    public void start(Stage primaryStage) {
        DBConnect();
        
        GridPane root = new GridPane();
        
        root.add(msg, 0, 0);
        root.add(ut, 0, 1);
        root.add(uf, 1, 1);
        root.add(pt, 0, 2);
        root.add(pf, 1, 2);
        root.add(bl, 0,3);
        root.add(bu, 1,3);
        root.add(sf, 0,4);
        root.add(bv, 1,4);
        root.add(be, 2,4);
        root.add(bd, 3,5);
        root.add(vu, 0,5);
        root.add(vp, 1,5);
        
        bl.setOnAction(e->insert());
        bv.setOnAction(e->view());
        be.setOnAction(e->edit());
        bu.setOnAction(e->update());
        bd.setOnAction(e->delete());
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXAPP");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void DBConnect(){
        try {
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/fxapp","root","");
            statement = conn.createStatement();
            msg.setText("Database Connected");
        } catch (Exception e) {
            msg.setText("Database Not Connected");
        }
    }
    
    public void insert(){
        try{
            String insertquery = "INSERT INTO `user`(`uname`, `pass`) VALUES ('"+uf.getText()+"', '"+pf.getText()+"')";
            statement.executeUpdate(insertquery);
            msg.setText("Inserted");
        } catch(Exception e){
            msg.setText("Not Inserted");
        }
    }
     public void view(){
        try {
            String insertquery = "select * from user where uname = '"+sf.getText()+"'";
            ResultSet result = statement.executeQuery(insertquery);
            if(result.next()){
                vu.setText("User Name: " + result.getString(2));
                vp.setText("Password: " + result.getString(3));
            }
        } catch (SQLException ex) {
            msg.setText("Problem to Show Data");
        }
     }
     
     public void edit(){
         try {
            String insertquery = "select * from user where uname = '"+sf.getText()+"'";
            ResultSet result = statement.executeQuery(insertquery);
            if(result.next()){
                uf.setText(result.getString(2));
                pf.setText(result.getString(3));
                id = result.getInt(1);
            }
        } catch (SQLException ex) {
            msg.setText("Problem to Show Data");
        }
        
    }
     
     public void update(){
        try {
            String insertquery = "UPDATE `user` set `uname`='"+uf.getText()+"',`pass`='"+pf.getText()+"' WHERE id = '"+id+"'";
            statement.executeUpdate(insertquery);
            msg.setText("Updated");
        } catch (SQLException ex) {
            msg.setText(ex.getMessage());
        }
     }
     
    /**
     *
     */
    public void delete(){
         try {
            String insertquery = "DELETE FROM `user` WHERE uname = '"+sf.getText()+"'";
            statement.executeUpdate(insertquery);
            msg.setText("Deleted");
        } catch (SQLException ex) {
            msg.setText(ex.getMessage());
        }
     }
}
