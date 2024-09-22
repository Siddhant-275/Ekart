package Hack.db;

public class Review 
{
	String  buyeremailid;
	String review;
	String selleremailid;
	String product_id;
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getBuyeremailid() {
		return buyeremailid;
	}
	public void setBuyeremailid(String buyeremailid) {
		this.buyeremailid = buyeremailid;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getSelleremailid() {
		return selleremailid;
	}
	public void setSelleremailid(String selleremailid) {
		this.selleremailid = selleremailid;
	}
	 
}
