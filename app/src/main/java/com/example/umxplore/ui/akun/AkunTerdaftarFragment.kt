package com.example.umxplore.ui.akun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.umxplore.R


class AkunTerdaftarFragment : Fragment() {

    lateinit var s:SharedPref
    lateinit var btnLogout:ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_akun_terdaftar, container, false)
        btnLogout = view.findViewById(R.id.btn_Logout)

        s = SharedPref(activity!!)
        btnLogout.setOnClickListener{
            s.setStatusLogin(false)
        }
        return view
    }


}