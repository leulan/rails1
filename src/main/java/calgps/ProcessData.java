package calgps;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessData {
	   public static String JDBC_DRIVER   = "com.mysql.jdbc.Driver";
	    public static String JDBC_USER     = "root";
	    public static String JDBC_PASSWORD = "lanlt";
	    public static void main(String[]  arg) throws FileNotFoundException {
	    	Sql1 s1=new Sql1();
       Driver driver = null;
       Connection con = null;
       Statement stmt = null,stmt1=null,stmt2=null,stmt3=null;
       ResultSet rs=null,rs1 = null,rs2=null,rs3=null;
       int cout=0;
       String jdbc_url = "jdbc:mysql://localhost:3306/om2mnscl";
       try {
           Class jdbcDriverClass = Class.forName(s1.JDBC_DRIVER );
           driver = (Driver) jdbcDriverClass.newInstance();
           DriverManager.registerDriver( driver );
           con = DriverManager.getConnection(jdbc_url, s1.JDBC_USER, s1.JDBC_PASSWORD);;
           if (con!=null) {
        	   String q="select count(URI) from contentinstance where URI like 'nscl/applications/HEALTHY/containers/DATA/contentInstances%'";
        	   String q1="select count(id) from data1 ";
               String query = "Select value from contentinstance where URI like 'nscl/applications/HEALTHY/containers/DATA/contentInstances%'";
               String q2="select count(id) from data1 where address like '%Minh%'";
               stmt = con.createStatement();
               rs=stmt.executeQuery(query);
               stmt1=con.createStatement();
               rs1 =stmt1.executeQuery(q);
               stmt2=con.createStatement();
               rs2=stmt2.executeQuery(q1);
               stmt3=con.createStatement();
               rs3=stmt3.executeQuery(q2);
               int x=0,y=0;
               
               while (rs1.next()) {
               String name = rs1.getString(1);
              // System.out.println(name);
               x=Integer.valueOf(name);
           
                
               }
               while (rs2.next()) {
                  String id=rs2.getString(1);
                   y=Integer.valueOf(id);
                    
                   }
               while (rs3.next()) {
                   String n=rs3.getString(1);
                   System.out.println(n);
                    }
               
               String tong="";
             while(rs.next()){
               String value=rs.getString("value");
               String[] t;
               String test="("+y+",";
               y++;
               t=value.split("\"");
              for(int i=3;i<t.length;i=i+4){
            	 //System.out.println(i+t[i]);
            	 if(i==19||i==23) {test+=t[i]+",";}
            	 else if(i==31) {test+=t[i]+")";
            	 cout++;
            	 } 
            	  else
            	  {    	 
                	  test+="'"+t[i]+"'"+",";
                	
            	  }                       
            	         	  
              }
         //  System.out.println(x);
            if(cout==x){     
            	tong+=test;
             }
            else{
            	tong+=test+",";
            }
             }
             System.out.println(tong);
             stmt1 = con.createStatement();
             String query1="insert into data1 values"+tong;
             System.out.println("the query is"+ query1);
             int count=stmt1.executeUpdate(query1);
             System.out.println(count+"record insert");
            }

       } catch (Exception ex) {
           System.out.println(ex.toString());
           return;
       }
         try {
               rs.close();
               stmt.close();
               stmt1.close();
               rs1.close();
               rs2.close();
               stmt2.close();
               stmt3.close();
               rs3.close();
               con.close();
           } catch (SQLException ex) {
               Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
           }       
//         ReadFileData r=new ReadFileData();
//	    	r.add();
//	    	for(int i=1;i<r.arrGps.size();i++){
//	    	System.out.println(r.arrGps.get(i).getLatidue());
//	    	}
//         public static void main(String[] ar ){
//        	 
//         }
	}

}

