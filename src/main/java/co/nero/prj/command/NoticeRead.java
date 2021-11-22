package co.nero.prj.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.prj.comm.Command;
import co.nero.prj.notice.service.NoticeService;
import co.nero.prj.notice.service.NoticeVO;
import co.nero.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeRead implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNo(Integer.valueOf(request.getParameter("no")));
		request.setAttribute("notice", noticeDao.noticeSelect(vo));
		
		return "notice/noticeRead";
	}

}
