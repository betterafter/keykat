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
    private val _topProfile = MutableStateFlow<TopProfileUiState>(TopProfileUiState.Init)
    val topProfile: StateFlow<TopProfileUiState> = _topProfile

    private val _bottomProfile = MutableStateFlow<BottomProfileUiState>(BottomProfileUiState.Init)
    val bottomProfile: StateFlow<BottomProfileUiState> = _bottomProfile

    suspend fun initTopProfile() {
        _topProfile.value = TopProfileUiState.Loading
        viewModelScope.launch {
            profileUseCase.getTopProfile()
                .catch { e ->
                    _topProfile.value = TopProfileUiState.Error(e)
                }.collect {
                    if (it != null) {
                        _topProfile.value = TopProfileUiState.Success(it)
                    } else {
                        _topProfile.value = TopProfileUiState.Error()
                    }
                }
        }
    }

    suspend fun initBottomProfile() {
        _bottomProfile.value = BottomProfileUiState.Loading
        viewModelScope.launch {
            profileUseCase.getBottomProfile()
                .catch { e ->
                    _bottomProfile.value = BottomProfileUiState.Error(e)
                }.collect {
                    _bottomProfile.value = BottomProfileUiState.Success(it)
                }
        }
    }
}