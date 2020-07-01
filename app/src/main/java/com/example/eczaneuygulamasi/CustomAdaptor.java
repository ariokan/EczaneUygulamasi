package com.example.eczaneuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;


public class CustomAdaptor extends BaseAdapter {

    private final LayoutInflater inflater;
    private final Context context;
    private final  ArrayList<Eczane> Eczanes;

    public CustomAdaptor(Context context, ArrayList<Eczane> eczanes) {
        this.inflater =(LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        Eczanes = eczanes;
    }


    @Override
    public int getCount() {
        return Eczanes.size();
    }

    @Override
    public Object getItem(int position) {
        return Eczanes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View lineView;
        lineView = inflater.inflate(R.layout.customview,null);


        TextView txtName = (TextView)lineView.findViewById(R.id.ecname);
        TextView txtTel =  (TextView)lineView.findViewById(R.id.ectel);
        TextView txtFaks = (TextView)lineView.findViewById(R.id.ecfax);
        TextView txtSgk = (TextView)lineView.findViewById(R.id.ecsgk);
        TextView txtAdres = (TextView)lineView.findViewById(R.id.ecadres);
        Button mapbtn =(Button)lineView.findViewById(R.id.haritabutton);


        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tıklanma","tıklanan btn");
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("kordx",Eczanes.get(position).Getx());
                intent.putExtra("kordy",Eczanes.get(position).Gety());
                context.startActivity(intent);

            }
        });

        Eczane ecz = Eczanes.get(position);
        txtName.setText("Eczane Adı"+" "+ecz.getName());
        txtTel.setText("Eczane Telefonu"+" "+ecz.getTel());
        txtFaks.setText("Eczane Faks "+" "+ ecz.getFaks());
        txtSgk.setText("SGK"+" "+ecz.getSgk());
        txtAdres.setText("Adres"+" "+ecz.getAddress());


        return lineView;
    }
}
