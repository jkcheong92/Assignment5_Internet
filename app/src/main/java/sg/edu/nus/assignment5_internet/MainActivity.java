package sg.edu.nus.assignment5_internet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onClick_activity1 (View view) {
        Intent i = new Intent(this, Activity1.class);
        startActivity(i);
    }

    public void onClick_activity2 (View view) {
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
    }

    public void onClick_activity3 (View view) {
        Intent i = new Intent(this, Activity3.class);
        startActivity(i);
    }

    public void onClick_activity4 (View view) {
        Intent i = new Intent(this, Activity4.class);
        startActivity(i);
    }
}
