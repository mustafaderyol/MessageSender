package com.example.myapplication.messagerulelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.room.Room
import com.example.myapplication.database.MessageRuleDatabase
import com.example.myapplication.database.MessageRuleItem
import com.example.myapplication.database.MessageRuleItemDao
import com.example.myapplication.databinding.MessageRuleListFragmentBinding
import com.example.myapplication.messagerulelist.messageruleitem.MessageRuleItemAdapter
import com.example.myapplication.messagerulelist.messageruleitem.MessageRuleItemEventHandler

class MessageRuleListFragment : Fragment(), MessageRuleItemEventHandler {

    private val adapter by lazy {
        MessageRuleItemAdapter(this)
    }

    private lateinit var binding: MessageRuleListFragmentBinding

    private val messageRuleDatabase: MessageRuleItemDao by lazy {
        Room.databaseBuilder(requireContext(), MessageRuleDatabase::class.java, "message_rule_db")
            .allowMainThreadQueries()
            .build()
            .messageRuleItemDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MessageRuleListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL,
                )
            )
            fabCreateRule.setOnClickListener {
                findNavController().navigate(
                    MessageRuleListFragmentDirections.actionMessageRuleListFragmentToCreateMessageRuleFragment()
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateListData()
    }

    override fun onDeleteClicked(item: MessageRuleItem) {
        messageRuleDatabase.deleteItem(item)
        updateListData()
    }

    private fun updateListData() {
        adapter.submitList(messageRuleDatabase.getAll())
    }
}
