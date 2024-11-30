package com.keykat.data.profile.datasource

import com.google.gson.Gson
import com.keykat.data.profile.dto.EducationDto
import com.keykat.data.profile.dto.ProfileDto
import com.keykat.data.profile.dto.TechDto


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

    fun getMockEducation(): EducationDto? {
        try {
            val inputStream = javaClass.classLoader?.getResourceAsStream("assets/education.json")
            val jsonString = inputStream?.bufferedReader().use { it?.readText() }

            return Gson().fromJson(jsonString, EducationDto::class.java)
        } catch (e: Exception) {
            return null
        }
    }

    fun getTechStack(): TechDto? {
        try {
            val inputStream = javaClass.classLoader?.getResourceAsStream("assets/tech.json")
            val jsonString = inputStream?.bufferedReader().use { it?.readText() }

            return Gson().fromJson(jsonString, TechDto::class.java)
        } catch (e: Exception) {
            return null
        }
    }
}