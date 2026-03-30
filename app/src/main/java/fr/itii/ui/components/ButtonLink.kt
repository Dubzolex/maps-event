package fr.itii.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLink(
    text: String,
    onClick: () -> Unit
) {
    // État pour gérer l'interaction (hover/press)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Définition des couleurs
    val blueLight = Color(0xFF2196F3)
    val blueDark = Color(0xFF0D47A1)

    Text(
        text = text,
        color = if (isPressed) blueDark else blueLight,
        style = TextStyle(
            textDecoration = TextDecoration.Underline, // Surligné (souligné)
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Supprime l'effet de ripple (vague) gris par défaut
                onClick = onClick
            )
            .padding(vertical = 4.dp) // Petite zone de clic plus large
    )
}