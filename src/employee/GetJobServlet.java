package employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/GetJobServlet")
public class GetJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetJobServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//JobId(String), JobTitle(String) : Map collection사용 - EmpDAO에서 하기
		EmpDAO dao = new EmpDAO();
		Map<String, String> map = dao.getJobCode();
		//json -> [{"job_id":"job_title"},{"job_id":"job_title"},{"job_id":"job_title"}]
		JSONObject obj = new JSONObject();
		JSONArray ary = new JSONArray();
		Set<String> set = map.keySet();
		for(String str : set) {
			obj.put("job_id", str);
			obj.put("job_title", map.get(str));//키값(str)을 넣어 주면 map에 있는 value부분을 리턴 해줌
			ary.add(obj);			
		}
		
		PrintWriter out = response.getWriter();
		out.write(ary.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
