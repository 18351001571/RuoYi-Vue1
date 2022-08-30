package com.ruoyi.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * description: 启动自动填充功能
 *
 * @return:
 * @author: weirx
 * @time: 2022/1/17 17:00
 */
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createBy", String.class, SecurityUtils.getUsername());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        try {
            this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtils.getUsername());
        } catch (Exception e) {

        }
    }
}