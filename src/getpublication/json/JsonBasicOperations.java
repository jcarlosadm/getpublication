package getpublication.json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class JsonBasicOperations {

    private JSONObject jsonObject = new JSONObject();
    
    public JSONObject getJsonObject(){
        return this.jsonObject;
    }
    
    public boolean load(){
        File file = new File(this.getJsonPath());
        if (file.exists()) {
            JSONParser jsonParser = new JSONParser();
            try {
                this.jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
            } catch (IOException | ParseException e) {
                System.out.println("error to load json file");
                return false;
            }
        }
        return true;
    }
    
    public boolean save() {
        try {
            FileUtils.writeStringToFile(new File(this.getJsonPath()),
                    this.jsonObject.toJSONString());
        } catch (IOException e) {
            System.out.println("Error to save json file");
            return false;
        }
        
        return true;
    }
    
    protected abstract String getJsonPath();
}
