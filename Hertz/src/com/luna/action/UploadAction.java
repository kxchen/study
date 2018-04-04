package com.luna.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadAction extends HttpServlet {

	private static final long serialVersionUID = -3785866485710123703L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private static final String ALLOW_EXT = "jpg,jpeg,png,bmp,gif";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// WEB形式的表现地址，示例：http://127.0.0.1:8080/uploadtest/

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		/*
		 * request.getScheme() 返回当前链接使用的协议； request.getServerName()
		 * 就是获取你的网站的域名，如果是在本地的话就是localhost
		 * request.getServerPort()返回的是WEB容器使用的HTTP端口号
		 */
		System.out.println("web地址：" + basePath);

		// 获得本地地址，示例如：c://tomcat/webapp/
		String temp = getServletContext().getRealPath("/") + "upload/temp"; // 临时目录
		String loadpath = getServletContext().getRealPath("/") + "upload"; // 上传文件存放目录

		System.out.println("本地地址：" + loadpath);
		// response.setContentType(MIME)的作用是使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		try {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Set factory constraints设置最多只允许在内存中存储的数据,单位:字节
			factory.setSizeThreshold(4096);
			// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录(缓存)
			factory.setRepository(new File(temp));
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint设置允许用户上传文件大小,单位:字节，这里设为1M
			upload.setSizeMax(1 * 1024 * 1024);
			// Parse the request
			List /* FileItem */ fileItems = upload.parseRequest(request);
			// 开始读取上传信息
			// 依次处理每个上传的文件
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					System.out.print("form field:");
					System.out.print(item.getFieldName() + "  ");
					System.out.print(item.getString());
				}
				// 忽略其他不是文件域的所有表单信息

				if (!item.isFormField()) {
					// 上传文件名
					String name = item.getName();
					System.out.println("上传文件名:" + name);
					long size = item.getSize();
					System.out.println("上传文件大小:" + size);
					if ((name == null || name.equals("")) && size == 0)
						continue;
					name = name.substring(name.lastIndexOf("\\") + 1);// 从全路径中提取文件名
					String extName = name.substring(name.lastIndexOf(".") + 1);
					System.out.println("上传文件名:" + name);
					System.out.println("上传文件扩展名:" + extName);
					if (ALLOW_EXT.indexOf(extName) == -1) {
						request.setAttribute("error", "只允许上传jpg,png,bmp,gif图片");
						request.getRequestDispatcher("../mobilePhone/upload.jsp").forward(request, response);
					}
					// 处理中文编码问题，前台是UTF－8，所以这里需要对应上，如果前台是gb2312的话，这里也应该是gb2312
					name = new String(name.getBytes(), "utf-8");
					try {
						// 保存上传的文件到指定的目录
						// 在下文中上传文件至数据库时，将对这里改写
						Date d = new Date();
						name = d.getTime() + "." + extName;
						System.out.println("上传文件改名为:" + name);
						File fNew = new File(loadpath, name);
						item.write(fNew);
						request.setAttribute("imgPath", name);
						request.getRequestDispatcher("/upload.jsp").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
						out.println(e);
					}
				}
			}
		} catch (Exception e) {
			request.setAttribute("error", "上传操作失败，请检查文件大小是否在限制1M范围内?");
			request.getRequestDispatcher("/upload.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
}
