package br.com.greenlight.model

import Rows

class TripDetails (
    val destination_addresses : List<String>,
    val origin_addresses : List<String>,
    val rows : List<Rows>,
    val status : String
)