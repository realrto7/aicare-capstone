package com.example.capstone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.data.local.UserSession
import com.example.capstone.ui.fragment.HistoryFragment
import com.example.capstone.ui.fragment.HomeFragment
import com.example.capstone.ui.viewmodel.MainViewModel
import com.example.capstone.ui.viewmodel.ProfileViewModel
import com.example.capstone.ui.viewmodel.factory.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val homeFragment = HomeFragment()
        val historyFragment = HistoryFragment()

        val pref = UserSession.getInstance(dataStore)

        val mainViewModel =
            ViewModelProvider(this, ViewModelFactory(pref, this))[MainViewModel::class.java]

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

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.history -> setCurrentFragment(historyFragment)
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

    companion object {
        private const val DURATION = 200L
        private const val ALPHA = 1f
        val emailRegex: Regex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+\$")
        val phoneRegex: Regex = Regex("^(^\\+62|62|^08)(\\d{3,4}-?){2}\\d{3,4}\$")
    }
}
