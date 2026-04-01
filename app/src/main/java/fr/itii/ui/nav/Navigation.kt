package fr.itii.ui.nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Navigation(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem(
                icon = Icons.Filled.LocationOn,
                label = "Maps",
                isSelected = selectedItem == 0,
                onClick = { onItemSelected(0) }
            )

            NavigationItem(
                icon = Icons.Filled.Event,
                label = "Events",
                isSelected = selectedItem == 1,
                onClick = { onItemSelected(1) }
            )

            NavigationItem(
                icon = Icons.Filled.AccountCircle,
                label = "Profil",
                isSelected = selectedItem == 2,
                onClick = { onItemSelected(2) }
            )
        }
    }
}