package Api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 


public class Create extends HttpServlet {

    private PrintWriter out;

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Cache-Control","no-cache");
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        String idc=request.getParameter("id");
        String RGB=request.getParameter("RGB");
        String R=request.getParameter("R");
        String G=request.getParameter("G");
        String B=request.getParameter("B");
        String Pred=request.getParameter("Pred");
            StringBuilder json = new StringBuilder();
            json.append("[");      
      String result;      
      String Query="INSERT INTO COLORS(columncolors) values('{\"id\" :\""+idc+"\",\"RGB\" :\""+RGB+"\",\"R\" :\""+R+"\",\"G\" :\""+G+"\",\"B\" :\""+B+"\",\"PREDICTION\" :\""+Pred+"\"}');";      
    try{
        String Driver ="com.mysql.cj.jdbc.Driver";
        String Url = "jdbc:mysql://localhost/dbcolors";
    Class.forName(Driver);
    Connection db = DriverManager.getConnection(Url, "root", "1234");
   PreparedStatement s = db.prepareStatement(Query);   
    int i=s.executeUpdate();
        if(i>0){
            result="Se logro";
            out.write(result);
        } else{
            result="Se murio";
            out.write(result);
        }  
       }
    catch(ClassNotFoundException | SQLException e){
    e.printStackTrace();
    }
    out.write(Query);
    
    
    }
}
