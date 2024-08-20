package com.example.asimplecafe.api;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asimplecafe.Utils;
import com.example.asimplecafe.database.DBHandler;
import com.example.asimplecafe.fragment.HomeFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class APIHandler {
    private static final String TAG = "API HANDLER";

    public static void initProductsDB( Context ctx, String token ){
        DBHandler db = new DBHandler( ctx );
        String url = "https://tmiapi-dev.mitraindogrosir.co.id/api/get_data_member";

        RequestQueue queue = Volley.newRequestQueue(ctx);
        StringRequest request = new StringRequest( Request.Method.POST, url,
            response -> {
                try {
                    JSONObject memberList = new JSONObject(response);
                    JSONArray memberData = memberList
                            .getJSONObject("message")
                            .getJSONArray("data_product");

                    ExecutorService service = Executors.newFixedThreadPool(256);
                    for (int i = 0; i < memberData.length(); i++) {
                        JSONObject data = memberData.getJSONObject(i);

                        service.execute( () -> {
                            try {
                                db.addProduct(
                                        data.getString("product_name"),
                                        Integer.parseInt( data.getString("price" ) )
                                );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } );
                    }
                } catch (Exception e){
                    Log.e( TAG, e.toString( ) );
                }
            }, error -> {
                Utils.showToast(ctx, "API Failed: " + error);
                Log.e("Error POST VOLLEY", error.toString());
            } ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token); return headers;
            }
        };
        queue.add(request);
    }

    public static void doLogin(Context ctx, Fragment fragment) {
        String email = "ivanekadharma@gmail.com";
        String password = "123456";
        String url = "https://tmiapi-dev.mitraindogrosir.co.id/api/login_member_api";

        RequestQueue queue = Volley.newRequestQueue( ctx );
        StringRequest request = new StringRequest( Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject loginObject = new JSONObject(response);
                        String loginResult = loginObject.getString("message");
                        if ( loginResult.equals( "Berhasil masuk" ) ){
                            JSONObject responseObject = loginObject.getJSONObject("user_data");
                            String access_token = responseObject.getString("access_token");
                            Log.e("access code", access_token);
                            initProductsDB(ctx, access_token);
                            ((HomeFragment)fragment).notifyFirstInitialization();
                        }
                    } catch (Exception e){
                        Log.e("JSON OBJ VOLLEY ERROR", e.toString() );
                    }
                }, error -> {
                    Utils.showToast(ctx, "Login Failed: " + error);
                    Log.e("Error POST VOLLEY", error.toString() );
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        queue.add(request);
    }
}
