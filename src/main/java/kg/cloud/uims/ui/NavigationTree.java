package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;

import com.vaadin.ui.Tree;

public class NavigationTree extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MyVaadinApplication app;
	public NavigationTree(MyVaadinApplication app){
		this.app=app;
	}
	
	public void buildTree(){
		
	}
}
