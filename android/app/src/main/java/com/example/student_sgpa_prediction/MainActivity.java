package com.example.student_sgpa_prediction;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b;
    EditText s1,s2,s3,s4,s5;
    String S1,S2,S3,S4,S5;
    TextView res;

    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        s1 = findViewById(R.id.sgpa1);
        s2 = findViewById(R.id.sgpa2);
        s3 = findViewById(R.id.sgpa3);
        s4 = findViewById(R.id.sgpa4);
        s5 = findViewById(R.id.sgpa5);
        res = findViewById(R.id.sgpa_predict);
    }

    public void POST(View view) {
            //awesomeValidation.addValidation(this, R.id.et_name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
            awesomeValidation.addValidation(this,R.id.sgpa1, Range.closed(0.0f, 10.0f), R.string.sgpa);
            awesomeValidation.addValidation(this,R.id.sgpa2, Range.closed(0.0f, 10.0f), R.string.sgpa);
            awesomeValidation.addValidation(this,R.id.sgpa3, Range.closed(0.0f, 10.0f), R.string.sgpa);
            awesomeValidation.addValidation(this,R.id.sgpa4, Range.closed(0.0f, 10.0f), R.string.sgpa);
            awesomeValidation.addValidation(this,R.id.sgpa5, Range.closed(0.0f, 10.0f), R.string.sgpa);
            if (awesomeValidation.validate()) {
                //volley Android
                if( !TextUtils.isEmpty(S1) && ( !TextUtils.isEmpty(S2) && ( !TextUtils.isEmpty(S3) &&( !TextUtils.isEmpty(S4) &&( !TextUtils.isEmpty(S5))))))
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    final String url = "";
                    JSONObject postParams = new JSONObject();
                    try {
                        postParams.put("S1", s1);
                        postParams.put("S2", s2);
                        postParams.put("S3", s3);
                        postParams.put("S4", s4);
                        postParams.put("S5", s5);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("On Response", "onResponse: " + response.toString());
                            res.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("On Error",error.toString());
                            Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                }
            }
        }
}