package com.beardness.macosmsapp.ui.compose.widget.gif

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.beardness.macosmsapp.R
import com.beardness.macosmsapp.ui.compose.component.spacer.SpacerColumn
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun GifWidget(
    modifier: Modifier,
    gifResId: Int?,
) {
    val waitingText = stringResource(id = R.string.waiting)

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            val factory =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoderDecoder.Factory()
                } else {
                    GifDecoder.Factory()
                }

            add(factory = factory)
        }
        .build()

    Surface(
        shape = MaterialTheme.shapes.large,
        shadowElevation = MacoDimens.dp4,
    ) {
        Column(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.large)
                .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f))
                .padding(all = MacoDimens.dp16),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = modifier,
                painter = rememberAsyncImagePainter(
                    model = gifResId,
                    imageLoader = imageLoader,
                ),
                contentDescription = null,
            )

            SpacerColumn(dp = MacoDimens.dp8)

            Text(
                text = waitingText,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}