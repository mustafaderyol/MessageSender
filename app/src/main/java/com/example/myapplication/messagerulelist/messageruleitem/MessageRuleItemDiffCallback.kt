package com.example.myapplication.messagerulelist.messageruleitem

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.database.MessageRuleItem

class MessageRuleItemDiffCallback : DiffUtil.ItemCallback<MessageRuleItem>() {

    override fun areItemsTheSame(oldItem: MessageRuleItem, newItem: MessageRuleItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessageRuleItem, newItem: MessageRuleItem): Boolean {
        return oldItem == newItem
    }
}
