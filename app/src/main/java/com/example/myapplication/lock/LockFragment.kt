package com.example.myapplication.lock

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.LockFragmentBinding

class LockFragment : Fragment() {

    private lateinit var binding: LockFragmentBinding
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LockFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(activity)
        val isPasswordValid = sharedPrefs.getBoolean("IS_PASSWORD_VALID", false)
        if(isPasswordValid) {
            navigate()
        }
        binding.run {
            btnLogin.setOnClickListener {
                val password = txtEdtPassword.text.toString()
                login(password)
            }
        }
    }

    private fun login(password: String) {
        if(password == "1916919404") {
            sharedPrefs.edit().putBoolean("IS_PASSWORD_VALID", true).apply()
            navigate()
        } else {
            activity?.finish()
        }
    }

    private fun navigate() {
        findNavController().navigate(LockFragmentDirections.actionLockFragmentToMessageRuleListFragment())
    }
}
