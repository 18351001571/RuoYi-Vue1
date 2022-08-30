package com.ruoyi.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.CmsType;
import com.ruoyi.system.service.ICmsTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;

import java.util.List;


/**
 * 分类信息Controller
 *
 * @author ruoyi
 * @date 2022-08-30
 */
@Api(value = "type", tags = "type")
@RestController
@RequestMapping("/system/type")
public class CmsTypeController extends BaseController
{
    @Autowired
    private ICmsTypeService cmsTypeService;

    /**
     * 获取分类信息分页列表
     */
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/pageList")
    public AjaxResult pageList(Page page, CmsType cmsType) {
        return AjaxResult.success(cmsTypeService.page(page, new QueryWrapper<>(cmsType)));
    }

    /**
     * 查询分类信息列表
     */
    @ApiOperation(value = "查询全部", notes = "查询全部")
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public AjaxResult list(CmsType cmsType) {
        return AjaxResult.success(cmsTypeService.list(new QueryWrapper<>(cmsType)));
    }


    /**
     * 导出分类信息列表
     */
    @ApiOperation(value = "导出", notes = "导出")
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "分类信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsType cmsType) {
        List<CmsType> list = cmsTypeService.list(new QueryWrapper<>(cmsType));
        ExcelUtil<CmsType> util = new ExcelUtil<CmsType>(CmsType.class);
        util.exportExcel(response, list, "分类信息数据");
    }

    /**
     * 获取分类信息详细信息
     */
    @ApiOperation(value = "根据id获取详情", notes = "根据id获取详情")
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId) {
        return AjaxResult.success(cmsTypeService.getById(typeId));
    }

    /**
     * 新增分类信息
     */
    @ApiOperation(value = "新增", notes = "新增")
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "分类信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsType cmsType) {
        return toAjax(cmsTypeService.save(cmsType));
    }

    /**
     * 修改分类信息
     */
    @ApiOperation(value = "更新", notes = "更新")
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "分类信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsType cmsType) {
        return toAjax(cmsTypeService.updateById(cmsType));
    }

    /**
     * 删除分类信息
     */
    @ApiOperation(value = "根据ids批量删除", notes = "根据ids批量删除")
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "分类信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable List<Long> typeIds) {
        return toAjax(cmsTypeService.removeBatchByIds(typeIds));
    }
}
