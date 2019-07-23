package com.example.hp_.koolcam;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView iclick;
    Button bclick;
    Button bdisco;
    Button bshiny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bclick = (Button) findViewById(R.id.button);
        iclick = (ImageView) findViewById(R.id.imageView);
        iclick.setImageDrawable(getResources().getDrawable(R.drawable.iv));
        /*Disable the button if the user has no camera
        if (!hasCamera())
             bclick.setEnabled(false);
        else
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

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Get the photo
            Bundle extras = data.getExtras();
            final Bitmap photo = (Bitmap) extras.get("data");
            iclick.setImageBitmap(photo);
            bdisco = (Button) findViewById(R.id.button2);
            bshiny = (Button) findViewById(R.id.button3);


            bdisco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(photo==null)
                    {
                        iclick.setImageDrawable(getResources().getDrawable(R.drawable.iv));

                    }
                    Bitmap newphoto = invertimage(photo);
                    iclick.setImageBitmap(newphoto);
                    iclick.setVisibility(View.VISIBLE);
                    MediaStore.Images.Media.insertImage(getContentResolver(),newphoto,"title","description");
                }
            });

            bshiny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(photo==null)
                    {
                        iclick.setImageDrawable(getResources().getDrawable(R.drawable.iv));

                    }

                    Drawable[] layers = new Drawable[2];
                    layers[1] = new BitmapDrawable(getResources(), photo);
                    layers[0] = getResources().getDrawable(R.drawable.effect1);
                    LayerDrawable newphoto = new LayerDrawable(layers);
                    iclick.setImageDrawable(newphoto);
                    Bitmap b = Bitmap.createBitmap(newphoto.getIntrinsicWidth(), newphoto.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    newphoto.setBounds(0, 0, newphoto.getIntrinsicWidth(), newphoto.getIntrinsicHeight());
                    newphoto.draw(new Canvas(b));
                    MediaStore.Images.Media.insertImage(getContentResolver(), b , "title", "description");

                }
            });


        }

}

    //Invert a bitmap image

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

/*

    public static Bitmap grayimage(Bitmap Original) {
        Bitmap finalImage = Bitmap.createBitmap(Original.getWidth(), Original.getHeight(), Original.getConfig());
        double A, R, G, B;
        int pixelColor;
        int height = Original.getHeight();
        int width = Original.getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = Original.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = (3 * Color.red(pixelColor))/100;
                G = (59 * Color.green(pixelColor))/100;
                B = (0.11 * Color.blue(pixelColor))/100;
                finalImage.setPixel(x, y, Color.argb(A, R, G, B));

            }
        }
        return finalImage;
    }
*/



}