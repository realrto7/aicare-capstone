package com.example.capstone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.data.local.UserSession
import com.example.capstone.ui.viewmodel.MainViewModel
import com.example.capstone.ui.viewmodel.ProfileViewModel
import com.example.capstone.ui.viewmodel.factory.ViewModelFactory

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val pref = UserSession.getInstance(dataStore)

        val profileViewModel =
            ViewModelProvider(this, ViewModelFactory(pref, this))[ProfileViewModel::class.java]

        profileViewModel.userToken.observe(this)
        { token: String ->
            if (token.isEmpty()) {
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
}