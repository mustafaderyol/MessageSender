package com.example.myapplication.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.widget.Toast
import androidx.room.Room
import com.example.myapplication.database.MessageRuleDatabase
import com.example.myapplication.extensions.numberFormat
import com.example.myapplication.extensions.slug

class SmsReaderService : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show()

        intent.extras?.let { data ->
            val pdus = data["pdus"] as? Array<Any>?

            var sender: String? = null
            val messageList = ArrayList<String>()
            for (i in pdus!!.indices) {
                val smsMessage: SmsMessage = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                sender = smsMessage.displayOriginatingAddress.slug().numberFormat()
                messageList.add(smsMessage.messageBody)
            }
            Toast.makeText(context, "sender : $sender", Toast.LENGTH_SHORT).show()
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

        database.getBySender(sender).forEach {
            Toast.makeText(context, "found rule : $it", Toast.LENGTH_SHORT).show()
            val smsManager = context.getSystemService(SmsManager::class.java)
            smsManager.sendMultipartTextMessage(
                "0" + it.to,
                null,
                messages,
                null,
                null,
            )
        }
    }
}
