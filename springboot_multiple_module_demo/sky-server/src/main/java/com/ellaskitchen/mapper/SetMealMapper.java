package com.ellaskitchen.mapper;

import com.ellaskitchen.annotations.AutoFill;
import com.ellaskitchen.dto.SetMealChangeStatusDTO;
import com.ellaskitchen.dto.SetMealPageQueryDTO;
import com.ellaskitchen.entity.SetMeal;
import com.ellaskitchen.enumeration.OperationType;
import com.ellaskitchen.vo.SetMealVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

@Mapper
public interface SetMealMapper {



    /**
     * 新增套餐
     * @param setMeal
     */
    @Insert("insert into setmeal(category_id, name, price, status, description, image, create_time, update_time, create_user, update_user)" + "values " + "(#{categoryId},#{name}, #{price}, #{status},#{description},#{image},#{createTime} ,#{updateTime} ,#{createUser} ,#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void save(SetMeal setMeal);

    /**
     * 删除套餐
     * @param ids
     */
    void deleteById(List<Long> ids);

    /**
     * 修改套餐信息
     * @param setMeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(SetMeal setMeal);

    /**
     * 套餐 启卖 禁售
     * @param setMealChangeStatusDTO
     */
    @AutoFill(OperationType.UPDATE)
    void setMealChangeStatus(SetMealChangeStatusDTO setMealChangeStatusDTO);

    /**
     * 分页查询 可以根据需要，按照套餐名称、分类、售卖状态进行查询
     * @param setMealPageQueryDTO
     * @return
     */
    Page<SetMealVO> pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);

    /**
     * 查询当前目录id下的套餐
     * @categoryId
     * @return
     */
    Integer countByCategoryId(long categoryId);

    /**
     * 返回SetMeal
     */
    SetMeal getById(long id);


}
