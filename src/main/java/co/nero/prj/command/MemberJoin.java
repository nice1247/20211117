package co.nero.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.prj.comm.Command;
import co.nero.prj.member.service.MemberService;
import co.nero.prj.member.service.MemberVO;
import co.nero.prj.member.serviceImpl.MemberServiceImpl;

public class MemberJoin implements Command {
	private String message;
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		//  회원가입 처리
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		vo.setName(request.getParameter("name"));
		vo.setTel(request.getParameter("tel"));
		vo.setAddress(request.getParameter("address"));
		vo.setAuthor("user");
		
		int n = memberDao.memberInsert(vo);
		if(n != 0 ) {
			message = "회원가입이 성공적으로 되었습니다.";
		} else {
			message = "회원가입이 실패하였습니다. <br> 관리자에게 문의하세요.";
		}
		
		request.setAttribute("message", message);
		return "member/memberJoin";
	}

}
