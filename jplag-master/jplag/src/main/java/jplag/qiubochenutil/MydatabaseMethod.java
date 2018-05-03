package jplag.qiubochenutil;


import jplag.java17.grammar.Java7Parser;

import java.io.FileNotFoundException;
import java.sql.*;


public class MydatabaseMethod {//qiubochen change
    PreparedStatement statement;
    Connection connection;

    String INSERTSQL="INSERT INTO dmcc.compare(student1,student2,result) VALUES (?,?,?)";
    String CREATE_DATABASE="Create Database If Not Exists dmcc Character Set UTF8";
    String CREATE_TABLE="CREATE TABLE If NOT EXISTS `dmcc`.`compare` (`id` INT NOT NULL AUTO_INCREMENT,`student1` VARCHAR(45) NULL,`student2` VARCHAR(45) NULL,`result` DECIMAL(45,10) NULL,PRIMARY KEY (`id`));";
    public MydatabaseMethod(){

    }
    public MydatabaseMethod(String[]args){

        if(judgeArgs(args)){
            MydatabaseBean mydatabaseBean=new MydatabaseBean();
            mydatabaseBean.setIp(args[6]);
            mydatabaseBean.setPort(args[7]);
            mydatabaseBean.setUsername(args[8]);
            mydatabaseBean.setPassword(args[9]);
            XMLUtil xmlUtil=new XMLUtil();
            xmlUtil.insertToXML(mydatabaseBean);
        }

    }
    public boolean judgeArgs(String[]args){
        if(args.length==10){
            return true;
        }
        return false;
    }
    public String[] changeArgs(String []args){
        String[]argstmp=new String[6];
        for (int i=0;i<6;i++){
            argstmp[i]=args[i];
        }
        return argstmp;
    }
    public void connect(MydatabaseBean mydatabaseBean)  {

        try {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

            connection= DriverManager.getConnection("jdbc:mysql://"+mydatabaseBean.getIp()+mydatabaseBean.getPort()+"?characterEncoding=utf8",mydatabaseBean.getUsername(),mydatabaseBean.getPassword());
            System.out.println("database link success");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("sorry,database link failure");
        }
    }
    public void insert(String s1,String s2,float percent){
        MydatabaseBean mydatabaseBean=new MydatabaseBean();
        XMLUtil xmlUtil=new XMLUtil();
        try {
            mydatabaseBean=xmlUtil.getFromXML();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        connect(mydatabaseBean);
        try {
            statement=connection.prepareStatement(CREATE_DATABASE);
            statement.executeUpdate();
            statement=connection.prepareStatement(CREATE_TABLE);
            statement.executeUpdate();
            statement=connection.prepareStatement(INSERTSQL);
            statement.setString(1,s1);
            statement.setString(2,s2);
            statement.setFloat(3,percent);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("insert error");
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
