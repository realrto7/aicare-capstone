package com.example.capstone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.capstone.ui.fragment.CameraFragment
import com.example.capstone.ui.fragment.HistoryFragment
import com.example.capstone.ui.fragment.HomeFragment
import com.example.capstone.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.maps -> {
                val i = Intent(this, MapsActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.profile -> {
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val cameraFragment = CameraFragment()
        val historyFragment = HistoryFragment()

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.camera -> setCurrentFragment(cameraFragment)
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