package com.keykat.presentation.screen.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import usecase.ProfileUseCase
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {
    private val _scrollState = MutableStateFlow<PagerState?>(null)
    val scrollState: MutableStateFlow<PagerState?> = _scrollState

    private val _topProfile = MutableStateFlow<TopProfileUiState>(TopProfileUiState.Init)
    val topProfile: StateFlow<TopProfileUiState> = _topProfile

    private val _bottomProfile = MutableStateFlow<BottomProfileUiState>(BottomProfileUiState.Init)
    val bottomProfile: StateFlow<BottomProfileUiState> = _bottomProfile

    fun setScrollState(scrollState: PagerState) {
        _scrollState.value = scrollState
        println("[keykat] scroll: ${scrollState}")
    }

    fun getScrollState() = _scrollState.value

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