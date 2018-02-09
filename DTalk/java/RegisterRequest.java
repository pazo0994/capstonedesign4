package com.example.tpwls.maketalk;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tpwls on 2018-02-08.
 */

public class RegisterRequest  extends StringRequest {


    final static private String URL = "http://tpwls8122.cafe24.com/MemberRegister.php";
    private Map<String,String> parameters;

    public RegisterRequest(String s_id, String s_password, String s_name, String s_major, String s_group, String s_phone, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("s_id", s_id);
        parameters.put("s_password", s_password);
        parameters.put("s_name", s_name);
        parameters.put("s_major", s_major);
        parameters.put("s_group", s_group);
        parameters.put("s_phone", s_phone);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}


