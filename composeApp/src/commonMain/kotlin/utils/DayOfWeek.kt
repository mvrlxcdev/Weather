package utils

fun getDayOfWeek(date: String): String {

    val parts = date.split("-")
    val year = parts[0].toInt()
    val month = parts[1].toInt()
    val day = parts[2].toInt()

    var m = month
    var y = year
    if (m < 3) {
        m += 12
        y -= 1
    }

    val k = y % 100
    val j = y / 100

    val h = (day + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) + 5 * j) % 7

    return when (h) {
        0 -> "SAT"
        1 -> "SUN"
        2 -> "MON"
        3 -> "TUE"
        4 -> "WED"
        5 -> "THU"
        6 -> "FRI"
        else -> "UNKNWN"
    }
}