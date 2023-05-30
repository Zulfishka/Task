package com.example.taskmanager.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.utils.loadImage

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private val REQUEST_IMAGE = 1
    private var selectedImage: Uri? = null

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val photoUri = result.data?.data.toString()
            pref.saveImage(photoUri)
            binding.ivProfile.loadImage(photoUri)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        saveClick()
        binding.etProfile.setText(pref.getName())
        pressCircleImage()
        binding.ivProfile.loadImage(pref.getImage().toString())
    }

    private fun saveClick() {
        with(binding) {
            bottomProfile.setOnClickListener {
                pref.saveName(binding.etProfile.text.toString())
                pref.saveImage(selectedImage.toString())

            }
        }
    }

    private fun pressCircleImage() {
        binding.ivProfile.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }
    }
}
