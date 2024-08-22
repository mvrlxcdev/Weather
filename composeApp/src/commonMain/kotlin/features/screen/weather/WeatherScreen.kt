package features.screen.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.ForecastInfo
import features.ui.theme.linearBase
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import weather.composeapp.generated.resources.Res
import weather.composeapp.generated.resources.drop_black
import weather.composeapp.generated.resources.sun_black
import weather.composeapp.generated.resources.thermometer_black
import weather.composeapp.generated.resources.wind_black

@OptIn(KoinExperimentalAPI::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = koinViewModel<WeatherViewModel>()
) {
    val uiState = viewModel.uiState.collectAsState().value
    if (uiState.isLoading or uiState.isError) {
        Text(uiState.errorMessage, fontSize = 12.sp)
    } else {
        Content(
            weatherIcon = uiState.icon,
            country = uiState.country,
            temperature = uiState.temperature,
            condition = uiState.condition,
            windMph = uiState.windKph,
            humidity = uiState.humidity,
            forecastList = uiState.forecastList,
            uvIndex = uiState.UVIndex,
            feelsLike = uiState.fellsLike
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    country: String,
    temperature: String,
    condition: String,
    weatherIcon: DrawableResource,
    windMph: String,
    humidity: String,
    forecastList: List<ForecastInfo>,
    uvIndex: String,
    feelsLike: String,
) {
    BottomSheetScaffold(
        sheetPeekHeight = 180.dp,
        sheetContent = {
            Details(
                forecastList,
                uvIndex,
                feelsLike
            )
        },
        sheetContainerColor = Color.Transparent,

        ) {

        Box(modifier = Modifier.fillMaxSize().background(linearBase))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth().fillMaxHeight(0.7f)


        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 14.dp).weight(0.1f)
            ) {
                Icon(
                    Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp).padding(end = 4.dp)
                )
                Text(
                    text = country,
                    fontSize = 20.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentHeight()
                )
            }
            Icon(
                vectorResource(weatherIcon),
                contentDescription = condition,
                tint = Color.White,
                modifier = Modifier.padding(24.dp).weight(0.25f).size(200.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().weight(0.3f)
            ) {
                Text(
                    text = condition,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                )
                Text(
                    text = temperature.plus("°"),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        vectorResource(Res.drawable.wind_black),
                        contentDescription = condition,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp).padding(end = 5.dp)
                    )
                    Text(
                        text = windMph.plus(" km/h"),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                    Icon(
                        vectorResource(Res.drawable.drop_black),
                        contentDescription = condition,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp).padding(end = 5.dp)
                    )
                    Text(
                        text = humidity.plus(" %"),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
private fun Details(
    forecastList: List<ForecastInfo>,
    uvIndex: String,
    feelsLike: String
) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.horizontalScroll(scrollState)
        ) {
            forecastList.forEach { data ->
                WeatherForecastCard(
                    icon = data.icon,
                    temp = data.temp,
                    weekDay = data.day
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            WeatherDetailsCard(
                title = "UV INDEX",
                icon = Res.drawable.sun_black,
                text = uvIndex,
                modifier = Modifier.weight(1f)
            )
            WeatherDetailsCard(
                title = "FEELS LIKE",
                icon = Res.drawable.thermometer_black,
                text = feelsLike,
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Composable
private fun WeatherForecastCard(
    icon: DrawableResource,
    weekDay: String,
    temp: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .size(height = 120.dp, width = 50.dp)

            .clip(RoundedCornerShape(24.dp))
            .background(brush = linearBase)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = weekDay,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                modifier = Modifier
            )
            Icon(
                vectorResource(icon),
                contentDescription = temp,
                tint = Color.White,
                modifier = Modifier.size(32.dp).padding(top = 4.dp, bottom = 4.dp)
            )
            Text(
                text = temp.plus("°"),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun WeatherDetailsCard(
    title: String,
    text: String,
    icon: DrawableResource,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, shape = RoundedCornerShape(20.dp), color = Color.DarkGray)
            .background(Color.Blue.copy(0.9f))
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                vectorResource(icon),
                contentDescription = title,
                tint = Color.White.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = title,
                fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                fontSize = 15.sp,
                color = Color.White.copy(alpha = 0.6f),
                modifier = Modifier
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}