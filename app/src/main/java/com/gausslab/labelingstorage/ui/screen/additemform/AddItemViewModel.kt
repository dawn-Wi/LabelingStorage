package com.gausslab.labelingstorage.ui.screen.additemform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.gausslab.labelingstorage.model.Item
import com.gausslab.labelingstorage.remote.LSApi
import com.gausslab.labelingstorage.service.SnackbarService
import com.gausslab.labelingstorage.ui.screen.home.navigateToHomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val lsApi: LSApi,
    private val snackbarService: SnackbarService,
    private val navController: NavHostController
): ViewModel(){

    private val _assetNumber = MutableStateFlow("")
    val assetNumber = _assetNumber.asStateFlow()

    private val _startDate = MutableStateFlow("")
    val startDate = _startDate.asStateFlow()

    private val _checkDate = MutableStateFlow("")
    val checkDate = _checkDate.asStateFlow()

    private val _useDepartment = MutableStateFlow("")
    val useDepartment = _useDepartment.asStateFlow()

    private val _manager = MutableStateFlow("")
    val manager = _manager.asStateFlow()

    private val _isBusy = MutableStateFlow(false)
    val isBusy = _isBusy.asStateFlow()

    fun onEvent(event: AddItemUiEvent){
        when(event){
            is AddItemUiEvent.AssetNumberChanged -> {
                _assetNumber.value = event.assetNumber
            }
            is AddItemUiEvent.StartDateChanged -> {
                _startDate.value = event.startDate
            }
            is AddItemUiEvent.CheckDateChanged -> {
                _checkDate.value = event.checkDate
            }
            is AddItemUiEvent.UseDepartmentChanged -> {
                _useDepartment.value = event.useDepartment
            }
            is AddItemUiEvent.ManagerChanged -> {
                _manager.value = event.manager
            }
            AddItemUiEvent.OnSubmitPressed -> {
                _isBusy.value = true
                val toSubmit = generateItem()
                viewModelScope.launch {
                    lsApi.submitItem(toSubmit)
                    _isBusy.value=false
                    snackbarService.showSnackbar("아이템 등록 성공")
                    navController.navigateToHomeScreen()
                }
            }
        }
    }

    private fun generateItem(): Item{
        return Item(
            assetNumber = assetNumber.value,
            startDate = startDate.value,
            checkDate = checkDate.value,
            useDepartment = useDepartment.value,
            manager = manager.value
        )
    }
}

sealed class AddItemUiEvent{
    data class AssetNumberChanged(val assetNumber: String) : AddItemUiEvent()
    data class StartDateChanged(val startDate: String) : AddItemUiEvent()
    data class CheckDateChanged(val checkDate: String) : AddItemUiEvent()
    data class UseDepartmentChanged(val useDepartment: String) : AddItemUiEvent()
    data class ManagerChanged(val manager:String) : AddItemUiEvent()
    object OnSubmitPressed : AddItemUiEvent()
}