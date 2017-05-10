import java.util.*;

public class DbConnection
{
   private static final int MAX_CONNS = 100;
   private static int totalConnections = 0;

   private static Set<DbConnection> availableConnections = new HashSet<DbConnection>();

   private DbConnection()
   {
     // ...
     totalConnections++;
   }

   public static DbConnection getDbConnection() throws Exception
   {
     if(totalConnections < MAX_CONNS)
     {
       return new DbConnection();
     }

     else if(availableConnections.size() > 0)
     {
         DbConnection dbc = availableConnections.iterator().next();
         availableConnections.remove(dbc);
         return dbc;
     }
     else {

         throw new NoDbConnections();
     }
   }

   public static void returnDbConnection(DbConnection dbc)
   {
     availableConnections.add(dbc);
     //...
   }

}
class NoDbConnections extends Exception{
   public NoDbConnections(){
      
   }

    /**
     * 載入預設設定檔 config.properties
     */
    private final void loadConfig() throws Exception 
    {
		this.prop = new Properties();
		try {
			prop.load(new InputStreamReader(
			    new FileInputStream("config.properties"), "UTF-8"));
			    
		} catch (IOException io) {
			io.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    }
    private Properties prop;
   
}   

