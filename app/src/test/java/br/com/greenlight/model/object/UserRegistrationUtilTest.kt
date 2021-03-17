package br.com.greenlight.model.`object`

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UserRegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = UserRegistrationUtil.validateRegistrationUtil(
            "Fulano Fulaninho",
            "",
            "Rua Teste",
            "222222",
            "11/11/1111"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and other fields  correctly filled`() {
        val result = UserRegistrationUtil.validateRegistrationUtil(
            "Fulano Fulaninho",
            "Fulano",
            "Rua Teste",
            "222222",
            "11/11/1111"
        )
        assertThat(result).isTrue()
    }
}