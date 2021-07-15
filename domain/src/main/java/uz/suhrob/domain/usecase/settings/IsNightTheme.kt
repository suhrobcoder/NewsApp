package uz.suhrob.domain.usecase.settings

import kotlinx.coroutines.flow.Flow
import uz.suhrob.pref.AppPrefs

class IsNightTheme(
    private val pref: AppPrefs,
) {
    operator fun invoke(): Flow<Boolean> {
        return pref.isNightTheme
    }
}