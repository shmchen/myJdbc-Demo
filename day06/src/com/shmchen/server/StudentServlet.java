package com.shmchen.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shmchen.dao.IStudentDao;
import com.shmchen.dao.impl.IStudentDaoImpl;
import com.shmchen.domain.Student;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IStudentDao dao = new IStudentDaoImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		// 获取操作指令
		String cmd = req.getParameter("cmd");		
		if ("save".equals(cmd)) {
			saveOrUpdate(req, resp);
		}else if ("delete".equals(cmd)) {
			delete(req, resp);
		}else if ("edit".equals(cmd)) {
			this.edit(req, resp);
		}else {
			list(req, resp);
		}
	}
	
	private void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String stuID = req.getParameter("id");
		
		if (stuID != null) { 
			// 更新操作
			int id = Integer.parseInt(stuID);
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age"));
			Student student = new Student(id, name, age);
			dao.edit(student);
		}else {
			// 新增操作
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age"));
			Student student = new Student(name, age);
			dao.save(student);
		}
		resp.sendRedirect(getServletContext().getContextPath() + "/student");
	}
	
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Student student = dao.get(Integer.parseInt(id));
		req.setAttribute("student", student);
		req.getRequestDispatcher("/views/edit.jsp").forward(req, resp);
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		dao.delete(id);		
		resp.sendRedirect(getServletContext().getContextPath() + "/student");
	}
	
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> all = dao.getAll();
		req.setAttribute("list", all);
		req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
	}
}
