package com.alibaba.dbhub.server.domain.core.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.alibaba.dbhub.server.domain.api.model.OperationLog;
import com.alibaba.dbhub.server.domain.api.param.OperationLogCreateParam;
import com.alibaba.dbhub.server.domain.api.param.OperationLogPageQueryParam;
import com.alibaba.dbhub.server.domain.api.service.OperationLogService;
import com.alibaba.dbhub.server.domain.core.converter.OperationLogConverter;
import com.alibaba.dbhub.server.domain.repository.entity.OperationLogDO;
import com.alibaba.dbhub.server.domain.repository.mapper.OperationLogMapper;
import com.alibaba.dbhub.server.tools.base.wrapper.result.DataResult;
import com.alibaba.dbhub.server.tools.base.wrapper.result.PageResult;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moji
 * @version UserExecutedDdlCoreServiceImpl.java, v 0.1 2022年09月25日 14:07 moji Exp $
 * @date 2022/09/25
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private OperationLogConverter operationLogConverter;

    @Override
    public DataResult<Long> create(OperationLogCreateParam param) {
        OperationLogDO userExecutedDdlDO = operationLogConverter.param2do(param);
        userExecutedDdlDO.setGmtCreate(LocalDateTime.now());
        userExecutedDdlDO.setGmtModified(LocalDateTime.now());
        operationLogMapper.insert(userExecutedDdlDO);
        return DataResult.of(userExecutedDdlDO.getId());
    }

    @Override
    public PageResult<OperationLog> queryPage(OperationLogPageQueryParam param) {
        QueryWrapper<OperationLogDO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(param.getSearchKey())) {
            queryWrapper.like("ddl", param.getSearchKey());
        }
        Integer start = param.getPageNo();
        Integer offset = param.getPageSize();
        Page<OperationLogDO> page = new Page<>(start, offset);
        IPage<OperationLogDO> executedDdlDOIPage = operationLogMapper.selectPage(page, queryWrapper);
        List<OperationLog> executedDdlDTOS = operationLogConverter.do2dto(executedDdlDOIPage.getRecords());
        return PageResult.of(executedDdlDTOS, executedDdlDOIPage.getTotal(), param);
    }
}
