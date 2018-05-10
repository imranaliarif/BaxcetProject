package com.codiansoft.baxcetproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.codiansoft.baxcetproject.Adapters.SelectImageAdapter;
import com.codiansoft.baxcetproject.Models.ImageModel;
import com.codiansoft.baxcetproject.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.safety.audio_recorder.AudioListener;
import br.com.safety.audio_recorder.AudioRecordButton;
import br.com.safety.audio_recorder.RecordingItem;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AddOrderDetailsActivity extends AppCompatActivity {

    public static int images_uploaded = 0;

    RelativeLayout parent;
    ImageView add_image , play_audio;
    RecyclerView recyclerView;
    SelectImageAdapter selectImageAdapter;
    List<ImageModel> list;
    Activity activity;
    int parent_width=0;
    public static final int GALLERY_CONSTANT = 1;
//    ImageView microphone;
    private MediaRecorder myAudioRecorder;
    private File outputFile;
//    private AudioRecordButton audioRecordButton;
    File file;
    public MediaPlayer mMediaPlayer;
    SeekBar seekBar;
    Button btn_order_now;
    ImageView back;
    ImageView microphone;
    boolean recordingStarted=false;
    Random random;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    boolean audioRecorded=false;
    public static final int RequestPermissionCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_details);
        activity = this;

        add_image = (ImageView) findViewById(R.id.add_image);
        recyclerView = (RecyclerView) findViewById(R.id.images_recycleview);
        parent=(RelativeLayout)findViewById(R.id.parent);
//        audioRecordButton = (AudioRecordButton) findViewById(R.id.audio_record_button);
        play_audio=(ImageView)findViewById(R.id.play_audio);
        seekBar=(SeekBar)findViewById(R.id.media_seekbar);
        btn_order_now=(Button)findViewById(R.id.order_now);
        back=(ImageView) findViewById(R.id.back);
        microphone=(ImageView)findViewById(R.id.microphone);
        random = new Random();

        microphone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if(checkPermission()) {
                    recordingStarted=true;

                    File outputDir = getApplicationContext().getCacheDir(); // context being the Activity pointer
                    try {
                         outputFile = File.createTempFile(CreateRandomAudioFileName(5), ".3gp", outputDir);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    MediaRecorderReady();

                    try {
                        myAudioRecorder.prepare();
                        myAudioRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    Toast.makeText(activity, "Recording started",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                }

                return false;
            }
        });

        microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordingStarted)
                {
                    myAudioRecorder.stop();
                    Toast.makeText(activity , "Recording stopped" , Toast.LENGTH_SHORT).show();
                    Uri uri=Uri.fromFile(outputFile);
                mMediaPlayer=MediaPlayer.create(activity ,uri );
                seekBar.setMax(mMediaPlayer.getDuration());
                    audioRecorded=true;
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_right);
            }
        });


        btn_order_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity , CheckOutActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_right);

            }
        });


        list = new ArrayList<>();
        selectImageAdapter = new SelectImageAdapter(list, activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(selectImageAdapter);

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageFromGallery();
            }
        });



        final Handler mSeekbarUpdateHandler = new Handler();
        final Runnable mUpdateSeekbar = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mMediaPlayer.getCurrentPosition());
                mSeekbarUpdateHandler.postDelayed(this, 50);
            }
        };
        play_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audioRecorded)
                {
                    mMediaPlayer.start();
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);
                }
                else {
                    Toast.makeText(activity , "Please record audio first !" , Toast.LENGTH_SHORT).show();
                }


            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if (fromUser)
                    mMediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }
    public void MediaRecorderReady(){
        myAudioRecorder =new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile.getPath());
    }
    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }
    public void selectImageFromGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galleryIntent, GALLERY_CONSTANT);
    }
    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(activity, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(activity,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_CONSTANT && resultCode == RESULT_OK) {

            //TODO: action
            Uri filePath = data.getData();
            try {
                if((recyclerView.getWidth()+ 300 ) >= parent.getWidth() )
                {
                    Log.e("condition" , recyclerView.getWidth()+"");
                    ViewGroup.LayoutParams params=recyclerView.getLayoutParams();
                    params.width=recyclerView.getWidth();
                    recyclerView.setLayoutParams(params);
                }
                ImageModel model = new ImageModel();
                model.setUri(filePath);
                list.add(model);

                selectImageAdapter.notifyDataSetChanged();


            } catch (Exception e) {

            }

        }

    }
}
