package demo;

public class Company {

	String companyName;
	String stockRate;
	int sharePrice;
	int previousClose;
	int percentChange;

	public String getStockRate() {
		return stockRate;
	}

	public void setStockRate(String stockRate) {
		this.stockRate = stockRate;
	}

	public int getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(int previousClose) {
		this.previousClose = previousClose;
	}

	public int getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(int percentChange) {
		this.percentChange = percentChange;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(int sharePrice) {
		this.sharePrice = sharePrice;
	}


}
