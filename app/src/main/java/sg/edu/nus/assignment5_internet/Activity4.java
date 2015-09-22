package sg.edu.nus.assignment5_internet;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Activity4 extends AppCompatActivity {

    private static String convertedCurrency = null;
    private static String baseCurrency = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class Activity4AsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return getJSON(params[0]);
        }

        // TODO:
        public void onPostExecute (String result) {
            try {
                // TODO: Convert currency using JSON
                JSONObject myJSONObject = new JSONObject(result);
                JSONObject ratesObj = myJSONObject.getJSONObject("rates");
                String rate = ratesObj.getString(convertedCurrency);
                String msg = "1 " + baseCurrency + " = " + rate + " " + convertedCurrency;
                // Toast.makeText(getApplicationContext(), "1 " + baseCurrency + " = " + rate + " " + convertedCurrency, Toast.LENGTH_LONG).show();

                // TODO: SetTextView for result
                TextView resultTxtView = (TextView) findViewById(R.id.result);
                resultTxtView.setText(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO:
    public void onClick_convertCurrency(View view) {
        // TODO: Convert currency to uppercase
        TextView baseTxtView = (TextView) findViewById(R.id.baseCurrency);
        TextView convertedTxtView = (TextView) findViewById(R.id.convertedCurrency);
        baseCurrency = baseTxtView.getText().toString().toUpperCase();
        convertedCurrency = convertedTxtView.getText().toString().toUpperCase();

        // TODO: Call Async Tasks and pass in currencies
        Activity4AsyncTask asyncTask4 = new Activity4AsyncTask();
        asyncTask4.execute("http://api.fixer.io/latest?base=" + baseCurrency, convertedCurrency);
    }

    // the following code convert string to url
    private URL ConvertToUrl(String urlStr) {
        try {
            URL url = new URL(urlStr);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(),
                    url.getHost(), url.getPort(), url.getPath(),
                    url.getQuery(), url.getRef());
            url = uri.toURL();
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getJSON(String inputStr) {
        URL url = ConvertToUrl(inputStr);
        HttpURLConnection httpURLConnection = null;
        int responseCode = -1;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            responseCode = httpURLConnection.getResponseCode();
            if (responseCode == httpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();

        } finally {
            httpURLConnection.disconnect();
        }

        return stringBuilder.toString();
    }
}
