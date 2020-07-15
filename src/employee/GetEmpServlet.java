package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetEmpServlet
 */
@WebServlet({ "/GetEmpServlet", "/GetEmpListServlet" })
public class GetEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().append("Served at: ").append(request.getContextPath())
//							.append("박호일");
		//{"id":"user1", "first_name":"Hong", "age":30}
		PrintWriter out = response.getWriter();
		//hr.employee(employee_id, first_name, email, salary)
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		int cnt = 0;
		int rowCnt = list.size();
		out.write("[");
		for (Employee emp : list) {
//			{"id":"data1","first_name":"data2","email":"data3","salary":"data4"}
			out.write("{\"last_name\":\""+emp.getLastName()
					 +"\",\"email\":\""+emp.getEmail()
					 +"\",\"hire_date\":\""+emp.getHireDate()+"\",\"job_id\":\""
					 +emp.getJobId()+"\"}");
			if(++cnt != rowCnt)
				out.write(",");
		}
		out.write("]");
//		out.write(" [{\"id\":\"user1\", \"first_name\":\"Hong\", \"age\":30},");
//		out.write(" {\"id\":\"user2\", \"first_name\":\"Hwang\", \"age\":26}]");
//		response.getWriter().write(" [{\"id\":\"user1\", \"first_name\":\"Hong\", \"age\":30},");//' " " 를 문자로 인식 시키기 위해서 역 슬래쉬(\)를 넣는다.
//		response.getWriter().write(" {\"id\":\"user2\", \"first_name\":\"Hwang\", \"age\":26}]");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
