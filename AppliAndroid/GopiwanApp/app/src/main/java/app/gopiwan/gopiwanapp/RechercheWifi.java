package app.gopiwan.gopiwanapp;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.content.Context;


public class RechercheWifi extends ActionBarActivity {

    private WifiManager wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_wifi);

        // On récupère le service gérant la wifi
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        // Test si la wifi est activé ou non
        if (!this.wifi.isWifiEnabled()) {
            wifi.setWifiEnabled(true);
            Toast.makeText(getApplicationContext(), "La wifi a étée automatiquement activée.", Toast.LENGTH_LONG).show();
        }

        WifiReceiver receiver = new WifiReceiver(wifi, this);

        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifi.startScan();

        // Récupère le bouton "boutonControle"
        Button b = (Button) this.findViewById(R.id.boutonControle);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ces deux lignes permettent de changer d'activités, c'est à dire de vue RechercheWifi vers Controles
                Intent intent = new Intent(RechercheWifi.this, Controles.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recherche_wifi, menu);
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
}
