package com.gausslab.labelingstorage.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.gausslab.labelingstorage.ui.screen.additemform.navigateToAddItemScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navController: NavHostController
): ViewModel(){

    fun onEvent(event: HomeUiEvent){
        when(event){
            HomeUiEvent.AddItemButtonPressed -> {
                navController.navigateToAddItemScreen()
            }
        }
    }

}

sealed class HomeUiEvent{
    object AddItemButtonPressed : HomeUiEvent()
}