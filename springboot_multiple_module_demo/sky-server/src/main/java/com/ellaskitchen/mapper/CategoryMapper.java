package com.ellaskitchen.mapper;

import com.ellaskitchen.dto.CategoryPageQueryDTO;
import com.ellaskitchen.dto.CategoryUpdateStatusDTO;
import com.ellaskitchen.entity.Category;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 增 删 改 查 + 其他业务(分页查询，分类查询，启用禁用)
     */
    /**
     * 新增category
     * @param category
     */
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user)" + "VALUES " + " (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 根据id删除category
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void delete(long id);

    /**
     * 根据id查询category
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category getById(long id);

    /**
     *修改category
     */
    void updateCategory(Category category);

    /**
     * 分类启用，禁用
     * @param categoryUpdateStatusDTO
     */
//    void changeStatus(@Param("status") Integer status,
//                      @Param("userId") Long userId,
//                      @Param("updateTime") LocalDateTime updateTime,
//                      @Param("id") Long id);
    //创建category
    void updateStatus(CategoryUpdateStatusDTO categoryUpdateStatusDTO);

    /**
     * 分类查询：查询所有的菜品类 type：1 或者套餐类 type：2
     */

    List<Category> selectByType(Integer type);

    /**
     * 品类分页查询
     * 管理员想查看所有菜品分类：
     * 1. 选择 type=1（菜品分类）
     * 2. 系统默认 page=1, pageSize=10
     * 3. 页面就会显示第一页的10个菜品分类
     * 4. 如果想找"川菜"，可以在搜索框输入"川"
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

}
