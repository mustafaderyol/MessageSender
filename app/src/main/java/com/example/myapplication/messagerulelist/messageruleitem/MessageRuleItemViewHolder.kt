package com.example.myapplication.messagerulelist.messageruleitem

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.MessageRuleItem
import com.example.myapplication.databinding.MessageRuleItemBinding

class MessageRuleItemViewHolder(
    private val binding: MessageRuleItemBinding,
    private val eventHandler: MessageRuleItemEventHandler,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: MessageRuleItem) {
        binding.run {
            txtFrom.text = root.context.getString(R.string.message_from, item.from)
            txtTo.text = root.context.getString(R.string.message_to, item.to)
            txtDescription.text = item.description
            imgDelete.setOnClickListener {
                eventHandler.onDeleteClicked(item)
            }
        }
    }
}
