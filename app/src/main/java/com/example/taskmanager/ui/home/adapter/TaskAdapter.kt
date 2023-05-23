package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ListItemBinding
import com.example.taskmanager.model.Task



class TaskAdapter(private val list : ArrayList<Task>): Adapter<TaskAdapter.TaskViewHolder>() {


    fun addTask(task: Task){
        list.add(0, task)
        notifyItemChanged(0)
    }

    inner class TaskViewHolder(private val binding: ListItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}