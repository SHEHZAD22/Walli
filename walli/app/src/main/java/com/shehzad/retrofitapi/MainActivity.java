package com.shehzad.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shehzad.retrofitapi.adapter.MyAdapter;
import com.shehzad.retrofitapi.api.RetrofitClient;
import com.shehzad.retrofitapi.model.ImageModel;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter adapter;
    ImageModel model;
    EditText search;
    ProgressDialog dialog;
    String query;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        search = findViewById(R.id.search);
        button = findViewById(R.id.buttonfetchImage);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.progress_dialog_msg));
        fetchImages();
        button.setOnClickListener(view -> fetchImages());
    }

    //this method is for closing the keyboard after entering the text in editText
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void fetchImages() {
        closeKeyboard();
        search.clearFocus();
        dialog.show();
        query = search.getText().toString();
        Call<ImageModel> call;
        if(query.equals("")) call = RetrofitClient.getInstance().getApi().getImageList((int)(Math.random() * 20 + 1));
        else call = RetrofitClient.getInstance().getApi().getImageListPerPage(query, 1, 70);
        call.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                dialog.hide();
                if (response.isSuccessful()) {
                    model = response.body();
                    Log.d("response", "onResponse: "+response.body());
                    if (model != null) {
                        adapter = new MyAdapter(model.getHits(),getApplicationContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {
                dialog.hide();
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}