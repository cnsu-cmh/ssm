package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Log;

public interface LogMapper extends BaseMapper<Log> {

	void truncateTable();
}