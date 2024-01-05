package egov.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.psl.dataaccess.EgovAbstractDAO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import egov.product.dto.ProductDTO;

@Mapper("ProductMapper")
public interface ProductMapper {
	
	void addProduct(HashMap<String, Object> paramMap)throws Exception;

    List<ProductDTO> showProductList();
    
    ProductDTO showProduct(int productid);
    
    void productRemove(int productid);

}
