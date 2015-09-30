package com.epam.facebookApi;

import com.epam.models.Likes;
import com.epam.models.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.util.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 * Created by Hleb_Lizunkou1 on 9/29/2015.
 */
@Stateless
public class FacebookDataGrabber {
    private static final String BASE_PATH = "https://graph.facebook.com";
    private static final String PERSONAL_DATA = "/me";
    private static final String GROUPS = "/me/groups";
    private static final String LIKES = "/me/likes";
    private static final String NOTIFY = "/notifications";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String CLIENT_ID_FIELD = "client_id";
    private static final String CLIENT_ID_VALUE="1608409702752496";
    private static final String CLIENT_SECRET_FIELD="client_secret";
    private static final String CLIENT_SECRET_VALUE="247935607f1dba0d1ecea0c909ca37bd";
    private static final String GRANT_TYPE_FIELD = "grant_type";
    private static final String GRANT_TYPE_VALUE = "client_credentials";
    private static final String ACCESS_TOKEN_APP="/oauth/access_token";

    public String getPersonalData(String token) {
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(BASE_PATH + PERSONAL_DATA)
                    .header("accept", "application/json")
                    .queryString(ACCESS_TOKEN, token)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }

    public String getGroups(String token) {
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(BASE_PATH + GROUPS)
                    .header("accept", "application/json")
                    .queryString(ACCESS_TOKEN, token)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }

    public String getLikes(String token) {
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(BASE_PATH + LIKES)
                    .header("accept", "application/json")
                    .queryString(ACCESS_TOKEN, token)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }

    public User fillData(User user) {
        String accessToken = user.getAccessToken();
        String personalData = getPersonalData(accessToken);
        String likes = getLikes(accessToken);
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(personalData);
            JSONObject jsonObj = (JSONObject) obj;
            user.setBirthday((String) jsonObj.get("birthday"));
            user.setGender((String) jsonObj.get("gender"));
            user.setLocation((String) ((JSONObject) jsonObj.get("hometown")).get("name"));
            user.setName((String) jsonObj.get("name"));
            obj = parser.parse(likes);
            jsonObj = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObj.get("data");
            ArrayList<Likes> likesList = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                String category = (String) ((JSONObject) array.get(i)).get("category");
                if(category.contains("Car")||category.contains("Automobiles")){
                    Likes like = new Likes();
                    like.setName((String) ((JSONObject) array.get(i)).get("name"));
                    like.setCategory(category);
                    likesList.add(like);
                }
            }
            user.setLikes(likesList);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean sendNotify(String href,String template,String userID){
        String accesKey = getAcessKey();
        JSONParser parser = new JSONParser();
        HttpResponse<String> jsonResponse = null;
        href = "\"href\":\""+href+"\"";
        template = "\"template\":\""+template+"\"";
        accesKey = "\"access_token\":\""+accesKey.substring(13)+"\"";
        try {
            String body = "{"+accesKey+','+href+','+template+ "}";
            JsonNode value = new JsonNode(body);
            jsonResponse = Unirest.post(BASE_PATH + "/" + userID+ NOTIFY)
                    .header("Content-Type", "application/json").body(value).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private String getAcessKey(){
        HttpResponse<String> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(BASE_PATH + ACCESS_TOKEN_APP)
                    .header("accept", "application/json")
                    .queryString(CLIENT_ID_FIELD, CLIENT_ID_VALUE)
                    .queryString(CLIENT_SECRET_FIELD,CLIENT_SECRET_VALUE)
                    .queryString(GRANT_TYPE_FIELD,GRANT_TYPE_VALUE)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse.getBody();
    }
}
