package com.beardness.macosmsapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.ad.compose.AdMobBannerWidget
import com.beardness.macosmsapp.navigation.AppNavigation
import com.beardness.macosmsapp.ui.theme.MacoSMSAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compose()
    }

    private fun compose() {
        setContent {
            MacoSMSAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScaffold(
                        content = { AppNavigation() },
                        bottom = { AdMobBannerWidget() },
                    )
                }
            }
        }
    }
}