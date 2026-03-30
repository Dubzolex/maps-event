package fr.itii.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ButtonAction(
    text: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    // 2. On observe si le bouton est pressé
    val isPressed by interactionSource.collectIsPressedAsState()

    // 3. On définit la couleur en fonction de l'état
    val backgroundColor = if (isPressed) Color(0xFF1565C0) else Color(0xFF42A5F5)

    Button(
        onClick = onClick,
        // 1. Définition des couleurs (Fond et Texte)
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor, // Bleu clair par défaut
            contentColor = Color.White,         // Texte en blanc
             ),
        // 2. Arrondir les bords (ici 12.dp, ajustez selon vos goûts)
        shape = RoundedCornerShape(10.dp),
        // 3. Supprimer l'élévation (ombre) si vous voulez un look "flat" sans bordure/relief
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 2.dp
        ),
        contentPadding = PaddingValues(16.dp,12.dp),
        modifier = Modifier.wrapContentSize()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}
