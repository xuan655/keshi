package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DButilhot {
	public static final String connectionURL="jdbc:mysql://localhost:3306/text3?useUnicode=true&characterEncoding=GB18030&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
	public static final String username="root";
	public static final String password="123456";
	static Connection connection;
	static ResultSet rSet;
	static PreparedStatement sql;
	//数据库连接
	public static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(connectionURL, username, password);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return null;
	}                                                   
	public static String getall()//查
	{
		try {
	
			connection=getConnection();
			sql=connection.prepareStatement("select * from lunwen");
            System.out.println(sql);
			rSet=sql.executeQuery();
			//ArrayList<Things> list =new ArrayList<>();
			
			JSONArray jsonArray=new JSONArray();
			while(rSet.next())
			{
				//System.out.println(rSet.getString(3));
				JSONObject json=new JSONObject();
				json.put("title",rSet.getString(2) );
                json.put("abstract", rSet.getString(3));
                json.put("keywords",rSet.getString(4));
                json.put("link", rSet.getString(5));
                jsonArray.add(json);
			}
			return jsonArray.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public static void main(String[] args) {
		//String string=getall();
		//System.out.println(string);
	}

}
