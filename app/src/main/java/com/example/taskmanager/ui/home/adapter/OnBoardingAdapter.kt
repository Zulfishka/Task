package com.example.taskmanager.ui.home.adapter

import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoard
import com.example.taskmanager.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val data = arrayListOf<OnBoard>(
        OnBoard(
            "https://w7.pngwing.com/pngs/398/1016/png-transparent-task-manager-task-management-action-item-tasks-together-orange-logo-sign-thumbnail.png",
            "Task Manager 1",
            "Task Manager Description"
        ),
        OnBoard(
            "https://w7.pngwing.com/pngs/185/850/png-transparent-task-computer-icons-tasks-s-angle-text-microsoft-office-thumbnail.png",
            "Task Manager 2",
            "Task Manager Description"
        ),
        OnBoard(
            "https://w7.pngwing.com/pngs/894/712/png-transparent-task-management-project-management-performance-management-business-text-service-people-thumbnail.png",
            "Task Manager 3",
            "Task Manager Description"
        ),

        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {

        fun bind(onBoard: OnBoard) {
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.description
            binding.ivBoard.loadImage(onBoard.image)


            binding.skip.isVisible = adapterPosition != data.lastIndex
            binding.start.isVisible = adapterPosition == data.lastIndex

            binding.skip.setOnClickListener {
                onClick()
            }
            binding.start.setOnClickListener {
                onClick()
            }
        }
    }
}
