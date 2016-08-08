package kr.ac.sungkyul.mysite.web.board;

import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("".equals(actionName)){
			
		}else{
			action = new BoardListAction();
		}
		return action;
	}

}
