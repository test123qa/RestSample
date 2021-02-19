package Util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by gveenam on 2/16/2021.
 */
public class TestUtil {


    public static String getValueByJPath(JsonObject responsejson, String jpath){
        Object obj = responsejson;
        for(String s : jpath.split("/"))
            if(!s.isEmpty())
                if(!(s.contains("[") || s.contains("]")))
                    obj = ((JsonObject) obj).get(s);
                else if(s.contains("[") || s.contains("]"))
                    obj = ((JsonArray) ((JsonObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
        return obj.toString();
    }



}
