package com.xiaoshu.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.MenuMapper;
import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.MenuExample;
import com.xiaoshu.util.StringUtil;

@Service
public class MenuService {

	@Autowired
	MenuMapper menuMapper;

	public List<Menu> findMenu(Menu menu) throws Exception {
		return menuMapper.select(menu);
	};

	public long countMenu(Menu menu) throws Exception {
		return menuMapper.selectCount(menu);
	};

	public void addMenu(Menu menu) throws Exception {
		menuMapper.insert(menu);
	};

	public void updateMenu(Menu menu) throws Exception {
		if(StringUtil.isNotEmpty(menu.getIconcls())){
			menu.setIconcls(menu.getIconcls()); 
		}
		menuMapper.updateByPrimaryKeySelective(menu);
	};

	public void deleteMenu(Integer menuid) throws Exception {
		menuMapper.deleteByPrimaryKey(menuid);
	};

	@SuppressWarnings("rawtypes")
	public List<Menu> menuTree(Map map) throws Exception {
		Integer parentId = (Integer)map.get("parentId");
		String[] menuIdStrs = (String[]) map.get("menuIds");
		Integer menuIds[] = StringUtil.stringArrToIntergerArr(menuIdStrs);
		MenuExample example = new MenuExample();
		example.createCriteria().andMenuidIn(Arrays.asList(menuIds)).andParentidEqualTo(parentId);
		return menuMapper.selectByExample(example);
	}

	public Menu findMenuByMenuid(Integer menuid) {
		return menuMapper.selectByPrimaryKey(menuid);
	};
}
