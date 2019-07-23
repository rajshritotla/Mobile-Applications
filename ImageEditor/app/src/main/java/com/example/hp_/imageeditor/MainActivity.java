package com.example.hp_.imageeditor;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView iclick;
    Button bclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bclick = (Button) findViewById(R.id.button);
        iclick = (ImageView) findViewById(R.id.imageView);

        /*Disable the button if the user has no camera
        //if (!hasCamera())
          //  bclick.setEnabled(false);
        //else
        */
        bclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera(v);

            }
        });
    }

    //Check if the user has a camera
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }



    //Launching the camera
    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");

            Bitmap newphoto = invertimage(photo);
            iclick.setImageBitmap(newphoto);

         /*   Intent send=new Intent(MainActivity.this,EditImg.class);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 50, bs);
            send.putExtra("byteArray", bs.toByteArray());
            startActivity(send);
*/






           /* Intent send=new Intent(MainActivity.this,EditImg.class);
            send.putExtra("byteArray",photo);
            startActivity(send);*/
        }


    }

    public static Bitmap invertimage(Bitmap Original) {
        Bitmap finalImage = Bitmap.createBitmap(Original.getWidth(), Original.getHeight(), Original.getConfig());
        int A, R, G, B;
        int pixelColor;
        int height = Original.getHeight();
        int width = Original.getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = Original.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                finalImage.setPixel(x, y, Color.argb(A, R, G, B));

            }
        }
        return finalImage;
    }

    }




