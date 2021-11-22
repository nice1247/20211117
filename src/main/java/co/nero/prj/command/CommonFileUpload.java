package co.nero.prj.command;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import co.nero.prj.comm.Command;
import co.nero.prj.notice.service.NoticeService;
import co.nero.prj.notice.service.NoticeVO;
import co.nero.prj.notice.serviceImpl.NoticeServiceImpl;

public class CommonFileUpload implements Command {
	// apache common-fileupload 라이브러리 사용
	private HashMap<String, String> map = new HashMap<String, String>();
	private String fileSave = "c:\\FileTest"; //개발시 업로드 파일 저장공간
//	private String fileSave = "fildUpload"; // 운영서버에 실제 동작환경을 꾸밀때 !!!!!!!!!!
//	private int fileSize = 1024 * 1024 * 100;;

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {

		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(); // 파일저장소 관련정보
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory); // request 객체 parse
//		File attechDir = new File(fileSave); // 파일객체 생성
//		fileItemFactory.setRepository(attechDir); // 저장공간
//		fileItemFactory.setSizeThreshold(fileSize); // 파일 최대 사이즈

		String fileName = null;
		String pfileName = null;

		// 공지사항 저장
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		HttpSession session = request.getSession();
		vo.setId((String) session.getAttribute("id")); // 세션에 저장된 id값
		vo.setName((String) session.getAttribute("name")); // 세션에 저장된 이름

		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			// FileItem 객체는 폼에서 넘어온 multipart 객체형식을 다루는 객체
			for (FileItem item : items) {
				if (item.isFormField()) {
					map.put(item.getFieldName(), item.getString("utf-8")); // 필드명과 데이터를 저장
				} else if (!item.isFormField() && item.getSize() > 0) {
					// 폼에서 넘어온 타입이 일반필드가 아니고 file 타입이고 사이즈가 0 보다큼면
					int index = item.getName().lastIndexOf(File.separator); // 마지막 \ 의 위치
					fileName = item.getName().substring(index + 1); // 실 파일명만 추출
					String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					UUID uuid = UUID.randomUUID();
					String newFileName = uuid.toString() + extension; // UUID를 통한 새로운 파일명으로 전환
					pfileName = fileSave + File.separator + newFileName;
//					map.put("fileName", fileName);
//					map.put("pfileName", pfileName);
					File uploadFile = new File(pfileName); // c:\\FileTest파일명
					item.write(uploadFile); // 파일 업로드가 읽어남
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 이곳에 DB처리할 값을 넣어주는곳
		vo.setFileName(fileName); // 원본파일 저장
		vo.setPfileName(pfileName); // 물리파일 저장
		vo.setWdate(Date.valueOf(map.get("wdate"))); // form에서 넘어오는 wdate(String)을 date값으로 바꿔줌
		vo.setTitle(map.get("title"));
		vo.setSubject(map.get("subject"));
		noticeDao.noticeInsert(vo);

		return "noticeList.do";
	}

}
