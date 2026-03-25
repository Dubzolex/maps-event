package fr.itii.utils.maps


open class MapElement() {

    var id: Int

    // Variable statique
    companion object {
        var countId = 0
    }

    init {
        this.id = countId
        countId++
    }
}