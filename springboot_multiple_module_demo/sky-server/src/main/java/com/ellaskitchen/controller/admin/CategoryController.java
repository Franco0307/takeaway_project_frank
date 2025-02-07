package com.ellaskitchen.controller.admin;

import com.ellaskitchen.dto.CategoryDTO;
import com.ellaskitchen.dto.CategoryPageQueryDTO;
import com.ellaskitchen.dto.CategoryUpdateStatusDTO;
import com.ellaskitchen.entity.Category;
import com.ellaskitchen.result.PageResult;
import com.ellaskitchen.result.Result;
import com.ellaskitchen.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.Intercept;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增品类
     * @param categoryDTO
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();

    }

    @PostMapping
    @ApiOperation("删除分类")
    public Result<String> delete(@PathVariable long id){
        log.info("删除分类：{}", id);
        categoryService.delete(id);
        return Result.success();

    }
    @PutMapping
    @ApiOperation("修改分类")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类:{}", categoryDTO);
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }
    @PutMapping
    @ApiOperation("修改状态")
    public Result<String> updateStatus(@RequestBody CategoryUpdateStatusDTO categoryUpdateStatusDTO){
        log.info("修改分类：{}", categoryUpdateStatusDTO);
        categoryService.updateStatus(categoryUpdateStatusDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询：{}",categoryPageQueryDTO);
       return Result.success(categoryService.pageQuery(categoryPageQueryDTO));
    }

    @GetMapping("/list")
    @ApiOperation("分类查询")
    public Result<List<Category>> listResult(Integer type){
        log.info("分类查询：{}",type);
        List<Category> categoryList = categoryService.getByType(type);
        return Result.success(categoryList);
    }



}
