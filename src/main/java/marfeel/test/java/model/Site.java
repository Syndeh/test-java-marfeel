package marfeel.test.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="site")
public class Site {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="url", nullable=false)
	private String url;
	@Column(name="marfeelizable")
	private Boolean marfeelizable;
	
	public Site() {}
	
	public Site(String url, Boolean marfeelizable) {
		super();
		this.url = url;
		this.marfeelizable = marfeelizable;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getMarfeelizable() {
		return marfeelizable;
	}
	public void setMarfeelizable(Boolean marfeelizable) {
		this.marfeelizable = marfeelizable;
	}
}
