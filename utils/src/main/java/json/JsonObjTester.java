package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjTester {
    public static void main(String[] args) {
        JsonObjTester jsonObjTester= new JsonObjTester();

        System.out.println(jsonObjTester.getStringFromJsonObj("1", "MODE", "Linux", "4.3", "param1"));

        System.out.println(jsonObjTester.getStringFromJsonObj("2", "MODE2", "IOS", null, "param2"));

        System.out.println();
    }

    private String getStringFromJsonObj(String id, String mode, String os, String version, String param) {
        // Construct the POST data.
        JSONObject flow = new JSONObject();
        try {


            JSONArray listeJsonArray = new JSONArray();

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", id);
            jsonObj.put("mode", mode);
            jsonObj.put("os", os);
            jsonObj.put("version", version == null ? JSONObject.NULL : version);

            listeJsonArray.put(jsonObj);

            JSONObject jsonObjBig = new JSONObject();
            jsonObjBig.put("param", param);
            jsonObjBig.put("sousparamstr", "value1");
            jsonObjBig.put("sousparamint", "0");
            jsonObjBig.put("sousparamstr2", "value2");
            jsonObjBig.put("array", listeJsonArray);

            JSONObject encJsonObj = new JSONObject();
            encJsonObj.put("encJsonObj", jsonObjBig);

            flow.put("finalJsonObj", encJsonObj);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return flow.toString();
    }


    public final String getIdFromJson(final String jsonFlow) {

        JSONObject jsonObject = new JSONObject(jsonFlow);
        return jsonObject.get("id").toString();

    }
}
