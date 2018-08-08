package brunoeleodoro.callforcode.org.sender.alert_mode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import brunoeleodoro.callforcode.org.sender.MainActivity;
import brunoeleodoro.callforcode.org.sender.R;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AlertModeActivity extends AppCompatActivity {
    String desc;
    String lat;
    String lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_mode);


        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        desc = prefs.getString("desc", null);
        lat = prefs.getString("lat", null);
        lng = prefs.getString("lng", null);


        new AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage(desc)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();


    }

    public void car_click(View view)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="+lat+","+lng+"&mode=d"));
        startActivity(intent);
    }

    public void ape_click(View view)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="+lat+","+lng+"&mode=w"));
        startActivity(intent);
    }
}
