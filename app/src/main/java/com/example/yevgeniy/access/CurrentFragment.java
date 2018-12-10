package com.example.yevgeniy.access;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrentFragment extends Fragment {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedprefretrofit";
    private static final String TOKEN = "token";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current,container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Current day");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String token = sharedPreferences.getString(TOKEN, "");

        Call<User> call = service.getCurrentDay("Bearer "+token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getActivity().getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                if (response.code() == 200) {
                    String[] menuItems = {"Item1", "Item2", "Blabla"};

                    ListView listView = (ListView) getActivity().findViewById(R.id.dayArray);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,menuItems);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(getApplicationContex(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
