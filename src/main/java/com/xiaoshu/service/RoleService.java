package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.RoleMapper;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.RoleExample;
import com.xiaoshu.entity.RoleExample.Criteria;

@Service
public class RoleService {
	
	@Autowired
	RoleMapper roleMapper;

	public Role findOneRole(Integer roleId) throws Exception {
		return roleMapper.selectByPrimaryKey(roleId);
	};

	public List<Role> findRole(Role t) throws Exception {
		return roleMapper.select(t);
	};

	public int countRole(Role t) throws Exception {
		return roleMapper.selectCount(t);
	};

	public void deleteRole(Integer roleid) throws Exception {
		roleMapper.deleteByPrimaryKey(roleid);
	};

	public void addRole(Role t) throws Exception {
		roleMapper.insert(t);
	};

	public void updateRole(Role t) throws Exception {
		roleMapper.updateByPrimaryKeySelective(t);
	};

	// 通过名称判断是否存在，（不能重名）
	public Role existRoleWithRoleName(String roleName) throws Exception {
		RoleExample example = new RoleExample();
		example.createCriteria().andRolenameEqualTo(roleName);
		List<Role> roleList = roleMapper.selectByExample(example);
		return roleList.isEmpty()?null:roleList.get(0);
	};

	// 批量删除
	public void deleteRoleByRoleIds(String[] roleIds) throws Exception {
		for (String roleid : roleIds) {
			roleMapper.deleteByPrimaryKey(Integer.parseInt(roleid));
		}
	}

	public PageInfo<Role> findAllRolePage(Role role, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roleList = roleMapper.select(role);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
	    return pageInfo;
	}

	public PageInfo<Role> findRolePage(Role role, Integer pageNum, Integer pageSize, String ordername, String order) {
		PageHelper.startPage(pageNum, pageSize);
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"roleid";
		order = StringUtil.isNotEmpty(order)?order:"asc";
		RoleExample example = new RoleExample();
		example.setOrderByClause(ordername+" "+order);
		Criteria criteria = example.createCriteria();
		if(StringUtil.isNotEmpty(role.getRolename())){
			criteria.andRolenameLike("%"+role.getRolename()+"%");
		}
		List<Role> roleList = roleMapper.selectByExample(example);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
		return pageInfo;
	}

}
