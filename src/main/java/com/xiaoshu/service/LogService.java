package com.xiaoshu.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.LogMapper;
import com.xiaoshu.entity.Log;
import com.xiaoshu.entity.LogExample;
import com.xiaoshu.entity.LogExample.Criteria;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;

@Service
public class LogService {
	
	@Autowired
	LogMapper logMapper;

	public void insertLog(Log t) throws Exception {
		logMapper.insert(t);
	};

	public PageInfo<Log> pageLogCreateBetween(String start, String end, Log log, int pageNum, int pageSize, String ordername, String order) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"userid";
		order = StringUtil.isNotEmpty(order)?order:"desc";
		LogExample logexample = new LogExample();
		logexample.setOrderByClause(ordername+" "+order);
		Criteria criteria = logexample.createCriteria();
		if(StringUtil.isNotEmpty(log.getModule())){
			criteria.andModuleLike("%"+log.getModule()+"%");
		}
		if(StringUtil.isNotEmpty(log.getOperation())){
			criteria.andOperationLike("%"+log.getOperation()+"%");
		}
		if(StringUtil.isNotEmpty(log.getUsername())){
			criteria.andUsernameEqualTo(log.getUsername());
		}
		
		Calendar cal = Calendar.getInstance();
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1900-01-01 00:00:00");
        cal.setTime(startDate);
		Date startTime = cal.getTime();
		Date endTime = new Date();
		if(StringUtil.isNotEmpty(start)){
			startTime = TimeUtil.ParseTime(start, "yyyy-MM-dd HH:mm:ss");
		}
		if(StringUtil.isNotEmpty(end)){
			endTime = TimeUtil.ParseTime(end, "yyyy-MM-dd HH:mm:ss");
		}
		criteria.andCreatetimeBetween(startTime, endTime);
		List<Log> logs = logMapper.selectByExample(logexample);
		PageInfo<Log> pageInfo = new PageInfo<Log>(logs);
		return pageInfo;
	};

	public int countLog(Log t) throws Exception {
		return logMapper.selectCount(t);
	};

	public void deleteLog(long l) throws Exception {
		logMapper.deleteByPrimaryKey(l);
	};

	public void truncateLog() throws Exception {
		logMapper.truncateTable();
	}

	public List<Log> findLog(Log log) {
		return logMapper.select(log);
	};
}
