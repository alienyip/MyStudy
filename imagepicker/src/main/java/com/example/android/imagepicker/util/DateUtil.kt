@file:JvmName("DataUtil")
package com.example.android.imagepicker.util

import java.util.*

/**
 * Created by Nature on 2017/12/4.
 */
fun isSameDate(date1: Date, date2: Date): Boolean {
    val cal1 = Calendar.getInstance()
    val cal2 = Calendar.getInstance()
    cal1.firstDayOfWeek = Calendar.MONDAY
    cal2.firstDayOfWeek = Calendar.MONDAY
    cal1.time = date1
    cal2.time = date2
    val subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)
    if (subYear == 0) {
        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
            return true
    } else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
            return true
    } else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
        if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
            return true
    }
    return false
}