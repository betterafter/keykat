package com.keykat.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import usecase.ProfileUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {
    private val _profile = MutableStateFlow<ProfileUiState>(ProfileUiState.Init)
    val profile: StateFlow<ProfileUiState> = _profile.asStateFlow()

    suspend fun initTopProfile() {
        _profile.value = ProfileUiState.Loading
        viewModelScope.launch {
            profileUseCase.getTopProfile()
                .collect {
                    _profile.value = ProfileUiState.Success(it)
                }
        }
    }
}