package com.school.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.dto.StudentInfo;
import com.school.service.StudentInfoService;

/**
 * 
 * 项目名称：SchoolQuery 类名称：StudentInfoController 类描述： 创建人：c-kx 创建时间：2015年10月27日
 * 下午1:48:27
 * 
 * @version
 */
public class StudentInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentInfoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			String operate = request.getParameter("operate");
			if (operate.equals("groupSearch")) {
				groupSearch(request, response);
			} else if (operate.equals("create")) {
				create(request, response);
			} else if (operate.equals("save")) {
				save(request, response);
			} else if (operate.equals("list")) {
				list(request, response);
			} else if (operate.equals("clearRecycleBin")) {
				clearRecycleBin(request, response);
			} else if (operate.equals("recycleBin")) {
				recycleBin(request, response);
			} else if (operate.equals("hide")) {
				hide(request, response);
			} else if (operate.equals("recover")) {
				recover(request, response);
			} else if (operate.equals("edit")) {
				edit(request, response);
			} else if (operate.equals("update")) {
				update(request, response);
			} else if (operate.equals("show")) {
				show(request, response);
			} else if (operate.equals("delete")) {
				delete(request, response);
			} else if (operate.equals("search")) {
				search(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void groupSearch(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("studentId");
		String studentNum = request.getParameter("studentNum");
		StudentInfoService studentInfoService = null;
		StudentInfo studentInfoInstance = null;
		try {
			studentInfoInstance = new StudentInfo();
			studentInfoService = new StudentInfoService();
			studentInfoInstance = studentInfoService.groupSearch(id, studentNum);
			if (studentInfoInstance != null) {
				request.setAttribute("studentInfo", studentInfoInstance);
				request.getRequestDispatcher("studentInfo/search.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "没有搜索到！");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearRecycleBin(HttpServletRequest request, HttpServletResponse response) {
		StudentInfoService studentInfoService = null;
		try {
			studentInfoService = new StudentInfoService();
			if (studentInfoService.deleteByStatus()) {
				request.setAttribute("msg", "已彻底删除！");
				recycleBin(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				recycleBin(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void recycleBin(HttpServletRequest request, HttpServletResponse response) {
		StudentInfoService studentInfoService = null;
		List<StudentInfo> studentInfoList = null;
		try {
			studentInfoList = new ArrayList<StudentInfo>();
			studentInfoService = new StudentInfoService();
			studentInfoList = studentInfoService.recycleBinList();
			request.setAttribute("studentInfoList", studentInfoList);
			request.getRequestDispatcher("studentInfo/recycleBin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void recover(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("studentId");
		StudentInfoService studentInfoService = null;
		StudentInfo studentInfoInstance = null;
		try {
			studentInfoInstance = new StudentInfo();
			studentInfoService = new StudentInfoService();
			studentInfoInstance = studentInfoService.get(id);
			studentInfoInstance.setStatus("show");
			if (studentInfoService.status(studentInfoInstance)) {
				request.setAttribute("msg", "还原成功！");
				recycleBin(request, response);
			} else {
				request.setAttribute("msg", "还原失败");
				recycleBin(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void hide(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("studentId");
		StudentInfoService studentInfoService = null;
		StudentInfo studentInfoInstance = null;
		try {
			studentInfoInstance = new StudentInfo();
			studentInfoService = new StudentInfoService();
			studentInfoInstance = studentInfoService.get(id);
			studentInfoInstance.setStatus("hide");
			if (studentInfoService.status(studentInfoInstance)) {
				request.setAttribute("msg", "删除成功！");
				list(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				list(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void show(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("studentId");
		StudentInfoService studentInfoService = null;
		StudentInfo studentInfoInstance = null;
		try {
			studentInfoInstance = new StudentInfo();
			studentInfoService = new StudentInfoService();
			studentInfoInstance = studentInfoService.get(id);
			request.setAttribute("studentInfo", studentInfoInstance);
			request.getRequestDispatcher("studentInfo/show.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		StudentInfoService studentInfoService = null;
		List<StudentInfo> studentInfoListByName = null;
		try {
			studentInfoListByName = new ArrayList<StudentInfo>();
			studentInfoService = new StudentInfoService();
			String account = request.getParameter("account");
			if (account == null || account.equals("")) {
				request.setAttribute("searchMsg", "请输入姓名关键字");
				list(request, response);
			} else {
				studentInfoListByName = studentInfoService.findByName(account);
				if (studentInfoListByName != null) {
					request.setAttribute("searchMsg", "搜索到得数据为：");
					request.setAttribute("studentInfoListByName", studentInfoListByName);
					request.getRequestDispatcher("studentInfo/searchResult.jsp").forward(request, response);
				} else {
					request.setAttribute("searchMsg", "没有搜索到数据！");
					list(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("studentId");
		StudentInfoService studentInfoService = null;
		try {
			studentInfoService = new StudentInfoService();
			if (studentInfoService.delete(id)) {
				request.setAttribute("msg", "已彻底删除！");
				recycleBin(request, response);
			} else {
				request.setAttribute("msg", "删除失败");
				recycleBin(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("studentId");
		StudentInfoService studentInfoService = null;
		StudentInfo studentInfoInstance = null;
		try {
			studentInfoInstance = new StudentInfo();
			studentInfoService = new StudentInfoService();
			studentInfoInstance = studentInfoService.get(id);
			request.setAttribute("studentInfo", studentInfoInstance);
			request.getRequestDispatcher("studentInfo/update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		StudentInfoService studentInfoService = null;
		StudentInfo studentInfoInstance = null;
		StringBuffer sbuffer = null;
		try {
			studentInfoService = new StudentInfoService();
			sbuffer = new StringBuffer();
			studentInfoInstance = new StudentInfo();
			String id = request.getParameter("studentId");
			studentInfoInstance = studentInfoService.get(id);
			String studentName = request.getParameter("studentName");
			if (studentName == null || studentName.equals("")) {
				sbuffer.append("您的姓名不能为空！<br/>");
			} else {
				studentInfoInstance.setStudentName(studentName);
			}
			String sex = request.getParameter("sex");
			if (sex == null || sex.equals("")) {
				sbuffer.append("性别不能为空！<br/>");
			} else {
				studentInfoInstance.setSex(sex);
			}
			String studentNum = request.getParameter("studentNum");
			if (studentNum == null || studentNum.equals("")) {
				sbuffer.append("学号不能为空！<br/>");
			} else {
				studentInfoInstance.setStudentNum(studentNum);
			}
			String major = request.getParameter("major");
			if (major == null || major.equals("")) {
				sbuffer.append("专业不能为空！<br/>");
			} else {
				studentInfoInstance.setMajor(major);
			}
			String classes = request.getParameter("classes");
			if (classes == null || classes.equals("")) {
				sbuffer.append("班级不能为空！<br/>");
			} else {
				studentInfoInstance.setClasses(classes);
			}
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.setAttribute("studentInfo", studentInfoInstance);
				request.getRequestDispatcher("studentInfo/update.jsp").forward(request, response);
			} else {
				if (studentInfoService.update(studentInfoInstance)) {
					request.setAttribute("msg", "修改成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "修改失败");
					request.setAttribute("studentInfo", studentInfoInstance);
					request.getRequestDispatcher("studentInfo/update.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		StudentInfoService studentInfoService = null;
		List<StudentInfo> studentInfoList = null;
		try {
			studentInfoList = new ArrayList<StudentInfo>();
			studentInfoService = new StudentInfoService();
			studentInfoList = studentInfoService.list();
			request.setAttribute("studentInfoList", studentInfoList);
			request.getRequestDispatcher("studentInfo/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		StudentInfoService studentInfoService = null;
		StringBuffer sbuffer = null;
		StudentInfo studentInfoInstance = null;
		try {
			studentInfoService = new StudentInfoService();
			sbuffer = new StringBuffer();
			studentInfoInstance = new StudentInfo();
			String id = request.getParameter("studentId");
			if (id == null || id.equals("")) {
				sbuffer.append("您的身份证号不能为空！<br/>");
			} else {
				studentInfoInstance.setId(id);
			}
			String studentName = request.getParameter("studentName");
			if (studentName == null || studentName.equals("")) {
				sbuffer.append("您的姓名不能为空！<br/>");
			} else {
				studentInfoInstance.setStudentName(studentName);
			}
			String sex = request.getParameter("sex");
			if (sex == null || sex.equals("")) {
				sbuffer.append("性别不能为空！<br/>");
			} else {
				studentInfoInstance.setSex(sex);
			}
			String studentNum = request.getParameter("studentNum");
			if (studentNum == null || studentNum.equals("")) {
				sbuffer.append("学号不能为空！<br/>");
			} else {
				studentInfoInstance.setStudentNum(studentNum);
			}
			String major = request.getParameter("major");
			if (major == null || major.equals("")) {
				sbuffer.append("专业不能为空！<br/>");
			} else {
				studentInfoInstance.setMajor(major);
			}
			String classes = request.getParameter("classes");
			if (classes == null || classes.equals("")) {
				sbuffer.append("班级不能为空！<br/>");
			} else {
				studentInfoInstance.setClasses(classes);
			}
			String status = "show";
			studentInfoInstance.setStatus(status);
			if (sbuffer.length() != 0) {
				request.setAttribute("msg", sbuffer.toString());
				request.getRequestDispatcher("studentInfo/add.jsp").forward(request, response);
			} else {
				if (studentInfoService.save(studentInfoInstance)) {
					request.setAttribute("msg", "添加成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "添加管理员失败");
					request.getRequestDispatcher("studentInfo/add.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/studentInfo/add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
