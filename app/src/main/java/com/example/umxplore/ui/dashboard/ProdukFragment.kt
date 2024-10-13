package com.example.umxplore.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.umxplore.R
import com.example.umxplore.databinding.FragmentProdukBinding

class ProdukFragment : Fragment() {

    private var _binding: FragmentProdukBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val produkViewModel =
            ViewModelProvider(this).get(ProdukViewModel::class.java)

        _binding = FragmentProdukBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.imageLokasi.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_produk_to_navigation_dashboard)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}