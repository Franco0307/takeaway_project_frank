package com.ellaskitchen.mapper;

import com.ellaskitchen.dto.CategoryUpdateStatusDTO;
import com.ellaskitchen.entity.Category;
import org.apache.ibatis.annotations.*;

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
     * 菜品启用，禁用
     * @param categoryUpdateStatusDTO
     */
//    void changeStatus(@Param("status") Integer status,
//                      @Param("userId") Long userId,
//                      @Param("updateTime") LocalDateTime updateTime,
//                      @Param("id") Long id);
    //创建category
    void updateStatus(CategoryUpdateStatusDTO categoryUpdateStatusDTO);







}
