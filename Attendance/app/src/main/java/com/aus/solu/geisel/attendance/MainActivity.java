package com.aus.solu.geisel.attendance;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.net.URL;

import static android.R.attr.autoUrlDetect;
import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {
    private Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
       scan_btn.setOnClickListener(new View.OnClickListener() {
           @Override
      //main scanning operations are preformed here
           public void onClick(View v) {
               IntentIntegrator integrator = new IntentIntegrator(activity);
               integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
               integrator.setPrompt("Scan");
               integrator.setCameraId(0);
               integrator.setBeepEnabled(false);
               integrator.setBarcodeImageEnabled(false);
               integrator.initiateScan();
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this, "You Cancelled Scanning", Toast.LENGTH_LONG ).show();
            }
            else
            {
                //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG ).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getContents()));
                startActivity(browserIntent);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
