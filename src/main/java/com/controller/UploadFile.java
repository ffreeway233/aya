package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFile extends HttpServlet {

	// 保存文件的目录
	private static String PATH_FOLDER = "/";
	// 存放临时文件的目录
	private static String TEMP_FOLDER = "/";

	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		// 初始化路径
		// 保存文件的目录
		PATH_FOLDER = servletCtx.getRealPath("/uploadstart");
		// 存放临时文件的目录,存放xxx.tmp文件的目录
		TEMP_FOLDER = servletCtx.getRealPath("/uploadshow");
	}

	public UploadFile() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("error");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("upload");
		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 如果没以下两行设置的话，上传大的文件会占用很多内存，
		// 设置暂时存放的存储室 ,这个存储室,可以和最终存储文件的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tmp
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			// 获取上传的文件
			String filenames = "";
			for (FileItem item : list) {
				// 获取表单的属性名字
				String name = item.getFieldName();

				// 如果获取的 表单信息是普通的 文本 信息
				if (item.isFormField()) {
					// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = item.getString();
					// request.setAttribute(name, value);
				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else {
					/**
					 * 以下三步，主要获取 上传文件的名字
					 */
					// 获取路径名
					String value = item.getName();
					// 索引到最后一个反斜杠
					int start = value.lastIndexOf("\\");
					// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
					String filename = value.substring(start + 1);
					String type = filename.substring(filename.lastIndexOf("."));
					String type1 = type.toLowerCase();
					filename = new Date().getTime() + type1;
					// request.setAttribute(name, filename);

					// 真正写到磁盘上
					// 它抛出的异常 用exception 捕捉

					item.write(new File(PATH_FOLDER, filename));// 第三方提供的
					// clear();
					// 手动写的
					// OutputStream out = new FileOutputStream(new File(
					// PATH_FOLDER, filename));
					//
					// InputStream in = item.getInputStream();
					//
					// int length = 0;
					// byte[] buf = new byte[1024];
					//
					// System.out.println("获取上传文件的总共的容量：" + item.getSize());
					//
					// // in.read(buf) 每次读到的数据存放在 buf 数组中
					// while ((length = in.read(buf)) != -1) {
					// // 在 buf 数组中 取出数据 写到 （输出流）磁盘上
					// out.write(buf, 0, length);
					//
					// }
					//
					// in.close();
					// out.close();
					response.getWriter().print(filename);
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
			response.getWriter().print("error");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("error");
		}
	}

	private void clear() {
		File file = new File(TEMP_FOLDER);
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			temp = new File(TEMP_FOLDER + "/" + tempList[i]);
			if (temp.isFile()) {
				temp.delete();
			}
		}
	}
}
