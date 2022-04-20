package com.example.unitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.R

class AreaViewModel : ViewModel() {
    private val _unit: MutableLiveData<Int> = MutableLiveData(R.string.square_centimeter)

    val unit: LiveData<Int>
        get() = _unit

    fun setUnit(value: Int) {
        _unit.value = value
    }

    private val _area: MutableLiveData<String> = MutableLiveData("")

    val area: LiveData<String>
        get() = _area

    fun getAreaAsFloat(): Float = (_area.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setArea(value: String) {
        _area.value = value
    }

    fun convert() = getAreaAsFloat().let {
        if (!it.isNaN())
            if (_unit.value == R.string.square_centimeter)
                it * 0.0001F
            else
                it * 1000F
        else
            Float.NaN
    }
}