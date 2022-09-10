package com.example.myapplication.createmessagerule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.myapplication.database.MessageRuleDatabase
import com.example.myapplication.database.MessageRuleItem
import com.example.myapplication.databinding.CreateMessageRuleFragmentBinding
import com.example.myapplication.extensions.numberFormat
import com.example.myapplication.extensions.slug

class CreateMessageRuleFragment : Fragment() {

    private lateinit var binding: CreateMessageRuleFragmentBinding

    private val messageRuleDatabase by lazy {
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
        binding = CreateMessageRuleFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            btnBack.setOnClickListener {
                goBack()
            }

            btnSave.setOnClickListener {
                val from = txtEdtFrom.text.toString().slug().numberFormat()
                val to = txtEdtTo.text.toString().slug().numberFormat()
                val description = txtEdtDescription.text.toString()
                val item = MessageRuleItem(0, from, to, description)
                messageRuleDatabase.addItem(item)
                goBack()
            }
        }
    }

    private fun goBack() = findNavController().popBackStack()
}
