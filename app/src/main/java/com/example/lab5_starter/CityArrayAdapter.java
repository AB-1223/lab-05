package com.example.lab5_starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import com.google.firebase.firestore.FirebaseFirestore;

public class CityArrayAdapter extends ArrayAdapter<City> {
    private Context context;

    public CityArrayAdapter(Context context, ArrayList<City> cities){
        super(context, 0, cities);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_city, parent, false);
        }

        final City currentCity = getItem(position);
        if (currentCity == null) return view;

        TextView cityName = view.findViewById(R.id.textCityName);
        TextView cityProvince = view.findViewById(R.id.textCityProvince);

        cityName.setText(currentCity.getName());
        cityProvince.setText(currentCity.getProvince());

        Button deleteButton = view.findViewById(R.id.buttonDelete);
        if (deleteButton != null) {
            deleteButton.setOnClickListener(v -> {
                FirebaseFirestore.getInstance()
                        .collection("cities")
                        .document(currentCity.getName())
                        .delete();
            });
        }

        return view;
    }
}
