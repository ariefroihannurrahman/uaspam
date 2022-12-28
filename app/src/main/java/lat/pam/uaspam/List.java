package lat.pam.uaspam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

public class List extends AppCompatActivity {

    TextView pizza, spageti, burger, steak, kentangGoreng;
    TextView pDetail, spDetail, bDetail, stDetail, kgDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pizza = findViewById(R.id.pepperoni_pizza);
        pDetail = findViewById(R.id.pizza_detail);
        spageti = findViewById(R.id.spaghetti);
        spDetail = findViewById(R.id.spaghetti_detail);
        burger = findViewById(R.id.burger);
        bDetail = findViewById(R.id.burger_detail);
        steak = findViewById(R.id.steak);
        stDetail = findViewById(R.id.steak_detail);
        kentangGoreng = findViewById(R.id.kentang_goreng);
        kgDetail = findViewById(R.id.kentang_goreng_detail);


        callServer();

        FloatingActionButton fab = findViewById(R.id.button_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List.this, Detail.class)
                );
            }
        });


    }

    private void callServer() {
        String url= BaseURL.base_url;
        StringRequest request = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);

                    for(int index = 0; index < jsonArray.length(); index++){
                        String pizzaName = jsonArray.getJSONObject(index).getString("foodName");
                        if(index == 0){pizza.setText(pizzaName);}
                        else if(index == 1){spageti.setText(pizzaName);}
                        else if(index == 2){burger.setText(pizzaName);}
                        else if(index == 3){steak.setText(pizzaName);}
                        else if(index == 4){kentangGoreng.setText(pizzaName);}
                        String pizzaDetails = jsonArray.getJSONObject(index).getString("details");
                        if(index == 0){pDetail.setText(pizzaDetails);}
                        else if(index == 1){spDetail.setText(pizzaDetails);}
                        else if(index == 2){bDetail.setText(pizzaDetails);}
                        else if(index == 3){stDetail.setText(pizzaDetails);}
                        else if(index == 4){kgDetail.setText(pizzaDetails);}
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(List.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(List.this);
        requestQueue.add(request);
    }
}