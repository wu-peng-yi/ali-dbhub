package com.alibaba.dbhub.server.domain.support.model;

import java.util.List;

import com.alibaba.dbhub.server.domain.support.enums.IndexTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 索引信息
 *
 * @author Jiaju Zhuang
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TableIndex {

    /**
     * 索引名称
     */
    private String name;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 索引类型
     *
     * @see IndexTypeEnum
     */
    private String type;

    /**
     * 注释
     */
    private String comment;

    /**
     * 索引包含的列
     */
    private List<TableIndexColumn> columnList;
}
