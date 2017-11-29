package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.MenuMapper;
import com.xiaoshu.dao.OperationMapper;
import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Operation;

@Service
public class OperationService {

	@Autowired
	OperationMapper operationMapper;
	
	@Autowired
	MenuMapper menuMapper;
	
	public PageInfo<Operation> findOperationPage(Operation t, int page, int rows) throws Exception {
		PageHelper.startPage(page, rows);
		List<Operation> operationList = operationMapper.select(t);
        PageInfo<Operation> pageInfo = new PageInfo<Operation>(operationList);
        return pageInfo;
	};
	
	public List<Operation> findOperation(Operation t) throws Exception {
        return operationMapper.select(t);
	};

	public int countOperation(Operation t) throws Exception {
		return operationMapper.selectCount(t);
	};

	public void addOperation(Operation t) throws Exception {
		operationMapper.insert(t);
	};

	public void updateOperation(Operation t) throws Exception {
		t.setMenuid(null);
		operationMapper.updateByPrimaryKeySelective(t);
	};

	public void deleteOperation(Integer operationId) throws Exception {
		operationMapper.deleteByPrimaryKey(operationId);
	}

	public Menu getMenuByMenuid(Integer menuid) {
		return menuMapper.selectByPrimaryKey(menuid);
	}

	public List<Operation> findOperationIdsByMenuid(Integer menuid) {
		Operation operation = new Operation();
		operation.setMenuid(menuid);
		return operationMapper.select(operation);
	};

}