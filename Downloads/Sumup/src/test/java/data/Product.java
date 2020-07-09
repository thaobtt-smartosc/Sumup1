package data;

public class Product {
	String url;
	String name;
	String price;
	String color;
	String size;
	/**
	 * @param url
	 * @param name
	 * @param price
	 * @param color
	 * @param size
	 */
	public Product(String url, String name, String price, String color, String size) {
		super();
		this.url = url;
		this.name = name;
		this.price = price;
		this.color = color;
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String toString() {

		return "url: " + url + "\n"
				+ "name: " + name + "\n"
				+ "price: " + price + "\n"
				+ "color: " + color + "\n"
				+ "size: " + size + "\n";
	}
}
