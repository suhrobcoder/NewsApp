package uz.suhrob.domain.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilsTest {
    @Test
    fun `works correct when hour is zero`() {
        val time1 = 30
        assertThat(time1.toTimeString()).matches("30 min")
    }

    @Test
    fun `works correct when minute is zero`() {
        val time1 = 60
        assertThat(time1.toTimeString()).matches("1 h ")
    }

    @Test
    fun `works correct when both of hour and minute are not zero`() {
        val time1 = 90
        assertThat(time1.toTimeString()).matches("1 h 30 min")
    }
}