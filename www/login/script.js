const showStatus = async (data, element = "status") => {
    const { alert, message, error, success } = data
    if(alert) {
        console.error(error)
        alert(message)
        return
    }

    const div = document.getElementById(element)
    if(div){
        div.classList.remove("green")
        div.classList.remove("red")
        div.innerHTML = message
        div.classList.add(`${success ? "green" : "red"}`)
        await new Promise(resolve => setTimeout(resolve, 10000))
        div.innerHTML = ""
    }
}


const connect = async () => {
    try {
        const post = {
            "email": document.getElementById("email").value,
            "password": document.getElementById("password").value
        }

        const response = await fetch(`/login/login.php?a=connect`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(post)
        });

        const result = await response.json()

        showStatus(result)

        console.log(result)
    } catch(e) {
        console.error(e)
    }
    
}

document.getElementById("login").addEventListener("click", () => {
    connect()
})