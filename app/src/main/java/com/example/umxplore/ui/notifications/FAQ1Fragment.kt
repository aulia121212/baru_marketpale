package com.example.umxplore.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.umxplore.R

class FAQ1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_f_a_q1, container, false)

        // Tombol Back
        val buttonBack = view.findViewById<ImageButton>(R.id.button_back)
        buttonBack.setOnClickListener {
            requireActivity().onBackPressed() // Kembali ke aktivitas sebelumnya
            // atau
            // findNavController().navigateUp() // Kembali menggunakan NavController jika menggunakan Navigation Component
        }

        return view
    }
}
