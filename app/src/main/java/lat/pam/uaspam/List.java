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

    TextView pizza, spaghetti, burger, steak, frenchFries;
    TextView pizzaDetail, spaghettiDetail, burgerDetail, steakDetail, frenchFriesDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pizza = findViewById(R.id.pepperoni_pizza);
        pizzaDetail = findViewById(R.id.pizza_detail);
        spaghetti = findViewById(R.id.spaghetti);
        spaghettiDetail = findViewById(R.id.spaghetti_detail);
        burger = findViewById(R.id.burger);
        burgerDetail = findViewById(R.id.burger_detail);
        steak = findViewById(R.id.steak);
        steakDetail = findViewById(R.id.steak_detail);
        frenchFries = findViewById(R.id.kentang_goreng);
        frenchFriesDetail = findViewById(R.id.kentang_goreng_detail);


        callServer();

        FloatingActionButton fab = findViewById(R.id.button_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(List.this, Detail.class));
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

                    String pizzaName = jsonArray.getJSONObject(0).getString("foodName");
                    pizza.setText(pizzaName);
                    String pizzaDetails = jsonArray.getJSONObject(0).getString("details");
                    pizzaDetail.setText(pizzaDetails);

                    String spaghettiName = jsonArray.getJSONObject(1).getString("foodName");
                    spaghetti.setText(spaghettiName);
                    String spaghettiDetails = jsonArray.getJSONObject(1).getString("details");
                    spaghettiDetail.setText(spaghettiDetails);

                    String burgerName = jsonArray.getJSONObject(2).getString("foodName");
                    burger.setText(burgerName);
                    String burgerDetails = jsonArray.getJSONObject(2).getString("details");
                    burgerDetail.setText(burgerDetails);

                    String steakName = jsonArray.getJSONObject(3).getString("foodName");
                    steak.setText(steakName);
                    String steakDetails = jsonArray.getJSONObject(3).getString("details");
                    steakDetail.setText(steakDetails);

                    String frenchFriesName = jsonArray.getJSONObject(4).getString("foodName");
                    frenchFries.setText(frenchFriesName);
                    String frenchFriesDetails = jsonArray.getJSONObject(4).getString("details");
                    frenchFriesDetail.setText(frenchFriesDetails);
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