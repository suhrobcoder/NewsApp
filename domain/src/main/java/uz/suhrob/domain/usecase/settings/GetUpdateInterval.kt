package uz.suhrob.domain.usecase.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.suhrob.domain.util.DEFAULT_INTERVAL
import uz.suhrob.pref.AppPrefs

class GetUpdateInterval(
    private val pref: AppPrefs,
) {
    operator fun invoke(): Flow<Int> {
        return pref.updateInterval.map { it ?: DEFAULT_INTERVAL }
    }
}