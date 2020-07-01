package model;

public enum PANG {

	fun("View/resource/chooser/pang1.png","View/resource/chooser/life.png"),
	cute("View/resource/chooser/pang2.png","View/resource/chooser/life.png");
	
	private String urlpang;
	private String urlLife;
	
	private PANG(String urlpang,String urlLife) {
		this.urlpang = urlpang;
		this.urlLife = urlLife;
	}
	
	public String getUrl() {
		return this.urlpang;
	}
	public String getUrlLife() {
		return urlLife;
	}
	
}
