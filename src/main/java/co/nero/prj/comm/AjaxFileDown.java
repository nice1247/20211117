package co.nero.prj.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AjaxFileDown")
public class AjaxFileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxFileDown() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String fileName = request.getParameter("fileName");
		String pfileName = request.getParameter("pfileName");
		InputStream in = null;
		OutputStream out = null;
		File file = null;
		try {
			file = new File(pfileName); // 물리위치에서 파일을 선택하고
			in = new FileInputStream(file); // 읽어들임
			fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1"); // 한글 파일명 처리
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

			out = response.getOutputStream(); // response 객체로 초기화
			byte b[] = new byte[(int) file.length()];
			int leng = 0;
			while ((leng = in.read(b)) > 0) { // 실제 다운로드 함
				out.write(b, 0, leng);
			}
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
