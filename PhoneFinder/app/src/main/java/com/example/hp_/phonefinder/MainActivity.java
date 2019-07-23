package com.example.hp_.phonefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.SmsMessage;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.FilterOutputStream;

public class MainActivity extends AppCompatActivity {

    //Switch on;
    String myMsg="phone";
    ToggleButton bon;
    TextView t;
    int f=0;
    String msgBody = null ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bon=(ToggleButton)findViewById(R.id.toggleButton1);
        t=(TextView)findViewById(R.id.textView1);
        //set the switch to ON
        //bon.setChecked(true);
        t.setText("1");
        bon.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked==true)
                {
                    //readmsg();
                    t.setText("True");
                    //SmsListener.onReceive(getApplicationContext(),null);



                    class SmsListener extends BroadcastReceiver{

                        private SharedPreferences preferences;

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            // TODO Auto-generated method stub

                            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                                Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
                                SmsMessage[] msgs = null;

                                if (bundle != null){
                                    //---retrieve the SMS message received---
                                    try{
                                        Object[] pdus = (Object[]) bundle.get("pdus");
                                        msgs = new SmsMessage[pdus.length];
                                        for(int i=0; i<msgs.length; i++){
                                            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                                            msgBody = msgs[i].getMessageBody();
                                        }

                                    }catch(Exception e){
//				                            Log.d("Exception caught",e.getMessage());
                                    }
                                }
                            }
                        }
                    }









                    int match=readmsg();
                    if(match==1)
                    {
                        try {
                            AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                            //int currentVolume = audio.getStreamVolume(AudioManager.STREAM_RING);
                            int max = audio.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
                            audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                            audio.setStreamVolume(AudioManager.STREAM_RING, max, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                            r.play();
                            r.wait(100);
                            r.stop();

                        }
                        catch(Exception e){
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG);
                        }
                    }



                    else
                    {
                        Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG);
                    t.setText("Error");
                    bon.setChecked(true);

                }

            }});

    }

    @SuppressLint("ShowToast")
        //int readmsg()
	/*int readmsg()
	{

			 // int f=0;
			  Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null,null, null, null);
			   cursor.moveToFirst();
SmsMessage sms = null;
String  msg_body=null;
String s = null;
			   do{
			   String msgData = "";
			   for(int idx=0;idx<cursor.getColumnCount();idx++)
			   {
			       msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
			      // t.setText(msgData);



			   }
			   msg_body =  cursor.getString(cursor.getColumnIndexOrThrow("body")).toString();
			   t.setText(msg_body);

			   if(msg_body.contains(myMsg))
			   {
				  // t.setTag(myMsg);
				   f=1;
				   break;

			   }

			   }while(cursor.moveToNext());

			   return f;
	}	*/

    int readmsg()
    {
        if(msgBody.contains(myMsg))
        {
            // t.setTag(myMsg);
            f=1;
        }
        return f;

    }
		 /* int check(String msgData)
  	    {
  	    	if(msgData.contains(myMsg))
  	    	       return 1;
  	    	else
  	    		return 0;
  	    }
*/








}
