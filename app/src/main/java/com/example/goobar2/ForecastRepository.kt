package com.example.goobar2
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast (zipcode: String){
        val randomValues = List(10){ Random.nextFloat().rem(100) * 100}
        val forecastItems  = randomValues.map { temp->
            DailyForecast(temp, getDescription(temp))
        }
        _weeklyForecast.setValue(forecastItems)
    }
    private fun getDescription (temp : Float): String {
        return when (temp){
            0f -> "No sense"
            in 0f.rangeTo(32f) -> "Way too Cold"
            in 32f.rangeTo(55f) -> "Colder than i prefer"
            in 55f.rangeTo(65f) -> "Getting Better"
            in 35f.rangeTo(80f) -> "Sweet Spot"
            in 80f.rangeTo(90f) -> "Getting Hotter"
            in 90f.rangeTo(100f) -> "Need  Ac"
            in 100f .. Float.MAX_VALUE -> "is it Arizona ?"
            else -> "Does Not Compute"
        }
    }
}