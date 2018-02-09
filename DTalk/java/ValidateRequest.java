package com.example.tpwls.maketalk;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tpwls on 2018-02-06.
 */

public class ValidateRequest extends StringRequest {

    final static private String URL = "http://tpwls8122.cafe24.com/MemberValidate.php";
    private Map<String,String> parameters;

    public ValidateRequest(String s_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("s_id", s_id);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}

