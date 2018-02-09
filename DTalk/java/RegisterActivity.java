package com.example.tpwls.maketalk;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tpwls.d_talk.R;

import org.json.JSONObject;

/**
 * Created by tpwls on 2018-02-06.
 */

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private String s_id;
    private String s_password;
    private String s_name;
    private String s_major;
    private String s_group;
    private String s_phone;
    private AlertDialog dialog;
    private boolean validate = false; // 사용가능한거인지 체크해주는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.majorSpinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.major,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText phoneText = (EditText) findViewById(R.id.phoneText);

        final RadioGroup classGroup = (RadioGroup) findViewById(R.id.classGroup);
        final int classGroupID = classGroup.getCheckedRadioButtonId();
        s_group = ((RadioButton) findViewById(classGroupID)).getText().toString();

        classGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton classButton = (RadioButton) findViewById(i);
                s_group = classButton.getText().toString();
            }
        });



        final Button validateButton = (Button) findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String s_id = idText.getText().toString();
                if(validate){
                    return;
                }
                if(s_id.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디를 입력해주세요.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 있습니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate = true;
                                idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));

                                return;
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 없는 아이디입니다.")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateReqeust = new ValidateRequest(s_id,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateReqeust);
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String s_id = idText.getText().toString();
                String s_password = passwordText.getText().toString();
                String s_name = nameText.getText().toString();
                String s_major = spinner.getSelectedItem().toString();
                String s_phone = phoneText.getText().toString();

                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog  = builder.setMessage("먼저 중복 체크를 해주세요.")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                if(s_id.equals("")||s_password.equals("")||s_name.equals("")||s_major.equals("")||s_phone.equals("")||s_group.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog  = builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원 가입 성공")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                finish();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원 가입을 실패했습니다.")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(s_id,s_password,s_name,s_major,s_group,s_phone,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
