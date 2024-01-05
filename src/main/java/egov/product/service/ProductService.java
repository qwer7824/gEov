package egov.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egov.product.dto.ProductDTO;

public interface ProductService {
    void addProduct(HttpServletRequest request)throws Exception;

    List<ProductDTO> showProductList()throws Exception;
    
    ProductDTO showProduct(HttpServletRequest request) throws Exception;

	void productRemove(HttpServletRequest request) throws Exception;

}
