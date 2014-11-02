package la.mejorando.coffewearphone;

import android.app.Activity;
import android.os.Bundle;

import com.mariux.teleport.lib.TeleportClient;


public class SendPetition extends Activity {

    TeleportClient mTeleportClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_petition);
    }



}
