package br.com.greenlight

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception
import kotlin.jvm.Throws

class VehicleTest {
    
    @Test
    @Throws(Exception::class)
    fun `carbon emission by fuel`() {

        val co2emissionByOil = 217
        val co2emissionByDiesel = 280
        val co2emissionByEtanol = 66
        val co2emissionByGas = 154

        val kmTraveled = 2

        val expectedOil = 434
        val expectedDiesel = 560
        val expectedEtanol = 132
        val expectedGas = 308

        val actualOil = co2emissionByOil * kmTraveled
        val actualDiesel = co2emissionByDiesel * kmTraveled
        val actualEtanol = co2emissionByEtanol * kmTraveled
        val actualGas = co2emissionByGas * kmTraveled

        assertEquals(actualOil, expectedOil)
        assertEquals(actualDiesel, expectedDiesel)
        assertEquals(actualEtanol, expectedEtanol)
        assertEquals(actualGas, expectedGas)
    }
}