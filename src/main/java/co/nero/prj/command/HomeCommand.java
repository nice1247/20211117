package co.nero.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.prj.comm.Command;

public class HomeCommand implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 단순히 처음들어오는 홈페이지 호출
		return "home/home";
	}

}
