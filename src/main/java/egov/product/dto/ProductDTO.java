package egov.product.dto;

import java.util.Date;

public class ProductDTO {
	private int productid;
    private int productcode;
    private int price;
    private String userid;
    private Date writetime;
    private String title;
    private String contents;
    private String filename;
    private String fileurl;
	public int getProductid() {
		return productid;
	}
	public int getProductcode() {
		return productcode;
	}
	public int getPrice() {
		return price;
	}
	public String getUserid() {
		return userid;
	}
	public Date getWritetime() {
		return writetime;
	}
	public String getTitle() {
		return title;
	}
	public String getContents() {
		return contents;
	}
	public String getFilename() {
		return filename;
	}
	public String getFileurl() {
		return fileurl;
	}
    
}
