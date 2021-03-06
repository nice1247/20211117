package co.nero.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.nero.prj.comm.Command;
import co.nero.prj.member.service.MemberService;
import co.nero.prj.member.service.MemberVO;
import co.nero.prj.member.serviceImpl.MemberServiceImpl;

public class MemberInfo implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보 보기
		HttpSession session = request.getSession();
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId((String) session.getAttribute("id"));
		
		request.setAttribute("member", memberDao.memberSelect(vo));
		return "member/memberInfo";
	}

}
