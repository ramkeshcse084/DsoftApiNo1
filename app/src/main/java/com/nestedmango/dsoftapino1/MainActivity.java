package com.nestedmango.dsoftapino1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<CityPlace> cityList;
    ListView listview;

    CityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.list);
        cityList = new ArrayList<CityPlace>();
        new CityAsyntask().execute("http://api.modinagarmycity.com/api/Category");


        adapter = new CityAdapter(getApplicationContext(), R.layout.row, cityList);

        listview.setAdapter(adapter);



    }




    public class CityAsyntask extends AsyncTask<String, String, List<CityPlace>> {

        @Override
        protected List<CityPlace> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            URL url = null;
            try {
                url = new URL((String) params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }
                String data = buffer.toString();
                // JSONObject jsono = new JSONObject(data);
                JSONArray jarray = new JSONArray(data);

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject object = jarray.getJSONObject(i);

                    CityPlace actor = new CityPlace();

                    actor.setName(object.getString("NAME"));
                    actor.setCretedone(object.getString("CREATEDON"));
                    actor.setId(object.getString("ID"));
                    actor.setStatus(object.getString("STATUS"));
                    actor.setImage(object.getString("IMAGEURL"));

                    cityList.add(actor);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }


        @Override
        protected void onPostExecute(List<CityPlace> result) {
            super.onPostExecute(result);

            CityAdapter adapter = new CityAdapter(getApplication(), R.layout.row, cityList);
            listview.setAdapter(adapter);
        }
    }
}
