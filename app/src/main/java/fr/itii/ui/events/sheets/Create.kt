package fr.itii.ui.events.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import fr.itii.domain.models.collections.Events
import fr.itii.ui.components.ActionButton
import fr.itii.ui.components.InputField
import fr.itii.ui.components.LinkButton
import fr.itii.ui.components.NeutralButton


@Composable
fun CreateEventSheet(
    location: LatLng,
    onCreate: (Events) -> Unit,
    onClose: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var ville by remember { mutableStateOf("") }
    var adresse by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf(location.latitude.toString()) }
    var longitude by remember { mutableStateOf(location.longitude.toString()) }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Créer un événement")
        }


        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {


                InputField(label = "Nom", value = name, onValueChange = { name = it }, modifier = Modifier.fillMaxWidth())
                InputField(label = "Description", value = description, onValueChange = { description = it },modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        ,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    InputField(label = "Type", value = type, onValueChange = { type = it }, modifier = Modifier.weight(1f))
                    InputField(label = "Date", value = date, onValueChange = { date = it }, modifier = Modifier.weight(1f))
                }

                InputField(label = "Ville", value = ville, onValueChange = { ville = it }, modifier = Modifier.fillMaxWidth())
                InputField(label = "Adresse", value = adresse, onValueChange = { adresse = it }, modifier = Modifier.fillMaxWidth())

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    InputField(label = "Latitude", value = latitude, onValueChange = { latitude = it }, modifier = Modifier.weight(1f))
                    InputField(
                        label = "Longitude",
                        value = longitude,
                        onValueChange = { longitude = it },
                        modifier = Modifier.weight(1f))
                }



            }
        }

        item {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                NeutralButton(
                    text = "Annuler",
                    onClick = onClose
                );

                ActionButton(
                    text = "Créer",
                    onClick = {
                        val newEvent = Events(
                            name = name,
                            description = description,
                            type = type,
                            date = date,
                            ville = ville,
                            adresse = adresse,
                            latitude = latitude.toDouble(),
                            longitude = longitude.toDouble(),
                        )

                        onCreate(newEvent)
                        onClose()
                    }
                )
            }

        }

    }
}


