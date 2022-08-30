package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;

/**
 * 分类信息对象 cms_type
 *
 * @author ruoyi
 * @date 2022-08-30
 */
@Data
@TableName("cms_type")
public class CmsType extends BaseEntity {
private static final long serialVersionUID = 1L;

    /** 分类ID */
    @TableId(type = IdType.AUTO)
    private Long typeId;

    /** 分类名称 */
    @TableField
    private String typeName;

    /** 分类图像 */
    @TableField
    private String typePic;

    /** 排序优先级 */
    @TableField
    private Long priority;

    /** 展示层级 */
    @TableField
    private Long displayLevel;

    /** 1启用，0禁用 */
    @TableField
    private Integer status;

    /** 是否是导航，1是，0否 */
    @TableField
    private Integer isNavigation;

}
