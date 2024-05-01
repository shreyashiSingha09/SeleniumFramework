package TestData;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        //Using Java Class FileUtils method at first convert the json to string
       String jsonContent= FileUtils.readFileToString(new File("E:\\Intelij Projects\\SeleniumFrameworkEx\\src\\test\\java\\TestData\\PurchaseOrderJsonData.json"),
                StandardCharsets.UTF_8);
       //Convert string to Hashmap
        ObjectMapper mapper=new ObjectMapper();
       List<HashMap<String,String>> data= mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>() {
       });
       return data;
    }
}

