package co.nero.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.prj.command.HomeCommand;
import co.nero.prj.command.Logout;
import co.nero.prj.command.MemberDelete;
import co.nero.prj.command.MemberEditSave;
import co.nero.prj.command.MemberIdCheck;
import co.nero.prj.command.MemberInfo;
import co.nero.prj.command.MemberJoin;
import co.nero.prj.command.MemberJoinForm;
import co.nero.prj.command.MemberList;
import co.nero.prj.command.MemberLogin;
import co.nero.prj.command.MemberLoginForm;
import co.nero.prj.command.MemberUpdate;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// Command들을 요청에 따라 처리할 수 있도록 메모리에 구성한다.
		map.put("/home.do", new HomeCommand()); // 홈 페이지
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); // 로그인 처리
		map.put("/logout.do", new Logout()); // 로그아웃 처리
		map.put("/memberList.do", new MemberList()); // 멤버리스트
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 회원가입 폼 호출
		map.put("/ajaxIdCheck.do", new MemberIdCheck()); // 아이디 중복체크
		map.put("/memberJoin.do", new MemberJoin()); // 회원가입처리
		map.put("/memberInfo.do", new MemberInfo()); // 회원정보
		map.put("/memberUpdate.do", new MemberUpdate());
		map.put("/memberEditSave.do", new MemberEditSave());
		map.put("/memberDelete.do", new MemberDelete());
	}
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청을 분석 실행할 명령을 찾아 수행하고 결과를 돌려주는 메소드
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());

		Command command = map.get(page);
		String viewPage = command.run(request, response);

		if (!viewPage.endsWith(".do")) {
			if (viewPage.startsWith("ajax:")) { // ajax 처리
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			} else {
				viewPage = "WEB-INF/views/" + viewPage + ".jsp";
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
