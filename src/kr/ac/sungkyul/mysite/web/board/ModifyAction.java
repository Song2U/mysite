package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
			WebUtil.redirect("/mysite/board", request, response);
			return;
		}
		BoardVo authUser = (BoardVo) session.getAttribute("authUser");
		if (authUser == null) {
			WebUtil.redirect("/mysite/board", request, response);
			return;
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long userNo = authUser.getNo();

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserNo(userNo);
		
		BoardDao dao = new BoardDao();
		dao.update(vo);

		// 세션 정보 수정
		WebUtil.redirect("/mysite/board?a=view&no=", request, response);
	}
}