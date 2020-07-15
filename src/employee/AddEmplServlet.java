package employee;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddEmplServlet
 */
@WebServlet("/AddEmplServlet")
public class AddEmplServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmplServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: " + " Get");
		String lastName = request.getParameter("last_name");//last_name이라는 파라미터의 value값을 받아서 반환해줌
		String email = request.getParameter("email");
		String hireDate = request.getParameter("hire_date");
		String jobId = request.getParameter("job_id");
		
		Employee emp = new Employee(email, hireDate, lastName, jobId);
		EmpDAO dao = new EmpDAO();
		dao.insertEmp(emp);
		
		request.getRequestDispatcher("employeeList.html").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
//		response.getWriter().append("Served at: " + " Post");
	}

}
