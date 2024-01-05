package egov.product.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lib.pagination.PaginationInfo;
import com.lib.util.Validation_Form;

import egov.product.dao.ProductMapper;
import egov.product.dto.ProductDTO;
import egov.product.service.ProductService;

@Service("ProductService")
public class ProductServiceImpl extends EgovAbstractServiceImpl implements ProductService{
    
	@Resource(name="ProductMapper")
	ProductMapper productMapper;
	
	@Resource(name="fileUploadProperty")
	Properties properties;

	@Override
	public void addProduct(HttpServletRequest request) {
	    String title = request.getParameter("title");
	    String content = request.getParameter("Contents");
	    String productCode = request.getParameter("productcode");
	    String price = request.getParameter("price");

	    HashMap<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("title", title);
	    paramMap.put("productcode", productCode);
	    paramMap.put("Contents", content);
	    paramMap.put("price", price);

	    final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	    final Map<String, MultipartFile> fileMap = multiRequest.getFileMap();

	    String uploadPath = properties.getProperty("file.ImgPath");

	    // 파일 정보 저장
	    if (fileMap != null && !fileMap.isEmpty()) {
	        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
	            MultipartFile file = entry.getValue();
	            String originalFilename = file.getOriginalFilename();
	            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	            String savedFilename = productCode + extension;
	            String fileUrl = uploadPath + savedFilename;

	            if (!file.isEmpty()) {
	                try {
	                    
	                    // 파일 저장
	                    file.transferTo(new File(fileUrl));

	                    paramMap.put("filename", savedFilename);
	                    paramMap.put("filetype", extension);
	                    paramMap.put("fileurl", fileUrl);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    try {
	        productMapper.addProduct(paramMap);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
    public List<ProductDTO> showProductList() throws Exception{
        return productMapper.showProductList();
    }
	
	@Override
	public ProductDTO showProduct(HttpServletRequest request) throws Exception {

		String productid= request.getParameter("productid");
		boolean validNumber = false;
		validNumber = Validation_Form.validNum(productid);

		if(validNumber==false)
		{
			throw new Exception("유효성검사실패");
		}
		int pid =  Integer.parseInt(productid);
		ProductDTO result = productMapper.showProduct(pid);

		if(result==null)
		{
			throw new Exception("페이지찾을수없음");
		}		
		System.out.println(result);
		return result;
	}

	@Override
	public void productRemove(HttpServletRequest request) throws Exception {
		
		String productid= request.getParameter("productid");
		
		boolean validNumber = false;
		validNumber = Validation_Form.validNum(productid);

		if(validNumber==false)
		{
			throw new Exception("유효성검사실패");
		}
		
		int pid =  Integer.parseInt(productid);
		ProductDTO result = productMapper.showProduct(pid);

		if(result==null)
		{
			throw new Exception("상품없음");
		}	
		
	    // 이미지 파일 경로
	    String imgFilePath = properties.getProperty("file.ImgPath");
        String fileUrl = imgFilePath + result.getFilename();

	    // 이미지 파일 삭제
	    File imgFile = new File(fileUrl);
	    if (imgFile.exists()) {
	        if (imgFile.delete()) {
	            System.out.println("사진 파일이 삭제되었습니다.");
	        } else {
	            System.out.println("사진 파일 삭제에 실패하였습니다.");
	        }
	    }
	    
		productMapper.productRemove(pid);
	}
	
}
