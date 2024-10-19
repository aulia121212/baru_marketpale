package com.example.umxplore.ui.akun

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.umxplore.R
import com.example.umxplore.app.ApiConfig
import com.example.umxplore.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AkunFragment : Fragment() {

    private lateinit var btn_register: ImageButton
    private lateinit var btn_login: ImageButton
    private lateinit var edt_nama: EditText
    private lateinit var edt_email: EditText
    private lateinit var edt_phone: EditText
    private lateinit var edt_password: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment ini
        val view = inflater.inflate(R.layout.fragment_akun, container, false)

        // Inisialisasi view
        btn_register = view.findViewById(R.id.btn_register)
        edt_nama = view.findViewById(R.id.edt_nama)
        edt_email = view.findViewById(R.id.edt_email)
        edt_phone = view.findViewById(R.id.edt_phone)
        edt_password = view.findViewById(R.id.edt_password)
        btn_login = view.findViewById(R.id.btn_login)

        btn_register.setOnClickListener {
            register()
        }



        // Tambahkan logika untuk mengarahkan ke activity_masuk saat btn_login diklik
        btn_login.setOnClickListener {
            val intent = Intent(requireActivity(), MasukActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    fun dataDummy() {
        edt_nama.setText("Aulia")
        edt_email.setText("Aulia@gmail.com")
        edt_phone.setText("082157501818")
        edt_password.setText("TA1234567")
    }

    private fun register() {
        if (edt_nama.text.isEmpty()) {
            edt_nama.error = "Kolom Nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        } else if (edt_email.text.isEmpty()) {
            edt_email.error = "Kolom Email tidak boleh kosong"
            edt_email.requestFocus()
            return
        } else if (edt_phone.text.isEmpty()) {
            edt_phone.error = "Kolom Nomor Telepon tidak boleh kosong"
            edt_phone.requestFocus()
            return
        } else if (edt_password.text.isEmpty()) {
            edt_password.error = "Kolom Password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.register(
            edt_nama.text.toString(),
            edt_email.text.toString(),
            edt_phone.text.toString(),
            edt_password.text.toString()
        ).enqueue(object : Callback<ResponModel> {

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    Toast.makeText(requireContext(), respon.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(requireContext(), "Register failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
