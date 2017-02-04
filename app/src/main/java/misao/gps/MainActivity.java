package misao.gps;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button getLocation;

    private static final int REQUEST_CODE_PERMISSION = 2;
    GPSTracker gps;

    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if(ActivityCompat.checkSelfPermission(this,mPermission) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{mPermission},REQUEST_CODE_PERMISSION);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        getLocation = (Button)findViewById(R.id.getLocation);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps = new GPSTracker(MainActivity.this);

                if(gps.canGetLocation())
                {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(),"Ypur Location is  - \nLat : "
                            + latitude + "\nLong : " + longitude, Toast.LENGTH_LONG).show();
                }
                else
                {
                    gps.showSettingAlert();
                }
            }
        });
    }
}
