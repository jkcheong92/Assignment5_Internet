package sg.edu.nus.assignment5_internet;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Activity1 extends Activity implements
        ItemFragment.OnFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener {

    // Attach ItemFragment and BlankFragment dynamically
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        // Add ItemFragment to frag11
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment11 = new ItemFragment();
        fragmentTransaction.add(R.id.frag11, fragment11);
        fragmentTransaction.commit();

        // Add BlankFragment to frag12
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment12 = new BlankFragment();
        fragmentTransaction.add(R.id.frag12, fragment12);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity1, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }

    // JK: Pass data from ItemFragment to BlankFragment
    @Override
    public void onFragmentInteraction(String key) {
        String msg = "Classification = " + key;
        BlankFragment frag12 = (BlankFragment) fragmentManager.findFragmentById(R.id.frag12);
        frag12.setText(msg);
    }
}
