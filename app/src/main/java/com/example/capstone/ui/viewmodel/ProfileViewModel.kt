package com.example.capstone.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstone.data.local.UserSession

class ProfileViewModel(private val pref: UserSession) : ViewModel() {
    var userToken : LiveData<String> = pref.getToken().asLiveData()
}