package egov.product.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egov.product.dto.ProductDTO;
import egov.product.service.ProductService;

@Controller
public class ProductController {
	
    @Resource(name = "ProductService")
    private ProductService productService;
	

	
    @RequestMapping("/sale/productWrite.do")
    public String addProductPage(HttpServletRequest request,ModelMap model) {
    	
        return "main/sale/productwrite";
    }
    
    @RequestMapping("/sale/add.do")
    public String addProduct(HttpServletRequest request,ModelMap model) throws Exception {
    	
        productService.addProduct(request);
        
        return "redirect:/sale/productList.do";
    }
    
    @RequestMapping("/sale/productRemove.do")
    public String productRemove(HttpServletRequest request,ModelMap model) throws Exception {
    	
        productService.productRemove(request);
        
        return "redirect:/sale/productList.do";
    }
	 
		@RequestMapping(value="/sale/productList.do")
		public String productList(HttpServletRequest request,ModelMap model)throws Exception
		{
			List<ProductDTO> list = productService.showProductList();
			
			model.addAttribute("productlist",list);
			
			return "main/sale/productlist";
		}
		
		@RequestMapping(value="/sale/productView.do")
		public String productView(HttpServletRequest request,ModelMap model)throws Exception
		{
				ProductDTO dto = productService.showProduct(request);
		        model.addAttribute("dto", dto);

			return "main/sale/productview";
}
}
