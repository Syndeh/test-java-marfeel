package marfeel.test.java.resources;

public class SiteResponse {

	private String status;
	private Integer siteToEvaluate;
	
	public SiteResponse(String status, Integer siteToEvaluate) {
		super();
		this.status = status;
		this.siteToEvaluate = siteToEvaluate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getSiteToEvaluate() {
		return siteToEvaluate;
	}
	public void setSiteToEvaluate(Integer siteToEvaluate) {
		this.siteToEvaluate = siteToEvaluate;
	}	
}
