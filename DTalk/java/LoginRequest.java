package com.example.tpwls.maketalk;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tpwls on 2018-02-08.
 */

public class LoginRequest extends StringRequest{

    final static private String URL = "http://tpwls8122.cafe24.com/MemberLogin.php";
    private Map<String,String> parameters;

    public LoginRequest(String s_id, String s_password, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("s_id", s_id);
        parameters.put("s_password", s_password);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}
