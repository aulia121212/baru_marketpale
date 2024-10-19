package com.example.umxplore.ui.akun

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umxplore.MainActivity
import com.example.umxplore.R
import com.example.umxplore.app.ApiConfig
import com.example.umxplore.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasukActivity : AppCompatActivity() {

    // Deklarasi variabel SharedPreferences
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: ImageButton
    private lateinit var pb: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_masuk_activity)

        supportActionBar?.hide()

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE)

        // Inisialisasi elemen UI
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)
        pb = findViewById(R.id.pb) // Pastikan ProgressBar sudah diinisialisasi

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

        pb.visibility = View.VISIBLE

        ApiConfig.instanceRetrofit.login(
            edtEmail.text.toString(),
            edtPassword.text.toString()
        ).enqueue(object : Callback<ResponModel> {

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE

                val respon = response.body()!!
                Log.d("LoginResponse", "Response: ${respon?.succes}")

                if (respon.succes == 1) {
                    saveLoginStatus(true)

                    // Intent ke MainActivity dengan extra 'show_akun_terdaftar'
                    val user = respon.user

                    val intent = Intent(this@MasukActivity, MainActivity::class.java).apply {
                        putExtra("user_id", user.id)
                        putExtra("show_akun_terdaftar", true) // Kirimkan flag secara eksplisit
                    }
                    startActivity(intent)
                    finish()

                    Toast.makeText(this@MasukActivity, "Selamat datang " + respon.user.name, Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@MasukActivity, respon?.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@MasukActivity, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    // Fungsi untuk menyimpan status login
    private fun saveLoginStatus(isLoggedIn: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_logged_in", isLoggedIn)
        editor.apply()
    }
}
