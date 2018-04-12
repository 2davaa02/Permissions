package com.example.a2davaa02.permissions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener ,View.OnClickListener{
    LocationManager mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button grant=(Button)findViewById(R.id.grant),
                start=(Button)findViewById(R.id.start);

        grant.setOnClickListener(this);
        start.setOnClickListener(this);

        mgr=(LocationManager)getSystemService(Context.LOCATION_SERVICE);


    }
    public void onClick(View view)
    {
        if(view.getId()==R.id.grant){
            requestPermissions(new String[] {Manifest.permission. ACCESS_FINE_LOCATION }, 0);

        }else if(view.getId()==R.id.start)
        {
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
            }
            else{
            new AlertDialog.Builder(this).setPositiveButton("OK", null).
                    setMessage("No access to location").show();
            }

        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode) {
            case 0:
                if(grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    new AlertDialog.Builder(this).setPositiveButton("OK", null).
                            setMessage("Now you can access location").show();

                } else {
                    new AlertDialog.Builder(this).setPositiveButton("OK", null).
                            setMessage("Location permission denied").show();
                }
                break;
        }
    }


    public void onLocationChanged(Location newLoc) {
        TextView lon=(TextView)findViewById(R.id.lon);
        TextView lat=(TextView)findViewById(R.id.lat);

        lat.setText(String.valueOf(newLoc.getLatitude()));
        lon.setText(String.valueOf(newLoc.getLongitude()));

    }
    public void onProviderEnabled(String provider) {

    }
    public void onProviderDisabled(String provider) {

    }
    public void onStatusChanged(String provider,int status,Bundle b) {

    }




}
