package features.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.remote.models.SearchLocations
import features.ui.theme.linearBase
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(
    viewModel: WeatherViewModel = koinViewModel<WeatherViewModel>()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Content(
                isFailure = uiState.isError,
                isLoading = uiState.isLoading,
                exception = uiState.errorMessage,
                locations = uiState.countiesList,
                text = uiState.searchText,
                onTextFieldValueChange = {
                    viewModel.onTriggerEvent(
                        WeatherViewEvent.GetLocations(
                            it
                        )
                    )
                },
                onCardClick = {
                   // scope.launch { scaffoldState.bottomSheetState.hide() }
                    viewModel.onTriggerEvent(WeatherViewEvent.SelectCountry(it))

                },
                currentCountry = uiState.country,
            )
        },
        sheetContainerColor = Color.Transparent,

        ) {
        WeatherScreen(onCountryClick = { scope.launch { scaffoldState.bottomSheetState.expand() } })
    }
}

@Composable
private fun Content(
    isLoading: Boolean,
    isFailure: Boolean,
    exception: String,
    locations: List<SearchLocations>,
    onTextFieldValueChange: (String) -> Unit,
    text: String,
    onCardClick: (String) -> Unit,
    currentCountry: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(linearBase)
            .fillMaxSize()
    ) {
        Text("current: $currentCountry")
        TextField(
            value = text,
            onValueChange = onTextFieldValueChange,
            placeholder = {
                Text(
                    text = "type location",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            textStyle = MaterialTheme.typography.labelMedium,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            modifier = Modifier.height(64.dp).fillMaxWidth().padding(4.dp),

            )
        if (isLoading) {
            Text("Loading")
        } else if (isFailure) {
            Text("error $exception", fontSize = 24.sp)
        } else {
            LazyColumn {
                itemsIndexed(locations.toTypedArray()) { _, item ->
                    LocationCard(
                        country = item.country,
                        city = item.name,
                        onClick = { onCardClick(item.name) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LocationCard(
    country: String,
    city: String,
    onClick: () -> Unit,

    ) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        onClick = onClick
    ) {
        Text(
            text = city,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = country,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
        )
    }
}


