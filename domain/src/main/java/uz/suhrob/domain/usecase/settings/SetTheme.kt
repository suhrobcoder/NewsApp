package uz.suhrob.domain.usecase.settings

import uz.suhrob.pref.AppPrefs

class SetTheme(
    private val pref: AppPrefs,
) {
    suspend operator fun invoke(isNightTheme: Boolean) {
        pref.setNightTheme(isNightTheme)
    }
}