package app.gopiwan.gopiwanapp;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiConfiguration;
import android.provider.Settings;
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
            //sb.append(connexion.toString());

            if (connexion.SSID.equalsIgnoreCase("GoPiWan"))
            {
                sb.append("Le robot a été trouvé. Connexion en cours...");
                WifiConfiguration wc = new WifiConfiguration();

                wc.SSID = "\""+connexion.SSID+"\"";
                wc.BSSID = connexion.BSSID;
                wc.status = WifiConfiguration.Status.ENABLED;
                wc.hiddenSSID = false;
                wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

                int netID = wifi.addNetwork(wc); // add network

                if(wifi.enableNetwork(netID, true)) // enable network, but cannot work???????????
                {
                    sb = new StringBuilder();
                    sb.append("Connecté");
                }
            }
        }

        TextView listConnexion = (TextView) rechercheWifi.findViewById(R.id.textRechercheWifi);
        listConnexion.setText(sb);
    }

}
