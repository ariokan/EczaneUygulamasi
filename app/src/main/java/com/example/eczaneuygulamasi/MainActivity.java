package com.example.eczaneuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> ilceler = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetData getir = new GetData();
        getir.execute();
    }
    public class GetData extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            try {
                Document doc =Jsoup.connect("https://apps.istanbulsaglik.gov.tr/NobetciEczane/").get();
                Log.d("html",doc.body().toString());

                Elements  elements= doc.getElementsByClass("ilce-link");
                Log.d("claslar",elements.toString());
            int i=0;
                for(Element ilce : elements){
                    ilceler.add(ilce.attr("data-value"));
                    Log.d("ilceler",ilceler.get(i));
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView listem = (ListView) findViewById(R.id.ilcelistesi);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,ilceler);
                    listem.setAdapter(adapter);

                    listem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.d("tıklanan",ilceler.get(position));
                            Intent intent = new Intent(MainActivity.this,screen2.class);
                            intent.putExtra("gidenilce", ilceler.get(position).toString());
                            startActivity(intent);

                        }
                    });
                }
            });

            return null;
        }
    }
}
