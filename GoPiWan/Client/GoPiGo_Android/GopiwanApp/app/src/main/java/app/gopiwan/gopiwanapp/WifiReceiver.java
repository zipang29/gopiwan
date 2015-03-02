package app.gopiwan.gopiwanapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Guillaume on 01/03/2015.
 */
public class WifiReceiver extends BroadcastReceiver {

    private StringBuilder sb;

    private List<ScanResult> wifiList;

    private WifiManager wifi;

    private RechercheWifi rechercheWifi;

    public WifiReceiver(WifiManager wifi, RechercheWifi rechercheWifi) {
        this.wifi = wifi;
        this.rechercheWifi = rechercheWifi;
    }

    public void onReceive(Context c, Intent intent) {
        sb = new StringBuilder();
        wifiList = this.wifi.getScanResults();
        sb.append("Nombre de connexion trouvées : " + wifiList.size());

        for (ScanResult connexion : wifiList) {
            sb.append(connexion.toString());
        }

        TextView listConnexion = (TextView) rechercheWifi.findViewById(R.id.textRechercheWifi);
        listConnexion.setText(sb);
    }

}
