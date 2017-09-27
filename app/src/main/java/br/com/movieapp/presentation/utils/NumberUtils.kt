package br.com.movieapp.presentation.utils

import java.math.BigDecimal

/**
 * Created by pedrohenrique on 26/09/17.
 */
class NumberUtils{
    companion object {
        fun roundFloat(d: Float, decimalPlace: Int): BigDecimal {
            var bd = BigDecimal(java.lang.Float.toString(d))
            bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP)
            return bd
        }
    }
}