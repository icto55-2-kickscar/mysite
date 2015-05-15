package com.sds.icto.mysite.servlet.action.board;

import com.sds.icto.web.Action;

public class ActionFactory {
	private static ActionFactory instance;
	static {
		instance = new ActionFactory(); 
	}
	
	private ActionFactory(){}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction( String a ) {
		Action action = null;
		
		if( "write".equals( a ) ) {
			action = new WriteAction();
		} else if( "insert".equals( a ) ) {
			action = new InsertAction();
		} else if( "view".equals( a ) ) {
			action = new ViewAction();
		} else if( "modify".equals( a ) ) {
			action = new ModifyAction();
		} else if( "update".equals( a ) ) {
			action = new UpdateAction();
		} else if( "delete".equals( a ) ) {
			action = new DeleteAction();
		}
		
		if( action == null ) {
			action = new IndexAction();
		}
		
		return action;
	}
}
