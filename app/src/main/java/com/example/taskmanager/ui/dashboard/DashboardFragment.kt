package com.example.taskmanager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.App.Companion.db
import com.example.taskmanager.databinding.FragmentDashboardBinding
import com.example.taskmanager.model.Car
import com.example.taskmanager.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db =  FirebaseFirestore.getInstance()
        binding.btnSave.setOnClickListener {
            onSave()
        }
    }

    private fun onSave() = with(binding) {
        val car = Car(name = etName.text.toString(), model = etModel.text.toString())
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .document().set(car).addOnSuccessListener {
                etName.text?.clear()
                etModel.text?.clear()
                showToast("Успешно")
            }.addOnFailureListener{
                showToast("Ошибка")
            }
}
}