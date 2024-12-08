package com.keykat.presentation.screen.career

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keykat.domain.career.entity.CareerDetailEntity
import com.keykat.domain.career.entity.CareerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import usecase.CareerUseCase
import javax.inject.Inject

@HiltViewModel
class CareerViewModel @Inject constructor(
    private val careerUseCase: CareerUseCase
) : ViewModel() {
    private val _career = MutableStateFlow<CareerUiState>(CareerUiState.Init)
    val career: MutableStateFlow<CareerUiState> = _career

    private val _currentClickedCareer = MutableStateFlow<CareerEntity?>(null)
    val currentClickedCareer: MutableStateFlow<CareerEntity?> = _currentClickedCareer


    private val _currentClickedCareerDetail = MutableStateFlow<CareerDetailEntity?>(null)
    val currentClickedCareerDetail: MutableStateFlow<CareerDetailEntity?> = _currentClickedCareerDetail

    private val _isExpandedModalBottomSheet = MutableStateFlow(false)
    val isExpandedModalBottomSheet: MutableStateFlow<Boolean> = _isExpandedModalBottomSheet

    suspend fun getCareer() {
        _career.value = CareerUiState.Loading
        viewModelScope.launch {
            careerUseCase.getCareer()
                .catch { error ->
                    _career.value = CareerUiState.Error(error = error)
                }.collect {
                    _career.value = CareerUiState.Success(it)
                    println("[keykat] career: ${_career.value}")
                }
        }
    }

    fun clickDetail(
        careerEntity: CareerEntity,
        careerDetailEntity: CareerDetailEntity
    ) {
        _currentClickedCareer.value = careerEntity
        _currentClickedCareerDetail.value = careerDetailEntity
        _isExpandedModalBottomSheet.value = true
    }

    fun closeModalBottomSheet() {
        _isExpandedModalBottomSheet.value = false
    }
}