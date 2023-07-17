package com.example.lab2;
//media 6
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (int i = 0; i < messages.length; i++) {
            SmsMessage currentMessage = messages[i];
            String senderNum = currentMessage.getDisplayOriginatingAddress();
            String message = currentMessage.getDisplayMessageBody();
            Toast.makeText(context, "Sender: " + senderNum + ", message: " + message, Toast.LENGTH_LONG).show();

            String[] components = message.split("\\|");
            String id = components[0];
            String title = components[1];
            String isbn = components[2];
            String author = components[3];
            String description = components[4];
            String price = components[5];
            Boolean addprice = Boolean.parseBoolean(components[6]);

            Intent myintent = new Intent();
            myintent.setAction("mySMS");
            myintent.setAction("mySMS");
            myintent.putExtra("ID", id);
            myintent.putExtra("TITLE", title);
            myintent.putExtra("ISBN", isbn);
            myintent.putExtra("AUTHOR", author);
            myintent.putExtra("DESCRIPTION", description);
            myintent.putExtra("PRICE", price);
            myintent.putExtra("ADDPRICE", addprice);
            myintent.putExtra("KEY1", message);
            context.sendBroadcast(myintent);
        }
    }
}
