package sg.edu.nus.assignment5_internet;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
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

    // Start Asynchronous Task
    private class Activity2AsyncTask extends AsyncTask<String, Void, String> {
        // Do in Background
        @Override
        protected String doInBackground(String... params) {
            return params[0];
        }

        public void onPostExecute (String result) {
            Toast.makeText(getApplicationContext(), "in onPostExecute, string = " + result, Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_startAsyncTask(View view) {
        Activity2AsyncTask asyncTask = new Activity2AsyncTask();
        asyncTask.execute("This string is from doInBackground");
    }
}
