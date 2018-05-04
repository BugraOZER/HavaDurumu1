package com.bugra.myapplication;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import android.app.ListActivity;



public class MainActivity extends AppCompatActivity {
        static TextView xtemp,xtemp_min,xtemp_max,xhumidity,xdate,xdescription;
        Button button;
        ImageButton add;
        static ImageView imageView2;
        static String apilink;
        int i;
        static Toolbar toolbar;
        static String desc,icon,city;
        static int a;
        static ArrayList<String> sehirler = new ArrayList<>();
        static ArrayList<String> sehirid = new ArrayList<>();
        static ArrayList<String> city_url= new ArrayList<>();










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        xtemp=findViewById(R.id.tv_temp);
        toolbar= findViewById(R.id.toolbar_main);
        xdescription=findViewById(R.id.tv_desc);
        xtemp_min=findViewById( R.id.tv_min );
        xtemp_max=findViewById( R.id.tv_max );
        xhumidity=findViewById( R.id.tv_humidty );
        xdate=findViewById( R.id.tv_date );
        button= findViewById(R.id.bttn);
        add=findViewById(R.id.addbttn);
        imageView2=findViewById(R.id.imageView2);


        setSehirler();





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),fragment.class);
                startActivity(intent);
            }
        });


       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                final int x;
                x=random.nextInt(100000);
                apilink=city_url.get(x);
                //fragment.hava_durumu();
                Toast.makeText(getApplicationContext(),city_url.get(x),Toast.LENGTH_LONG).show();

                }
        });



        setSehirler();
      //  hava_durumu();
    }

   public void hava_durumu() {

        String url =apilink;
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                        JSONObject o_main = response.getJSONObject( "main" );

                            JSONArray JA = response.getJSONArray( "weather" );
                            JSONObject JO = JA.getJSONObject( 0 );
                            desc = JO.getString( "description" );
                            icon = JO.getString( "icon" );
                            String temp = String.valueOf( o_main.getDouble( "temp" ) );
                            String humidity = String.valueOf( o_main.getDouble( "humidity" ) );
                            String temp_min = String.valueOf( o_main.getDouble( "temp_min" ) );
                            String temp_max = String.valueOf( o_main.getDouble( "temp_max" ) );
                            if (icon.equals( "01d" )) imageView2.setImageResource( R.drawable.i01d );
                            if (icon.equals( "01n" )) imageView2.setImageResource( R.drawable.i01n );
                            if (icon.equals( "02d" )) imageView2.setImageResource( R.drawable.i02d );
                            if (icon.equals( "02n" )) imageView2.setImageResource( R.drawable.i02n );
                            if (icon.equals( "03d" )) imageView2.setImageResource( R.drawable.i03d );
                            if (icon.equals( "03n" )) imageView2.setImageResource( R.drawable.i03n );
                            if (icon.equals( "04d" )) imageView2.setImageResource( R.drawable.i04d );
                            if (icon.equals( "04n" )) imageView2.setImageResource( R.drawable.i04n );
                            if (icon.equals( "09d" )) imageView2.setImageResource( R.drawable.i09d );
                            if (icon.equals( "09n" )) imageView2.setImageResource( R.drawable.i09n );
                            if (icon.equals( "10d" )) imageView2.setImageResource( R.drawable.i10d );
                            if (icon.equals( "10n" )) imageView2.setImageResource( R.drawable.i10n );
                            if (icon.equals( "11d" )) imageView2.setImageResource( R.drawable.i11d );
                            if (icon.equals( "11n" )) imageView2.setImageResource( R.drawable.i11n );
                            if (icon.equals( "13d" )) imageView2.setImageResource( R.drawable.i13d );
                            if (icon.equals( "13n" )) imageView2.setImageResource( R.drawable.i13n );
                            if (icon.equals( "50d" )) imageView2.setImageResource( R.drawable.i50d );
                            if (icon.equals( "50n" )) imageView2.setImageResource( R.drawable.i50n );

                            city = response.getString( "name" );
                            toolbar.setTitle( city );
                            xdescription.setText( desc );
                            xhumidity.setText( "Nem: %" + humidity );

                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat( "EEEE-MM-dd" );
                            String formatted_date = sdf.format( calendar.getTime() );
                            xdate.setText( formatted_date );

                            double temp1 = Double.parseDouble( temp );
                            double centi = (temp1 - 32) / 1.8000;
                            centi = Math.round( centi );
                            a = (int) centi;
                            xtemp.setText( a + "°C" );

                            double temp_min1 = Double.parseDouble( temp_min );
                            double centi_min = (temp_min1 - 32) / 1.8000;
                            centi_min = Math.round( centi_min );
                            int a_min = (int) centi_min;
                            xtemp_min.setText( "En Düşük Sıcaklık:" + a_min + "°C" );

                            double temp_max1 = Double.parseDouble( temp_max );
                            double centi_max = (temp_max1 - 32) / 1.8000;
                            centi_max = Math.round( centi_max );
                            int a_max = (int) centi_max;
                            xtemp_max.setText( "En Yüksek Sıcaklık:" + a_max + "°C" );

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
        new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }

    }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }

   public void setSehirler(){
        String data;
        try {

            byte[] buffer;
            JSONArray jsonArray;
            InputStream is = getAssets().open( "city.list.json" );
                int size = is.available();
                buffer = new byte[size];
                is.read( buffer );
                is.close();

            data = new String(buffer,"UTF-8");
            jsonArray = new JSONArray( data );
            for(int i =0;i<jsonArray.length();i++){
                JSONObject JO = jsonArray.getJSONObject(i);
                sehirler.add(JO.getString("name") + ","+JO.getString("country"));
                sehirid.add(JO.getString( "id" ));
                city_url.add("http://api.openweathermap.org/data/2.5/weather?id="+JO.getString("id")+"&appid=b36a41e42bb3173c8632ba46d47d6132&units=Imperial");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }




    }














