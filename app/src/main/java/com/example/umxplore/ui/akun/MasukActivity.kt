package com.example.umxplore.ui.akun

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umxplore.R
import com.example.umxplore.app.ApiConfig
import com.example.umxplore.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasukActivity : AppCompatActivity() {

    lateinit var s: SharedPreferences
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_masuk_activity)

        // Inisialisasi elemen UI
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)

        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if (edtEmail.text.isEmpty()) {
            edtEmail.error = "Kolom Email tidak boleh kosong"
            edtEmail.requestFocus()
            return
        } else if (edtPassword.text.isEmpty()) {
            edtPassword.error = "Kolom Password tidak boleh kosong"
            edtPassword.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.login(
            edtEmail.text.toString(),
            edtPassword.text.toString()
        ).enqueue(object : Callback<ResponModel> {

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    Toast.makeText(this@MasukActivity, respon.message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MasukActivity, respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@MasukActivity, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
