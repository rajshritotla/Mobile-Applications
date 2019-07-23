package com.friends.calltrack;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.widget.ToggleButton;
import android.content.Intent;
import android.content.*;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity implements LocationListener{

    ToggleButton tb;

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb=(ToggleButton)findViewById(R.id.toggleButton);
        final String[] finalAddress = new String[1];
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //i = new Intent(getApplicationContext(), CatchNum.class);

                        class PhoneStateChangedReceiver extends BroadcastReceiver {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                            if (phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                                String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                               // Toast.makeText(context,“Incoming Call From:“ + phoneNumber,Toast.LENGTH_LONG).show();


                                LocationManager locationManager;
                                LocationListener locationListener;
                                String latitude,longitude;

                                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, phoneNumber);


                                public void onLocationChanged(Location location)
                                {
                              //      txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());


                                 Geocoder geocoder=new Geocoder(getApplicationContext(),Locale.ENGLISH);
                               // Geocoder geocoder = new Geocoder(this,Locale.ENGLISH);

                                try {
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                    if(addresses != null) {
                                        Address returnedAddress = addresses.get(0);
                                        StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
                                        for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                                        }
                                        finalAddress[0] =strReturnedAddress.toString();
                                    }
                                    else
                                    {
                                        finalAddress[0] ="No Address returned!";
                                    }
                                }
                                catch (IOException e) {
                                    e.printStackTrace();
                                    finalAddress[0] ="Can not get Address!";
                                }

                            }




                            }

                            }
                        }
                    }


                } else {
                    // The toggle is disabled
                }

        }})

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

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
