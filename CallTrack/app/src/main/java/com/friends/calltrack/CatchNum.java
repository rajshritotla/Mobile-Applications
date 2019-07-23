package com.friends.calltrack;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;
/**
 * Created by hp- on 21-10-2015.
 */
public class CatchNum {


    private class CallStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    // called when someone is ringing to this phone

                    Toast.makeText(ctx,
                            "Incoming: "+incomingNumber,
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }


    /*PhoneStateListener callStateListener = new PhoneStateListener() {
        public void onCallStateChanged(int state, String incomingNumber) {

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE :
                    callStateStr = “idle”; break;
                case TelephonyManager.CALL_STATE_OFFHOOK :
                    callStateStr = “offhook”; break;
                case TelephonyManager.CALL_STATE_RINGING :
                    callStateStr = “ringing. Incoming number is: “
                    + incomingNumber;
                    break;
                default : break;
            }
            Toast.makeText(MyActivity.this,
                    callStateStr, Toast.LENGTH_LONG).show();
        }
    };
    telephonyManager.listen(callStateListener,
    PhoneStateListener.LISTEN_CALL_STATE);*/
}
