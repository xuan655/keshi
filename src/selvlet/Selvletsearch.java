package selvlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import dbutil.DButil;
import javabeen.Things;

/**
 * Servlet implementation class Selvletsearch
 */
@WebServlet("/Selvletsearch")
public class Selvletsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 request.setCharacterEncoding("utf-8");
	     response.setContentType("text/html;charset=UTF-8");
	       // String date=request.getParameter("date");
	        response.getWriter().write(DButil.search(request.getParameter("date")));
	}

}
