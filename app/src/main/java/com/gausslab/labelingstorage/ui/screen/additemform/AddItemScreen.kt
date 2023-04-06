package com.gausslab.labelingstorage.ui.screen.additemform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.inputfield.InputField
import com.baec23.ludwig.component.section.DisplaySection

const val addItemScreenRoute = "addItem_screen_route"

fun NavGraphBuilder.addItemScreen() {
    composable(route = addItemScreenRoute) {
        AddItemScreen()
    }
}

fun NavController.navigateToAddItemScreen(navOptions: NavOptions? = null) {
    this.navigate(route = addItemScreenRoute, navOptions = navOptions)
}

@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val assetNumber by viewModel.assetNumber.collectAsState()
    val startDate by viewModel.startDate.collectAsState()
    val checkDate by viewModel.checkDate.collectAsState()
    val useDepartment by viewModel.useDepartment.collectAsState()
    val manager by viewModel.manager.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DisplaySection(headerText = "Add Form") {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 11.sp,
                    text = "제품사진"
                )
                Spacer (modifier = Modifier.height(5.dp))
                Button(
                    modifier = Modifier.height(35.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Take a Photo")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 11.sp,
                    text = "자산번호"
                )
                Spacer(modifier = Modifier.height(5.dp))
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    value = assetNumber,
                    onValueChange = { viewModel.onEvent(AddItemUiEvent.AssetNumberChanged(it)) },
                    maxLines = 1,
                    minLines = 1,
                    placeholder = "자산번호를 적어주세요."
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 11.sp,
                    text = "도입일자"
                )
                Spacer(modifier = Modifier.height(5.dp))
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    value = startDate,
                    onValueChange = { viewModel.onEvent(AddItemUiEvent.StartDateChanged(it)) },
                    maxLines = 1,
                    minLines = 1,
                    placeholder = "도입일자를 적어주세요."
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 11.sp,
                    text = "조사일자"
                )
                Spacer(modifier = Modifier.height(5.dp))
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    value = checkDate,
                    onValueChange = { viewModel.onEvent(AddItemUiEvent.CheckDateChanged(it)) },
                    maxLines = 1,
                    minLines = 1,
                    placeholder = "조사일자를 적어주세요."
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 11.sp,
                    text = "사용부서"
                )
                Spacer(modifier = Modifier.height(5.dp))
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    value = useDepartment,
                    onValueChange = { viewModel.onEvent(AddItemUiEvent.UseDepartmentChanged(it)) },
                    maxLines = 1,
                    minLines = 1,
                    placeholder = "사용부서를 적어주세요."
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 11.sp,
                    text = "취급책임"
                )
                Spacer(modifier = Modifier.height(5.dp))
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    value = manager,
                    onValueChange = { viewModel.onEvent(AddItemUiEvent.ManagerChanged(it)) },
                    maxLines = 1,
                    minLines = 1,
                    placeholder = "취급책임을 적어주세요."
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            StatefulButton(
                modifier = Modifier.fillMaxWidth(),
                text = "저장",
            ) {
                viewModel.onEvent(AddItemUiEvent.OnSubmitPressed)
            }
        }
    }
}
