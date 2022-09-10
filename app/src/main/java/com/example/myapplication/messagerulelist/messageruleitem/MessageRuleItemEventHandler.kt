package com.example.myapplication.messagerulelist.messageruleitem

import com.example.myapplication.database.MessageRuleItem

interface MessageRuleItemEventHandler {

    fun onDeleteClicked(item: MessageRuleItem)
}
