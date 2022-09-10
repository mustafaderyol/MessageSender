package com.example.myapplication.messagerulelist.messageruleitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.database.MessageRuleItem
import com.example.myapplication.databinding.MessageRuleItemBinding

class MessageRuleItemAdapter(
    private val eventHandler: MessageRuleItemEventHandler,
) : ListAdapter<MessageRuleItem, MessageRuleItemViewHolder>(
    MessageRuleItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageRuleItemViewHolder {
        val binding = MessageRuleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return MessageRuleItemViewHolder(binding, eventHandler)
    }

    override fun onBindViewHolder(holder: MessageRuleItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
