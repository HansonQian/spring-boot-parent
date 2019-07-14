package spring.boot.h2.mapper;

import org.apache.ibatis.annotations.*;
import spring.boot.h2.bean.Product;

import java.util.List;

public interface ProductMapper {
    @Select("SELECT *  FROM product where pid = #{id}")
    Product getProductById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "pid")
    @Insert("INSERT INTO product VALUES (#{pid},#{proName},#{proType},#{price},#{createTime})")
    int addProduct(Product p);

    @Update("update product set pro_name=#{proName} , pro_type=#{proType} , price=#{price} WHERE pid=#{pid}")
    int updateProduct(Product product);

    @Delete("DELETE FROM product WHERE pid = #{pid}")
    int delProductById(Integer pid);

    @Select("SELECT * FROM product")
    List<Product> queryAll();

}
