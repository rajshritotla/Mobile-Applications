package com.example.hp_.imageeditor;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;


public class EditImg extends AppCompatActivity {

    ImageView iv = null;
    Drawable dshiny;
    Button bdisco;
    Button bshiny;
    Bitmap bitmapImage = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_img);

        bdisco = (Button) findViewById(R.id.button2);
        bshiny = (Button) findViewById(R.id.button3);
        iv = (ImageView) findViewById(R.id.imageView2);

        if (getIntent().hasExtra("byteArray")) {
            //ImageView imv = new ImageView(this);
            bitmapImage = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
            iv.setImageBitmap(bitmapImage);

        }

        //buckysface=dshiny
        //bitmapImage=bitmapImage
        //iv=iv

        //dshiny = getResources().getDrawable(R.drawable.bucky,null);
        //bitmapImage = ((BitmapDrawable)dshiny).getBitmap();

        /*if (bdisco.isPressed()) //check...........
        {
            Bitmap newphoto = invertimage(bitmapImage);
            iv.setImageBitmap(newphoto);
        } else if (bshiny.isPressed()) //check.........................
        {
            //Drawable d = new BitmapDrawable(getResources(), bitmapImage);
            Drawable[] layers = new Drawable[2];
            //layers[0]=getResources().getDrawable(R.drawable.bucky);...........my img
            //layers[0]=Drawable(bitmapImage);//...............
            //layers[0]= d;
            layers[0] = new BitmapDrawable(getResources(), bitmapImage);
            layers[1] = getResources().getDrawable(R.drawable.effect1);
            LayerDrawable newphoto = new LayerDrawable(layers);
            iv.setImageDrawable(newphoto);


           ..........................
            Drawable layer=getResources().getDrawable(R.drawable.effect1);
            LayerDrawable newphoto=new LayerDrawable(layer,bitmapImage);
            BitmapDrawable bd=new BitmapDrawable(getResources().getDrawable(R.drawable.effect1),bitmapImage);
            iv.setImageDrawable(newphoto);
            ............................


        }*/

        bdisco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap newphoto = invertimage(bitmapImage);
                iv.setImageBitmap(newphoto);
                iv.setVisibility(View.VISIBLE);
            }
        });

        bshiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable[] layers = new Drawable[2];
                layers[0] = new BitmapDrawable(getResources(), bitmapImage);
                layers[1] = getResources().getDrawable(R.drawable.effect1);
                LayerDrawable newphoto = new LayerDrawable(layers);
                iv.setImageDrawable(newphoto);

            }
        });

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
    }