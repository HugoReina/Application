package com.example.test;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    RecyclerView recyclerView;
    String url = "http://onetaccueil.fr/API.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.mat); //matériel
        recyclerView = findViewById(R.id.recyclerView);
        Context context = this;



        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("JSON Response", response.toString()); // Ajoutez cette ligne
                        try {

                            List<MaterialItem> materialItemList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject item = response.getJSONObject(i);
                                String materialKey = item.getString("material_key");
                                materialItemList.add(new MaterialItem(materialKey));
                            }




                            // Set up RecyclerView
                            RecyclerView recyclerView = findViewById(R.id.recyclerView);
                            MaterialAdapter adapter = new MaterialAdapter(materialItemList, this);
                            LinearLayoutManager lm = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                            recyclerView.setLayoutManager(lm);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(adapter);

                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString()); // Ajoutez cette ligne pour afficher les erreurs
                        // Handle error
                    }
                });

// Add the request to the RequestQueue
        Volley.newRequestQueue(this).add(request);
    }
}