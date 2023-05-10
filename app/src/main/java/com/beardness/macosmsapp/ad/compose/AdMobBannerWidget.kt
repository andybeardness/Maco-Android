package com.beardness.macosmsapp.ad.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.viewinterop.AndroidView
import com.beardness.macosmsapp.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

private const val BOTTOM_BANNER_AD_ID = BuildConfig.ADMOB_BOTTOM_BANNER_ID

@Composable
fun AdMobBannerWidget() {
    val inspectionMode = LocalInspectionMode.current

    if (!inspectionMode) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = BOTTOM_BANNER_AD_ID
                    loadAd(AdRequest.Builder().build())
                }
            },
        )
    }
}