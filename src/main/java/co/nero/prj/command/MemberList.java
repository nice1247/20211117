package co.nero.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.prj.comm.Command;
import co.nero.prj.member.service.MemberService;
import co.nero.prj.member.serviceImpl.MemberServiceImpl;

public class MemberList implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		MemberService memberDao = new MemberServiceImpl();
		request.setAttribute("members", memberDao.memberSelectList());
		return "member/memberList";
	}

}
