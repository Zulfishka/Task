package com.example.taskmanager.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentOnBoardBinding
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.utils.loadImage

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private val REQUEST_IMAGE = 1
    private var selectedImage: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref= Pref(requireContext())
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
        binding.ivProfile.setOnClickListener{
            openGal()
        }
    }

    private fun openGal() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null){
            selectedImage = data.data
            binding.ivProfile.loadImage(selectedImage.toString())
        }
    }
}
