package com.keykat.data.profile.datasource

import com.google.gson.Gson
import com.keykat.data.profile.dto.ProfileDto


class MockProfileApi {
    fun getMockProfile(): ProfileDto? {
        try {
            val inputStream = javaClass.classLoader?.getResourceAsStream("assets/profile.json")
            val jsonString = inputStream?.bufferedReader().use { it?.readText() }

            return Gson().fromJson(jsonString, ProfileDto::class.java)
        } catch (e: Exception) {
            return null
        }
    }
}