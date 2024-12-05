package com.example.presentation.screens.roomscreen

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.DeleteFromFavoriteUseCase
import com.example.domain.usecase.GetFavoriteCatsUseCase
import com.example.presentation.ads.AdsProvider
import com.example.presentation.core.BaseViewModel
import com.example.presentation.mapper.toCatUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getFavoriteCatsUseCase: GetFavoriteCatsUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val adsProvider: AdsProvider,
) : BaseViewModel<RoomContracts.State, RoomContracts.Event, RoomContracts.Effect>(
    RoomContracts.State()
) {
    override fun onEvent(event: RoomContracts.Event) {
        super.onEvent(event)
        when (event) {
            is RoomContracts.Event.OnLoad -> load()
            is RoomContracts.Event.OnDelete -> {
                deleteFromFavorite(event.id)
                load()
            }

            is RoomContracts.Event.OnAdsClick -> showInterstitialAds(event.activity)
        }
    }

    private fun load() = viewModelScope.launch {
        setState { it.copy(isLoading = true) }
        val list = getFavoriteCatsUseCase.execute().map { it.toCatUi(isFavorite = true) }
        setState { it.copy(images = list, isLoading = false) }
    }

    private fun deleteFromFavorite(id: String) = viewModelScope.launch {
        deleteFromFavoriteUseCase.execute(id)
    }

    private fun showInterstitialAds(activity: Activity) {
        adsProvider.interstitialAd(activity)
    }
}
