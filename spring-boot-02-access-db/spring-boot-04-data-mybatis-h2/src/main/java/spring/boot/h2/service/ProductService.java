package spring.boot.h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.h2.bean.Product;
import spring.boot.h2.mapper.ProductMapper;

import java.util.List;

@Service
public class ProductService {

    private ProductMapper productMapper;

    @Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    /**
     * 新增
     *
     * @param product
     * @return
     */
    public int saveProduct(Product product) {
        return this.productMapper.addProduct(product);
    }

    /**
     * 删除
     *
     * @param pid
     * @return
     */
    public int delProductById(Integer pid) {
        return this.productMapper.delProductById(pid);
    }

    /**
     * 修改
     *
     * @param product
     * @return
     */
    public int updateProduct(Product product) {
        return this.productMapper.updateProduct(product);
    }

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);
    }

    /**
     * 查询全部
     *
     * @return
     */
    public List<Product> queryAll() {
        return this.productMapper.queryAll();
    }


}
