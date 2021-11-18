package co.nero.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.prj.comm.Command;
import co.nero.prj.member.service.MemberService;
import co.nero.prj.member.service.MemberVO;
import co.nero.prj.member.serviceImpl.MemberServiceImpl;

public class MemberIdCheck implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 아이디 중복체크(ajax 이용)
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("chkid"));
		boolean b = memberDao.memberIdCheck(vo); // true = 이미 존재하는거, false 사용가능
		String chk = "1";
		if(b) { // 존재하면 
			chk = "0"; 
		}
		//request.setAttribute("idCheck", chk);
		return "ajax:"+chk;
	}

}
