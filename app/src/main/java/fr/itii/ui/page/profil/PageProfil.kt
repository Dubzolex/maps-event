package fr.itii.ui.page.profil

private val PageProfil.auth: Any

class PageProfil {

fun pageProfil() {
    val user = auth.currentUser


    if (user != null) {
        Login(user.email)
    } else {
        Account()
    }
}

}