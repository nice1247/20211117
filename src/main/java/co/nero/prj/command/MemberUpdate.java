package co.nero.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.prj.comm.Command;
import co.nero.prj.member.service.MemberService;
import co.nero.prj.member.service.MemberVO;
import co.nero.prj.member.serviceImpl.MemberServiceImpl;

public class MemberUpdate implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		  // 회원정보 수정
	      MemberService memberDao = new MemberServiceImpl();
	      MemberVO vo = new MemberVO();
	      vo.setId(request.getParameter("id")); // 세션에서 불러오면 object 타입이라 string으로 boxing 해줘야한다.
	      
	      request.setAttribute("member", memberDao.memberSelect(vo));
	      
	      return "member/memberUpdate";

	}

}
