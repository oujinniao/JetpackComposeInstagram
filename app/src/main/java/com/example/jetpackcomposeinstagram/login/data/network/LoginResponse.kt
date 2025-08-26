package com.example.jetpackcomposeinstagram.login.data.network

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("success") val success: Boolean)