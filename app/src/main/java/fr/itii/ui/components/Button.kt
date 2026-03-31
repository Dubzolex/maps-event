package fr.itii.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
fun ActionButton(
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

@Composable
fun NeutralButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp), // Ajout de la virgule manquante
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        border = BorderStroke(1.dp, Color.Gray), // Optionnel : pour définir la couleur du contour
        modifier = Modifier.wrapContentSize()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray // Optionnel : pour un look plus "neutre"
        )
    }
}








@Composable
fun LinkButton(
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