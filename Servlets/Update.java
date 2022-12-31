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
 


public class Update extends HttpServlet {

    private PrintWriter out;

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out = response.getWriter();
        response.setHeader("Cache-Control","no-cache");
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        String idu=request.getParameter("id");
        String RGBu=request.getParameter("RGB");
        String Ru=request.getParameter("R");
        String Gu=request.getParameter("G");
        String Bu=request.getParameter("B");
        String Predu=request.getParameter("Pred");
            StringBuilder json = new StringBuilder();
            json.append("[");      
      String result;      
      String Query="UPDATE COLORS SET columncolors ='{\"id\" :\""+idu+"\",\"RGB\" :\""+RGBu+"\",\"R\" :\""+Ru+"\",\"G\" :\""+Gu+"\",\"B\" :\""+Bu+"\",\"PREDICTION\" :\""+Predu+"\"}' WHERE JSON_EXTRACT(columncolors,'$.id')="+"'"+idu+"';";      
    try{
        String Driver ="com.mysql.cj.jdbc.Driver";
        String Url = "jdbc:mysql://localhost/dbcolors";
    Class.forName(Driver);
    Connection db = DriverManager.getConnection(Url, "root", "1234");
   PreparedStatement s = db.prepareStatement(Query);   
    int i=s.executeUpdate();
        if(i>0){
            result="Se inserto la query pa";
            out.write(result);
        } else{
            result="Se murio la query pa";
            out.write(result);
        }  
       }
    catch(ClassNotFoundException | SQLException e){
    e.printStackTrace();
    }
    
    
    
    }
}
