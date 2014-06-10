package com.bd.theworld.cup;

public enum SubMenu {

	SUB_1("赛程"), SUB_2("直播"), SUB_3("新闻"), SUB_4("我的");

	private String titleName;
	
	SubMenu(String titleName) {
		this.titleName = titleName;
	}
	
	public static int getCount() {
		SubMenu[] menus = values();
		return menus.length;
	}

	public static SubMenu menuOfpos(int position) {
		SubMenu[] menus = values();
		
		for(SubMenu menu : menus) {
			if(position == menu.ordinal()) {
				return menu;
			}
		}
		
		return SUB_1;
	}

	public static String getTitleName(int position) {
		SubMenu submenu = menuOfpos(position);
		if(submenu != null) {
			return submenu.titleName;
		}
		return null;
	}
	
	public static BaseFragment getFragment(int position) {
		SubMenu menu = SubMenu.menuOfpos(position);
		BaseFragment fragment = null;
		switch (menu) {
		case SUB_1:
			fragment = PlaceholderFragment.newInstance(position + 1);
			break;
		case SUB_2:
			fragment = PlaceholderFragment.newInstance(position + 1);
			break;
		case SUB_3:
			fragment = PlaceholderFragment.newInstance(position + 1);
			break;
		case SUB_4:
			fragment = PlaceholderFragment.newInstance(position + 1);
			break;
		}
		return fragment;
	}
}
