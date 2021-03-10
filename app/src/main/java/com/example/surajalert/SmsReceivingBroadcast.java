package com.example.surajalert;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceivingBroadcast extends BroadcastReceiver {
     String phonenum;
     SmsManager sms;
     @RequiresApi(api= Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b=intent.getExtras();
         SmsMessage msg[]=null;
         String str="";

         if(b!=null){
             Object[]pdus = (Object[])b.get("pdus");
             msg=new SmsMessage[pdus.length];
             for(int i=0;i<msg.length;i++){
                 msg[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                 str+=msg[i].getMessageBody().toString();
                 phonenum=msg[i].getOriginatingAddress().toString();

                 NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

                 Notification.Builder builder=new Notification.Builder(context);

                 builder.setSmallIcon(R.mipmap.ic_launcher);
                 builder.setContentTitle("SMS NOTIFICATION");
                 builder.setContentText("You have received a message");
                 builder.setSubText(str);
                 builder.setTicker("SMS");
                 builder.setAutoCancel(true);

                 Notification notification=builder.getNotification();
                 notificationManager.notify(R.mipmap.ic_launcher,notification);
                 Toast.makeText(context,"message:" + str + "phone:" + phonenum,Toast.LENGTH_LONG).show();
             }

         }
    }
}