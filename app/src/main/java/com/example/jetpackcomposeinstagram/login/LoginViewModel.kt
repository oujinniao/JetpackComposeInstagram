package com.example.jetpackcomposeinstagram.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String): Boolean {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 6
        return isValidEmail && isPasswordValid
    }
    //logica para iniciar sesion

    fun onLoginClicked() {
        Log.i("Login", "Intento de inicio de sesión")
        Log.i("Login", "Email: ${email.value}")
        Log.i("Login", "Password: ${password.value}")
        if (enableLogin(email.value!!, password.value!!)) {
            Log.i("Login", "Inicio de sesión exitoso")
        } else {
            Log.i("Login", "Inicio de sesión fallido")
        }

    }



}