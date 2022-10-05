package com.example.myapplication.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsManager
import android.telephony.SmsMessage
import androidx.room.Room
import com.example.myapplication.database.MessageRuleDatabase
import com.example.myapplication.extensions.numberFormat
import com.example.myapplication.extensions.slug

class SmsReaderService : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.extras?.let { data ->
            val pdus = data["pdus"] as? Array<Any>?

            var sender: String? = null
            val messageList = ArrayList<String>()
            for (i in pdus!!.indices) {
                val smsMessage: SmsMessage = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                sender = smsMessage.displayOriginatingAddress.slug().numberFormat()
                messageList.add(smsMessage.messageBody)
            }
            sender?.let {
                sendMessage(context, sender, messageList)
            }
        }
    }

    private fun sendMessage(context: Context, sender: String, messages: ArrayList<String>) {
        val database =
            Room.databaseBuilder(context, MessageRuleDatabase::class.java, "message_rule_db")
                .allowMainThreadQueries()
                .build()
                .messageRuleItemDao()

        database.getBySender(sender).forEach { sndr ->
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                context.getSystemService(SmsManager::class.java)
            } else {
                SmsManager.getDefault()
            }
            messages.forEach { message->
                smsManager.sendTextMessage(
                    "0" + sndr.to,
                    null,
                    message,
                    null,
                    null
                )
            }
        }
    }
}
