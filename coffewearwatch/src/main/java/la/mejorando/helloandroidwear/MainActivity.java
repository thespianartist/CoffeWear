package la.mejorando.helloandroidwear;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.wearable.DataMap;
import com.mariux.teleport.lib.TeleportClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity{


    private static final int SPEECH_REQUEST_CODE = 0;
    public ImageView bean;
    TeleportClient mTeleportClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTeleportClient = new TeleportClient(getApplicationContext());


        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                bean = (ImageView) findViewById(R.id.listen_bean);
                bean.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("aiqjgygd8u97t87yuguhivbjhknbm sas","jsakljsa");
                        //displaySpeechRecognizer();
                        //sendData();
                        mTeleportClient.setOnSyncDataItemTask(new ShowToastHelloWorldTask());

                    }
                });

            }
        });
    }

    private void displaySpeechRecognizer() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {

            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            Log.e("hola", spokenText);
            Toast.makeText(getApplicationContext(),spokenText,Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTeleportClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTeleportClient.disconnect();
    }

    public class ShowToastHelloWorldTask extends TeleportClient.OnSyncDataItemTask {

        @Override
        protected void onPostExecute(DataMap dataMap) {

            String hello = dataMap.getString("hello");
            Log.e("asas","jsakljsa");
            Toast.makeText(getApplicationContext(),hello,Toast.LENGTH_SHORT).show();
        }
    }





}
