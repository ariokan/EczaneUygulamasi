package com.example.eczaneuygulamasi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class screen2 extends AppCompatActivity {
    TextView header;
    Button mapbtn;
    public ArrayList<Eczane> EczaneListesi = new ArrayList<Eczane>();
    public ArrayList<Integer> indexlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        header = (TextView)findViewById(R.id.header);
        header.setText(getIntent().getExtras().getString("gidenilce")+" "+"ilcesindeki nöbeçi eczaneler");
        GetInfo getir =new GetInfo();
        getir.execute();

    }
        class GetInfo extends AsyncTask<String,String,String>{
        String ilceisim = getIntent().getExtras().getString("gidenilce");
        String deneme = ("#"+ilceisim);

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection.Response response = Jsoup.connect("https://apps.istanbulsaglik.gov.tr/NobetciEczane/HOME/GetEczaneler")
                        .userAgent("Mozilla/5.0").method(Connection.Method.POST).data("ilce",ilceisim).execute();
                Document doc = response.parse();
                Log.d("HTML2",doc.body().toString());
                return doc.body().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Document document =Jsoup.parse(s);
                    Elements elements= document.getElementsByClass("card");
                    String eczane, telefon,fax,sgk,adres,tarif="";
                    int i=0;
                    for(Element ec  : elements){
                            Document dc =Jsoup.parse(ec.toString());
                            Elements eczanesorgu = dc.select("div[class=card-header text-center]");
                            eczane=eczanesorgu.get(1).text();
                            Log.d("eczane ismi",eczane);

                            Elements bilgiler =dc.select("div>table>tbody>tr>td>label");
                            telefon =bilgiler.get(1).text();
                            Log.d("telefon",telefon);
                            fax =bilgiler.get(3).text();
                            sgk =bilgiler.get(5).text();
                            adres=bilgiler.get(7).text();

                            Element elo = dc.select("a[class=btn btn-primary btn-block]").get(0);
                            Log.d("aloalo",elo.attr("href"));
                            String kordinat =elo.attr("href");
                            String x= kordinat.split("\\?")[1].split("&")[0].split("=")[1];
                            String y= kordinat.split("\\?")[1].split("&")[1].split("=")[1];
                            Log.d("kord",x+"  "+y);
                            EczaneListesi.add(new Eczane(eczane,telefon,fax,sgk,adres,x,y,i));
                            indexlist.add(EczaneListesi.indexOf(i));
                    }
                    final ListView listView = (ListView) findViewById(R.id.bilgilistesi);
                    CustomAdaptor adapter = new CustomAdaptor(screen2.this, EczaneListesi);
                    listView.setAdapter(adapter);

                }
            });

        }
    }

}
