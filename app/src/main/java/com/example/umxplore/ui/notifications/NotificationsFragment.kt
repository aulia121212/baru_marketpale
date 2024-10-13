package com.example.umxplore.ui.notifications

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.umxplore.R

class NotificationsFragment : Fragment() {

    private lateinit var btn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout first
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        // Initialize the buttons after inflating the layout
        btn = view.findViewById(R.id.button_wa)
        btn.setOnClickListener {
            val wpurl = "https://wa.me/+6281351359577?text=Halo Pak/Bu, saya ingin menanyakan terkait aplikasi UMKM Xplore Balikpapan"

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(wpurl)
            startActivity(intent)
        }

        // Initialize the buttons after inflating the layout
        btn = view.findViewById(R.id.imagebutton_telepon)
        btn.setOnClickListener {
            val phoneNumber = "05428879235"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)

        }

        val buttonTanya1 = view.findViewById<ImageButton>(R.id.tanya1)
        buttonTanya1.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_faq_to_navigation_faq1)
        }

        val buttonTanya2 = view.findViewById<ImageButton>(R.id.tanya2)
        buttonTanya2.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_faq_to_navigation_faq2)
        }

        val buttonTanya3 = view.findViewById<ImageButton>(R.id.tanya3)
        buttonTanya3.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_faq_to_navigation_faq3)
        }

        val buttonTanya4 = view.findViewById<ImageButton>(R.id.tanya4)
        buttonTanya4.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_faq_to_navigation_faq4)
        }

        val buttonTanya5 = view.findViewById<ImageButton>(R.id.tanya5)
        buttonTanya5.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_faq_to_navigation_faq5)
        }

        val buttonTanya6 = view.findViewById<ImageButton>(R.id.tanya6)
        buttonTanya6.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_faq_to_navigation_faq6)
        }

        val buttonTanya7 = view.findViewById<ImageButton>(R.id.tanya7)
        buttonTanya7.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_faq_to_navigation_faq7)
        }

        val buttonTanya8 = view.findViewById<ImageButton>(R.id.tanya8)
        buttonTanya8.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_faq_to_navigation_faq8)
        }

        // Return the inflated view
        return view
    }
}
