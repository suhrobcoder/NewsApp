package uz.suhrob.domain.usecase.settings

import uz.suhrob.pref.AppPrefs

class SetUpdateInterval(
    private val pref: AppPrefs,
) {
    suspend operator fun invoke(interval: Int) {
        pref.setUpdateInterval(interval)
    }
}