package utils

import org.jetbrains.compose.resources.DrawableResource
import weather.composeapp.generated.resources.Res
import weather.composeapp.generated.resources.cloud_black
import weather.composeapp.generated.resources.cloudelec_black
import weather.composeapp.generated.resources.cloudelecrainy_black
import weather.composeapp.generated.resources.cloudsunny_black
import weather.composeapp.generated.resources.rainycloud_black
import weather.composeapp.generated.resources.snow_black
import weather.composeapp.generated.resources.snowycloud_black
import weather.composeapp.generated.resources.snowycloudbig_black
import weather.composeapp.generated.resources.sun_black
import weather.composeapp.generated.resources.thunder_black

fun weatherIcon(code: Int): DrawableResource {
    return when (code) {
        1000 -> Res.drawable.sun_black
        1003 -> Res.drawable.cloudsunny_black
        1006 -> Res.drawable.cloud_black
        1009 -> Res.drawable.cloud_black
        1030 -> Res.drawable.cloud_black
        1063 -> Res.drawable.rainycloud_black
        1066 -> Res.drawable.cloudsunny_black
        1069 -> Res.drawable.snowycloud_black
        1072 -> Res.drawable.cloud_black
        1087 -> Res.drawable.cloudelec_black
        1114 -> Res.drawable.snowycloud_black
        1117 -> Res.drawable.snowycloud_black
        1135 -> Res.drawable.cloud_black
        1147 -> Res.drawable.snowycloud_black
        1150 -> Res.drawable.rainycloud_black
        1153 -> Res.drawable.rainycloud_black
        1168 -> Res.drawable.cloud_black
        1171 -> Res.drawable.snowycloud_black
        1180 -> Res.drawable.rainycloud_black
        1183 -> Res.drawable.rainycloud_black
        1186 -> Res.drawable.rainycloud_black
        1189 -> Res.drawable.rainycloud_black
        1192 -> Res.drawable.rainycloud_black
        1195 -> Res.drawable.rainycloud_black
        1198 -> Res.drawable.rainycloud_black
        1201 -> Res.drawable.rainycloud_black
        1204 -> Res.drawable.rainycloud_black
        1207 -> Res.drawable.rainycloud_black
        1210 -> Res.drawable.snowycloud_black
        1213 -> Res.drawable.snowycloud_black
        1216 -> Res.drawable.snowycloud_black
        1219 -> Res.drawable.snowycloud_black
        1222 -> Res.drawable.snowycloudbig_black
        1225 -> Res.drawable.snowycloudbig_black
        1237 -> Res.drawable.snowycloud_black
        1240 -> Res.drawable.snowycloud_black
        1243 -> Res.drawable.rainycloud_black
        1246 -> Res.drawable.rainycloud_black
        1249 -> Res.drawable.rainycloud_black
        1252 -> Res.drawable.rainycloud_black
        1255 -> Res.drawable.snowycloud_black
        1258 -> Res.drawable.snowycloud_black
        1261 -> Res.drawable.snowycloud_black
        1264 -> Res.drawable.snow_black
        1273 -> Res.drawable.cloudelec_black
        1276 -> Res.drawable.cloudelec_black
        1279 -> Res.drawable.thunder_black
        1282 -> Res.drawable.cloudelecrainy_black

        else -> Res.drawable.sun_black
    }
}