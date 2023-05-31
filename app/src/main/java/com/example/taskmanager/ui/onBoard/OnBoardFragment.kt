package com.example.taskmanager.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentOnBoardBinding
import com.example.taskmanager.ui.home.adapter.OnBoardingAdapter

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var pref: Pref
    private val adapter = OnBoardingAdapter(this::onStartClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)
    }

    private fun onStartClick() {
        pref.userSeen()
        findNavController().navigateUp()
    }
}