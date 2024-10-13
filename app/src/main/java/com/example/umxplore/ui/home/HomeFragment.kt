package com.example.umxplore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.umxplore.R
import com.example.umxplore.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Meng-inflate layout untuk fragment dan menginisialisasi _binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set onClickListener untuk tombol jelajahiToko
        binding.jelajahiToko.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_dashboard)
        }

        binding.jelajahiProduk.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_produk)
        }

        // Menghapus inset yang disebabkan oleh status bar
        view.setOnApplyWindowInsetsListener { v, insets ->
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = 0 // Pastikan margin atas dihapus
            v.layoutParams = params
            insets.consumeSystemWindowInsets() // Konsumsi insets untuk menghindari tambahan jarak
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Mengatur binding menjadi null untuk mencegah memory leaks
        _binding = null
    }
}
