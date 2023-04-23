package com.beardness.macosmsapp.usecase.common.helpers.avatarcolorgenerator

import androidx.compose.ui.graphics.Color

interface AvatarColorGeneratorProtocol {
    fun generate(author: String): Color
}
