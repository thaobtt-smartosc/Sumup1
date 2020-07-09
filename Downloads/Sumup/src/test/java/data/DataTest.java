package data;
import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class DataTest {

	JSONObject jsonObject, product, address;
	
	public DataTest() {
		File file= new File(System.getProperty("user.dir") + "/product.json");
		File fileAddress= new File(System.getProperty("user.dir") + "/address.json");
		try {
			product = new JSONObject(FileUtils.readFileToString(file,"utf-8"));
			address = new JSONObject(FileUtils.readFileToString(fileAddress, "utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Product getProduct(String id) {
		jsonObject = (JSONObject) product.get(id);

		return new Product(jsonObject.getString("url"),
				jsonObject.getString("name"),
				jsonObject.getString("price"),
				jsonObject.getString("color"),
				jsonObject.getString("size"));
	}

    public Address getAddress(String id) {
        jsonObject = (JSONObject) address.get(id);

        return new Address(jsonObject.getString("firstName"),
                jsonObject.getString("lastName"),
                jsonObject.getString("street"),
                jsonObject.getString("city"),
                jsonObject.getString("state"),
                jsonObject.getString("zipCode"),
                jsonObject.getString("country"),
                jsonObject.getString("phoneNumber"));
    }
}
