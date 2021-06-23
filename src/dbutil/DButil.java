package dbutil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javabeen.Things;

public class DButil {
	//数据库URL和账号密码
		public static final String connectionURL="jdbc:mysql://localhost:3306/keshi?useUnicode=true&characterEncoding=GB18030&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
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
		public static String search(String  date)//查
		{
			try {
		
				connection=getConnection();
				sql=connection.prepareStatement("select * from info where Date like '"+date+"%' and  City=''");
	            System.out.println(sql);
				rSet=sql.executeQuery();
				//ArrayList<Things> list =new ArrayList<>();
				
				JSONArray jsonArray=new JSONArray();
				while(rSet.next())
				{
					//System.out.println(rSet.getString(3));
					JSONObject json=new JSONObject();
					json.put("name",rSet.getString(3) );
	                json.put("num", rSet.getString(5));
	                json.put("yisi",rSet.getString(6));
	                json.put("cure", rSet.getString(7));
	                json.put("dead", rSet.getString(8));
	                json.put("code", rSet.getString(9));
	                jsonArray.add(json);
				}
				return jsonArray.toString();
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
			
		}
		public static void main(String[] args) {
			search("2020-02-12");
		}

}
