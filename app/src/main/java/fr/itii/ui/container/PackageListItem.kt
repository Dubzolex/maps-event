package fr.itii.ui.container

class PackageListItem {

    /*
    Pour réussir ton projet d'app d'événements, voici ta "boîte à outils" complète.
    En Jetpack Compose, on ne parle plus de widgets mais de Composables.

    1. La Liste des Éléments Clés (Composables)Voici les éléments indispensables pour ton application :
    ÉlémentRôle dans ton projetParamètres principauxScaffoldStructure globale (TopBar, BottomBar).topBar,
    bottomBar, floatingActionButton, content.ColumnAligner verticalement (Login, Formulaires).
    modifier, verticalArrangement, horizontalAlignment.RowAligner horizontalement (Barre de recherche).
    modifier, horizontalArrangement, verticalAlignment.BoxSuperposer (UI par-dessus la Map).modifier,
     contentAlignment.TextFieldSaisie de texte (Email, Nom Event).value, onValueChange, label,
     visualTransformation.ButtonActions (Valider, Créer).onClick, modifier, enabled, c
     ontent.GoogleMapAfficher la carte.cameraPositionState, modifier, onMapClick.AsyncImageAfficher la photo de profil (via URL Firebase).model, contentDescription, contentScale.2. Exemples d'utilisation des ContainersLa Box : Idéale pour ta MapLa Box permet de mettre des éléments les uns sur les autres. Ici, on met un bouton par-dessus la carte.KotlinBox(modifier = Modifier.fillMaxSize()) {


Écran	Layout Principal	Pourquoi ?
Map	Box	Pour mettre les boutons et la recherche au-dessus de la carte.
Login	Column	Pour aligner le logo, les champs et le bouton du haut vers le bas.
Recherche	LazyColumn	Pour faire défiler une longue liste d'événements sans bugger.
Profil	Column + Row	Column pour l'avatar/nom, et Row pour les stats (ex: "3 événements créés").


    // 1. La couche du dessous (La carte)
    Text("Ici sera ta Google Map", modifier = Modifier.align(Alignment.Center))

    // 2. La couche du dessus (Bouton flottant)
    FloatingActionButton(
        onClick = { /* Action */ },
        modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Ajouter")
    }
}
La Column et le Row : Pour ton formulaireUtilise Column pour le formulaire et Row pour mettre deux boutons côte à côte.KotlinColumn(
    modifier = Modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp) // Espace entre chaque élément
) {
    Text("Créer un Événement", style = MaterialTheme.typography.headlineMedium)

    TextField(value = "", onValueChange = {}, label = { Text("Nom de l'event") })

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = { /* Annuler */ }) { Text("Annuler") }
        Button(onClick = { /* Sauvegarder dans Firebase */ }) { Text("Sauvegarder") }
    }
}
3. Les Paramètres à connaître absolument : Les ModifiersLe Modifier est le paramètre que tu passeras à tous tes éléments. C'est lui qui définit le "Look & Feel" :.fillMaxSize() : Prend tout l'écran..padding(16.dp) : Ajoute de la marge autour..clickable { } : Rend n'importe quel élément (image, texte) cliquable..clip(RoundedCornerShape(8.dp)) : Arrondit les bords d'une image ou d'un container..background(Color.Blue) : Change la couleur de fond.4. Architecture de navigation conseilléePuisque tu as une BottomBar, voici le schéma de ce que tu vas construire :NavHost : C'est le conteneur qui va décider quel écran afficher.Routes : Ce sont des simples textes ("login", "map", "profile") qui servent d'identifiants.Souhaites-tu que je te montre comment créer le "NavHost" pour que tes boutons de barre de navigation fonctionnent réellement et changent d'écran ?

     */
}