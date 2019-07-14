package spring.boot.h2.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.h2.bean.Product;
import spring.boot.h2.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 新增
     *
     * @param product
     * @return
     */
    @ApiOperation(value = "创建产品", notes = "创建产品")
    @ApiImplicitParam(name = "product", value = "产品信息的实体", required = true, dataType = "Product")
    @PostMapping("/prod")
    public ResponseEntity<Void> addProduct(Product product) {
        this.productService.saveProduct(product);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除产品", notes = "根据url的id来指定id产品")
    @ApiImplicitParam(name = "id", value = "产品标识ID", required = true, dataType = "Integer", paramType = "path")
    @DeleteMapping("/prod/{id}")
    public ResponseEntity<Void> delProduct(@PathVariable("id") Integer id) {
        this.productService.delProductById(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }

    /**
     * 更新
     *
     * @param product
     * @return
     */
    @ApiOperation(value = "更新信息", notes = "更新产品信息")
    @ApiImplicitParam(name = "product", value = "产品实体product", required = true, dataType = "Product")
    @PutMapping("/prod")
    public ResponseEntity<Void> updateProduct(Product product) {
        this.productService.updateProduct(product);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取产品的详细信息", notes = "根据url的id来获取产品的详细信息")
    @ApiImplicitParam(name = "id", value = "产品标识", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("/prod/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * 获取全部
     *
     * @return
     */
    @ApiOperation(value = "获取全部的产品信息", notes = "根据url来获取全部的产品信息")
    @GetMapping("/prods")
    public ResponseEntity<List<Product>> queryAll() {
        List<Product> productList = productService.queryAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
