const showMenu = async () => {
    try {
        //const response = await fetch("api.php?a=show")
        //const result = await response.json()
        //console.log("Menu:", result)
        //console.log(result.html)
        // document.getElementById("tools").innerHTML =`
        //     <div class="fx-col gap-10 w-200">
        //         <input type="text" id="name" placeholder="Name">
        //     </div>
        // <div class="fx-col gap-10 w-200">
        //     <input type="text" id="email" placeholder="Email">
        // </div>
        // <div class="fx-col gap-10 w-200">
        //     <input type="text" id="password" placeholder="Password">
        // </div>
        // <button id="add">Add</button>`

        document.getElementById("add").addEventListener("click", async () => {
            addUser();
        })

    } catch (e) {
        console.error(e)
    }
}
showMenu()


const addUser = async () => {
    try {
        const post = {
            "email": document.getElementById("email").value,
            "username": document.getElementById("name").value,
            "password": document.getElementById("password").value,
        };

        const response = await fetch(`api.php?a=add`, { // Plus de "&data=" ici
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(post) // Les données partent "cachées" dans le corps
        });

        const result = await response.json()
        console.log("add:", result)

        await showMenu()
        await showListe()
    } catch (error) {
        console.error(error)
    }
}

const showListe = async () => {
    try {
        const response = await fetch("api.php?a=get")
        const result = await response.json()
        console.log("Liste:", result)

        document.getElementById("main").innerHTML =

        `<div class="fx-col gap-20">
            ${result.data.map(item => {
                return `
                    <div id="${item.id}" class="fx-row container jc-between ai-center p-10">
                        <div>#${item.id}</div>
                    
                    <div>${item.name}</div>
                        <div>${item.email}</div>
                        
                        <button class='btn-delete' data-id="${item.id}">Delete</button>
                    </div>`
            }).join("")}
        </div>`

        result.data.map((item) => {
            document.getElementById(item.id).addEventListener("click", async () => {
                deleteUser(item.id)
            })
        })

    } catch (e) {
        console.error(e)
    }
}
showListe()

const deleteUser = async (id) => {
    const response = await fetch("api.php?a=delete&id=" + id)
    const result = await response.json()
    console.log("delete:", result)
    await showListe()
}

const showScreen = async () => {
    try {
        const result = [
            {
                "id": "1",
                "name": "Ecran 1",
                "online": true
            },
            {
                "id": "2",
                "name": "Ecran 2",
                "online": true
            },
            {
                "id": "3",
                "name": "Ecran 3",
                "online": true
            },
            {
                "id": "4",
                "name": "Ecran 4",
                "online": true
            }
            ,
            {
                "id": "5",
                "name": "Ecran 5",
                "online": false
            },
            {
                "id": "6",
                "name": "Ecran 6",
                "online": true
            }
            ,
            {
                "id": "7",
                "name": "Ecran 7",
                "online": true
            },
            {
                "id": "8",
                "name": "Ecran 8"
            },
            {
                "id": "9",
                "name": "Ecran 9"
            }
            ,
            {
                "id": "10",
                "name": "Ecran 10"
            }
            ,
            {
                "id": "11",
                "name": "Ecran 11"
            }
            ,
            {
                "id": "12",
                "name": "Ecran 12"
            }

            

        ]

        const params = new URLSearchParams(window.location.search)
        const screenId = params.get("s")

        document.getElementById("screen").innerHTML =
            `<div class="fx-col p-20 ai-center gap-20">
                <h4>Screens</h4>
                <div class="fx-center">
                    <a href="" class="link">access screen</a>
                </div>
            </div>
            
            
            <div class="fx-col jc-evenly grow  gap-40 py-20">
                ${result?.map(item => {
                    return `
                        <div class="fx-col">
                            <button id="screen-${item.id}" class="${screenId === item.id ? "active" : ""}">
                                ${item.name}
                                <p>online : ${item.online}</p>
                            </button>
                        </div>`
                }).join("")}
            </div>
            `

        result.map((item) => {
            document.getElementById("screen-" + item.id).addEventListener("click", async () => {
                if(screenId === item.id) {
                    window.location.href = "index.html"
                } else {
                    window.location.href = "index.html?s=" + item.id
                }


            })
        })

    } catch (e) {
        console.error(e)
    }
}
await showScreen()


