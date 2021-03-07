package br.com.greenlight.api

import br.com.greenlight.model.Trip

interface DistanceServiceListener {
    fun obterDistanceTerminou(trip: Trip?)
    fun falhaReportada(falha:String?)
}