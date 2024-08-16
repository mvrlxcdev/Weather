package features.screen.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import features.ui.theme.solidPink
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = koinViewModel<WeatherViewModel>()
) {
    val uiState = viewModel.uiState.collectAsState().value

    Content(
        country = uiState.country,
        temperature = uiState.temperature,
        condition = uiState.condition,
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    country: String,
    temperature: Int,
    condition: String,
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 300.dp,
        sheetContent = { Details() }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = country,
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 64.dp, bottom = 8.dp)
            )
            Text(
                text = temperature.toString().plus("°"),
                fontSize = 48.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(text = condition, fontSize = 16.sp, modifier = Modifier.padding(8.dp))
        }
    }
}


@Composable
private fun Details(

) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        for (i in 0..55) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(solidPink)

            )
        }

    }
}

