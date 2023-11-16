package uz.example.cryptovalyuta.util

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun convertTimeCustom(timestamp:Long?):String{
    if (timestamp == null) return ""
    val stamp = Timestamp(timestamp*1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val smp = SimpleDateFormat(pattern, Locale.getDefault())
    smp.timeZone = TimeZone.getDefault()
    return smp.format(date)
}