package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UserVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		Long no = Long.parseLong(request.getParameter("no"));
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		BoardVo vo = dao.get(no);
		request.setAttribute("board", vo);
		request.setAttribute("no", no);
		request.setAttribute("authUser", authUser);
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}
}