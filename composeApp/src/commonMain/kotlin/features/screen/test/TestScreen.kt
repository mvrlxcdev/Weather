package features.screen.test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun TestScreen(
    viewModel: TestScreenViewModel = koinViewModel<TestScreenViewModel>()
) {
    val state = viewModel.uiState.collectAsState()
    val textField = remember { mutableStateOf("") }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(state.value.text)
            TextField(textField.value, onValueChange = { textField.value = it })
            Button(onClick = { viewModel.getWeather() }) {
                Text("fetch", modifier = Modifier.padding(16.dp), fontSize = 24.sp)
            }
        }
    }
}