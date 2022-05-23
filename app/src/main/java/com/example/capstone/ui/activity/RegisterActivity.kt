package com.example.capstone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.example.capstone.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var loginButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var fullNameEditText: EditText

    private var correctEmail = false
    private var correctPassword = false
    private var correctName = false
    private var correctPhoneNumber = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginButton = binding.loginButton
        emailEditText = binding.emailEditText
        passwordEditText = binding.passwordEditText
        phoneNumberEditText = binding.phoneNumberEditText
        fullNameEditText = binding.nameEditText

        fullNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                correctName = !(!s.isNullOrEmpty() && s.length < 3)
                if (!correctName) fullNameEditText.error = "Minimum Name Length 3"
                setLoginButtonEnable()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        phoneNumberEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                correctPhoneNumber =
                    !s.isNullOrEmpty() && MainActivity.phoneRegex.matches(s.toString())
                if (!correctPhoneNumber) phoneNumberEditText.error = "Wrong Phone Number Format"
                setLoginButtonEnable()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                correctEmail =
                    !s.isNullOrEmpty() && MainActivity.emailRegex.matches(s.toString())
                if (!correctEmail) emailEditText.error = "Wrong Email Format"
                setLoginButtonEnable()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                correctPassword = !(!s.isNullOrEmpty() && s.length < 6)
                println("correntPassword" + correctPassword)
                if (!correctPassword) passwordEditText.error = "Minimum Password Length 6"
                setLoginButtonEnable()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.tvAccount.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun setLoginButtonEnable() {
        loginButton.isEnabled = correctEmail && correctPassword && correctName && correctPhoneNumber
    }
}