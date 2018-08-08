package brunoeleodoro.callforcode.org.sender.firebase;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import brunoeleodoro.callforcode.org.sender.MainActivity;
import brunoeleodoro.callforcode.org.sender.R;
import brunoeleodoro.callforcode.org.sender.alert_mode.AlertModeActivity;

public class FirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try
        {
            Log.i("script", "firebase data="+remoteMessage.getData().toString());
            Intent intent = new Intent(this, AlertModeActivity.class);

            SharedPreferences.Editor editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
            editor.putString("desc", remoteMessage.getData().get("desc"));
            editor.putString("lat", remoteMessage.getData().get("lat"));
            editor.putString("lng", remoteMessage.getData().get("lng"));
            editor.apply();

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "0001")
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText("Clique aqui para mais informações")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(0001, mBuilder.build());
        }
        catch (Exception e)
        {
            Log.i("script","error firebase notification="+e);
        }

    }
}
