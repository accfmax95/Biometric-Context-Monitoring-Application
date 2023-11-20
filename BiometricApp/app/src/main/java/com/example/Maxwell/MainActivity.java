package com.example.Maxwell;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnNxt, btnResp, btnHeart;
    int heartRateCount = 0;
    VideoView videoView;
    TextView viewResp;
    private Uri videoUri;
    Intent videoIntent;
    DataBaseModel myDB = new DataBaseModel();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewResp = (TextView) findViewById(R.id.viewResp);
        videoView = findViewById(R.id.videoView);

        btnNxt = findViewById(R.id.sympButton);
        btnResp = findViewById(R.id.respButton);
        btnHeart = findViewById(R.id.heartButton);

        buttonClick();
        measureResp();
        measureHR();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {

            Uri VideoUri = data.getData();

            String sampleUri = VideoUri.toString();
            videoUri = Uri.parse(sampleUri);
            videoView.setVideoURI(videoUri);
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus();
            videoView.start();

            Toast.makeText(getApplicationContext(), "Calculating Heart Rate...", Toast.LENGTH_LONG).show();
            TextView viewHeart = findViewById(R.id.viewHeart);
            viewHeart.setText("Measured Heart Rate: Calculating....");

            HRCalc myHRCalc = new HRCalc();
            myHRCalc.execute(videoUri);
        }
    }

    private BroadcastReceiver bReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {

            String str = (String) intent.getExtras().get("success").toString();
            myDB.setRESPIRATORY_RATE(Integer.parseInt(str));
            viewResp.setText("Measured Respiratory Rate: " +str);
        }
    };

    protected void onResume() {

        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(bReceiver, new IntentFilter("message"));
    }

    protected void onPause () {

        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bReceiver);
    }
    public void buttonClick() {

        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent symptomsIntent = new Intent(getApplicationContext(), SymptomsScreen.class);
                symptomsIntent.putExtra("myDBObject", myDB);
                startActivity(symptomsIntent);
            }
        });
    }
    public void measureResp() {

        btnResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respiratoryIntentService = new Intent(getApplicationContext(),RespiratoryService.class);
                startService(respiratoryIntentService);
                viewResp.setText("Measured Respiratory Rate: Calculating....");
            }
        });
    }
    public void measureHR() {

        btnHeart.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 45);
                videoIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                videoIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED) {
                            if (videoIntent.resolveActivity(getPackageManager()) != null) {

                                startActivityForResult(videoIntent, 101);
                            }
                        } else {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                            if (videoIntent.resolveActivity(getPackageManager()) != null) {

                                startActivityForResult(videoIntent, 101);
                            }
                        }
                    } else {

                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 102);
                    }
                } else {

                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 103);
                }

            }
        });
    }

    public class HRCalc extends AsyncTask<Uri, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.R)
        @Override
        protected Void doInBackground(Uri... uris) {
            Bitmap m_bitmap = null;
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            List<Bitmap> frameList = new ArrayList<>();
            try {

                retriever.setDataSource(getApplicationContext(), videoUri);
                String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_FRAME_COUNT);
                int aduration = Integer.parseInt(duration);
                int i = 10;
                while (i < aduration) {
                    Bitmap bitmap = retriever.getFrameAtIndex(i);
                    frameList.add(bitmap);
                    i += 5;
                }
            } catch (Exception m_e) {
            } finally {
                retriever.release();
                long redBucket = 0;
                long pixelCount = 0;
                List<Long> a = new ArrayList<>();
                for (Bitmap i : frameList) {
                    redBucket = 0;
                    for (int y = 550; y < 650; y++) {
                        for (int x = 550; x < 650; x++) {
                            int c = i.getPixel(x, y);
                            pixelCount++;
                            redBucket += Color.red(c) + Color.blue(c) + Color.green(c);
                        }
                    }
                    a.add(redBucket);
                }
                List<Long> b = new ArrayList<>();
                for (int i = 0; i < a.size() - 5; i++) {
                    long temp = (a.get(i) + a.get(i + 1) + a.get(i + 2) + a.get(i + 3) + a.get(i + 4)) / 4;
                    b.add(temp);
                }
                long x = b.get(0);
                int count = 0;
                for (int i = 1; i < b.size() - 1; i++) {
                    long p = b.get(i);
                    if (p > x) {

                        count++;
                    }
                }

                heartRateCount = count;
                Log.d("HeartRateCalculatorTask", "Sending heart rate" + heartRateCount);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            TextView heartRateValue = findViewById(R.id.viewHeart);
            heartRateValue.setText("Measured Heart Rate: " + String.valueOf(heartRateCount));
            myDB.setHEART_RATE(heartRateCount);
        }
    }
}