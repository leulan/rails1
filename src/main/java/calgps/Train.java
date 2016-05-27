package calgps;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.om2m.healthy.Mapper;




public class Train {
	//static Sql1 s=new Sql1();
	public void insertdata() throws FileNotFoundException {
    	
   Driver driver = null;
   Connection con = null;
   Statement stmt = null,stmt1=null,stmt2=null,stmt3=null;
   ResultSet rs=null,rs1 = null,rs2=null,rs3=null;
   String JDBC_DRIVER   = "com.mysql.jdbc.Driver";
    String JDBC_USER     = "root";
   String JDBC_PASSWORD = "lanlt";
   int cout=0;
   String jdbc_url = "jdbc:mysql://localhost:3306/om2mnscl";
   try {
       Class jdbcDriverClass = Class.forName(JDBC_DRIVER );
       driver = (Driver) jdbcDriverClass.newInstance();
       DriverManager.registerDriver( driver );
       con = DriverManager.getConnection(jdbc_url,JDBC_USER, JDBC_PASSWORD);;
       if (con!=null) {
    	   String q="select count(URI) from contentinstance where URI like 'nscl/applications/HEALTHY/containers/DATA/contentInstances%'";
    	   String q1="select count(id) from data1 ";
           String query = "Select value from contentinstance where URI like 'nscl/applications/HEALTHY/containers/DATA/contentInstances%'";
//    	   String q="select count(URI) from contentinstance ";
//    	   String q1="select count(id) from data1 ";
//           String query = "Select value from contentinstance";
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
           con.close();
       } catch (SQLException ex) {
           Logger.getLogger(ProcessData.class.getName()).log(Level.SEVERE, null, ex);
       }       
}

	public String Process(int song,float vantoc) throws IOException {

		float t1 = 0;
		float t2;
		String t = " ";					
			//System.out.println(song + ":" + vantoc);
			Obj n = new Obj();
			n.setNumber(song);
			n.setVol(vantoc);
			ReadFile r=new ReadFile();
			r.readFile("D:/20152/data/data12.txt");
			Gauss gass = new Gauss();
			Posteror p=new Posteror();
			
			Classify1 c = new Classify1();
			c.Calculate(n,p,gass);
			//System.out.println(n.getState());
		
	
	       return n.getState();
				}
	ArrayList n=new ArrayList();
	public String number() throws ClassNotFoundException {
		   Driver driver = null;
	       Connection con = null;
	       Statement stmt1=null;
	       ResultSet rs1 = null;
	       int cout=0;
	       String t1 = null;
	       String JDBC_DRIVER   = "com.mysql.jdbc.Driver";
	       String JDBC_USER     = "root";
	      String JDBC_PASSWORD = "lanlt";
	       //String t=org.eclipse.om2m.core.constants.
	       String jdbc_url = "jdbc:mysql://localhost:3306/om2mnscl";
	       try {
	           Class jdbcDriverClass = Class.forName( JDBC_DRIVER );
	           try {
				driver = (Driver) jdbcDriverClass.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           DriverManager.registerDriver( driver );
	           con = DriverManager.getConnection(jdbc_url, JDBC_USER,JDBC_PASSWORD);;
	           if (con!=null) {
	        	   String q="select count(userEmail),avg(velocity) from data1 where address like 'Minh Khai%' and time(timestamp)= current_time() and date(timestamp)=current_date";
                    String q1="select count(userEmail),avg(velocity),address from data1 where address like 'Minh Khai%' and time(timestamp)=time('2016.05.05 13:33:53')";
                    //for(int i=0;i<s.c.);)
                 //   String q2="select count(userEmail),avg(velocity) from data1 where address like"+"'"+s.c.get(0)+"%"+"'"+" and time(timestamp)=time('2016.05.05 13:33:53')";
	               stmt1=con.createStatement();
	               rs1 =stmt1.executeQuery(q1);
	             
	               int x=0;
	               float y=0;
	              
	               
	               while (rs1.next()) {
	               String number = rs1.getString(1);
	               x=Integer.valueOf(number);
	               String vol = rs1.getString(2);
	               y=Float.valueOf(vol);
	             //  String t=rs1.getString(3);
	               t1=number+","+vol+","+"Minh Khai";	           
	                
	               }
		
		
	             }
	       }catch(SQLException e){
	    	   e.printStackTrace();
	       }
	           return t1;
	}
	
	public ObjTraining doExcute(Train n) throws ClassNotFoundException, IOException{
		ObjTraining o1=new ObjTraining();
		System.out.println("do excute");
//		   String JDBC_DRIVER   = "com.mysql.jdbc.Driver";
//		    String JDBC_USER     = "root";
//		   String JDBC_PASSWORD = "lanlt";
	//	Train n=new Train();
		n.insertdata();
		String t=n.number();
		String[] value;
		value=t.split(",");
		int x;
		float y;
		//String t2;
		x=Integer.valueOf(value[0]);
		y=Float.valueOf(value[1]);
		//t2=value[2];
		n.Process(x, y);
		o1.setAddress(value[2]);
		o1.setNumber(x);
		o1.setVol(y);
		o1.setDensity(n.Process(x,y));
		return o1;
	}
//	public static void main(String[] ar ) throws ClassNotFoundException, IOException{
//		Train n=new Train();
//		String t="1";
//		ObjTraining o=new ObjTraining();
//	   o= n.doExcute(n);
//	   System.out.println(o.getAddress());
//	   System.out.println(o.getDensity());
//	   System.out.println(o.getNumber());
//	   System.out.println(o.getVol());
//	   String[] data=new String[4]; 
//		data[0]= o.getAddress()+ "";
//		data[1] =o.getNumber()+ "";
//		data[2] =o.getVol() + "";
//		data[3] =o.getDensity()+"";
//	   String x=Mapper.getAppFullData(data);
//	   System.out.println(x);
//	   
//	}


}
