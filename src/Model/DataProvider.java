package Model;

import javafx.scene.chart.ScatterChart;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class DataProvider
{
    private String connectionStr ="jdbc:sqlserver://localhost;database=SavingAccount";
    private static DataProvider instance;
    public static DataProvider getInstance() {
        if(instance==null)
            instance=new DataProvider();
        return instance;
    }

    public static void setInstance(DataProvider instance) {
        DataProvider.instance = instance;
    }
    private  static  Boolean CheckDatabaseExists( String dbName)
    {
        try {
            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;integratedSecurity=true;");
            ResultSet rs = null;
            if(con != null){
                rs = con.getMetaData().getCatalogs();
                while(rs.next()){
                    String catalogs = rs.getString(1);

                    if(dbName.equals(catalogs)){
                        return true;
                    }
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    private  DataProvider()
    {
        if(!CheckDatabaseExists("SavingAccount" ))
        {
//            try {
////                URL resource = getClass().getResource("/Resources/Database.sql");
////                Path path = Paths.get(URI.create(resource.toString()));
////                String script = Files.readString(path, StandardCharsets.UTF_16LE);
////                Connection tempConn= DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;integratedSecurity=true;");
////                Statement statement = tempConn.createStatement();
////                statement.execute(script);
//                Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;integratedSecurity=true;");
//                ScriptRunner runner = new ScriptRunner(con, false, true);
//                URL resource = getClass().getResource("/Resources/Database.sql");
//                Path path = Paths.get(URI.create(resource.toString()));
//                BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream(), StandardCharsets.UTF_16LE));
//                runner.runScript(new BufferedReader(reader));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            try {
//                String line;
//                URL resource = getClass().getResource("/Resources/Database.sql");
//                Path path = Paths.get(URI.create(resource.toString()));
//                BufferedReader input = new BufferedReader(new InputStreamReader(resource.openStream(), StandardCharsets.UTF_16LE));
                URL resourceCMD = getClass().getResource("/Resources");
                Path pathCMD = Paths.get(URI.create(resourceCMD.toString()));
                File dir = new File(pathCMD.toString());
                Process p = Runtime.getRuntime().exec("sqlcmd -S 127.0.0.1 -E -i Database.sql", null, dir);
                p.waitFor();
//                while ((line = input.readLine()) != null) {
//                    System.out.println(line);
//                }
//                input.close();
            }
            catch (Exception err) {
                err.printStackTrace();
            }
        }
    }
    public ResultSet ExecuteQuery(String query, Object[] params)
    {
        ResultSet rs;
        try
        {
            Connection conn=SQLServerConnUtils_SQLJDBC.getSQLServerConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            if(params != null)
            {
                String[] listParas= query.split(" ");
                int i =0;
                for (String item: listParas) {
                    if(item.contains("?"))
                    {
                        ps.setObject(i+1, params[i]);
                        i++;
                    }
                }
            }
            rs=ps.executeQuery();
            //conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            rs=null;
        }
        return  rs;
    }
    public int ExecuteNonQuery(String query, Object[] params)
    {
        int rowAffected=0;
        try
        {
            Connection conn=SQLServerConnUtils_SQLJDBC.getSQLServerConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            if(params != null)
            {
                String[] listParas= query.split(" ");
                int i =0;
                for (String item: listParas) {
                    if(item.contains("?"))
                    {
                        if(params[i]!=null)
                        {
                            ps.setObject(i+1, params[i]);
                        }
                        else  ps.setNull(i+1, Types.FLOAT);
                        i++;
                    }
                }
            }
            rowAffected=ps.executeUpdate();
            //conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  rowAffected;
    }
    public Object ExecuteScalar (String query, Object[] params)
    {
        ResultSet rs;
        Object result=null;
        try
        {
            Connection conn=SQLServerConnUtils_SQLJDBC.getSQLServerConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            if(params != null)
            {
                String[] listParas= query.split(" ");
                int i =0;
                for (String item: listParas) {
                    if(item.contains("?"))
                    {
                        ps.setObject(i+1, params[i]);
                        i++;
                    }
                }
            }
            rs=ps.executeQuery();
            while (rs.next())
            {
                result=rs.getObject(1);
            }
            //conn.close();
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return  -1;
        }
    }
}
