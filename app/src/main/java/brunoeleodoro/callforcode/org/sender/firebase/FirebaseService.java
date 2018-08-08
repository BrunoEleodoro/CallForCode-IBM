package brunoeleodoro.callforcode.org.sender.firebase;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseService extends FirebaseInstanceIdService {
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("script","started firebase");
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
