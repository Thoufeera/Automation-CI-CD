import java.nio.charset.StandardCharsets;

public class DataReader {

	public List<HashMap<String,String>> readData()
	{
		String jsonContent=FileUtils.readFiletoString(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\rahulshettyacademy\\data\\PurchaseData.json)", StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HasMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HasMap<String,String>>>(){});
		return data;
	}
}
