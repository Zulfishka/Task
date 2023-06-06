package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.btnSave.setOnClickListener {
            if (
                binding.etTitle.text.isEmpty() ||
                binding.etDesc.text.isEmpty()
            ) {
                Toast.makeText(requireContext(), "поля не должны быть пустыми", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (binding.btnSave.text.equals(getString(R.string.update))) {
                    onUpdate()
                } else onSave()
                findNavController().navigateUp()
            }
        }
    }

    private fun onUpdate() {
        val result = task?.copy(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        if (result != null) {
            App.db.dao().update(result)
        }
    }

    private fun onSave() {
        val result = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.dao().insert(result)
    }

    private fun initData() {
        task = arguments?.getSerializable(HomeFragment.TASK_KEY) as Task?
        task?.let {
            binding.btnSave.text = getString(R.string.update)
            binding.etTitle.setText(it.title)
            binding.etDesc.setText(it.desc)
        }
    }
}