package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter
import com.example.taskmanager.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapterh: TaskAdapter

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterh = TaskAdapter(this::onLongClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
            val tasks = App.db.dao().getAll()
            adapterh.addTasks(tasks)
        }

        private fun initRecycler(){
            binding.rvTask.adapter = adapterh
    }

    private fun onLongClick(task: Task){
        val alertDialog = AlertDialog.Builder (requireContext())
            .setTitle("Удалить")
            .setMessage("Вы уверены?")
            .setPositiveButton("Удалить") {_, _ ->
                App.db.dao().delete(task)
                val list = App.db.dao().getAll()
                adapterh.addTasks(list)
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}
