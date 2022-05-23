package com.example.capstone.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.example.capstone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    private var correctEmail = false
    private var correctPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginButton = binding.loginButton
        emailEditText = binding.emailEditText
        passwordEditText = binding.passwordEditText

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
                if (!correctPassword) passwordEditText.error = "Minimum Password 6"
                setLoginButtonEnable()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.tvAccount.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun setLoginButtonEnable() {
        loginButton.isEnabled = correctEmail && correctPassword
    }
}