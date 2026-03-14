
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.10/firebase-app.js";
import { getFirestore, doc, setDoc, getDoc, getDocs, updateDoc, collection, onSnapshot, addDoc, deleteDoc } from "https://www.gstatic.com/firebasejs/9.6.10/firebase-firestore.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/9.6.10/firebase-auth.js";
import { getFunctions, httpsCallable } from "https://www.gstatic.com/firebasejs/9.6.10/firebase-functions.js";

import { db } from "./index.js"

const formData = {
    "users": [
        
        {
            "key": "userName",
            "type": "text",
        },
        {
            "key": "lastName",
            "type": "text",
        },
        {
            "key": "firstName",
            "type": "text",
        },
        {
            "key": "email",
            "type": "text",
        },
        {
            "key": "createdAt",
            "value": new Date().toISOString()
        },
        {
            "key": "updateAt",
            "value": new Date().toISOString()
        },
        {
            "key": "id",
            "value": crypto.randomUUID()
        }

    ],
    "cities": [
        
        {
            "key": "code",
            "type": "text",
        },
        {
            "key": "name",
            "type": "text",
        },
        {
            "key": "latitude",
            "type": "number"
        },
        {
            "key": "longitude",
            "type": "number"
        },
        {
            "key": "id",
            "value": crypto.randomUUID()
        }
    ],
    "events": [
        
        {
            "key": "title",
            "type": "text",
        },
        {
            "key": "subTitle",
            "type": "text",
        },
        {
            "key": "latitude",
            "type": "number"
        },
        {
            "key": "longitude",
            "type": "number"
        },
        {
            "key": "date",
            "value": new Date().toISOString()
        },
        {
            "key": "id",
            "value": crypto.randomUUID()
        }
    ]
}


const params = new URLSearchParams(window.location.search);
const colparam = params.get("collection")
const actionparam = params.get("action")

for(let item of ["users", "events", "cities"]) {
    document.getElementById(item).addEventListener("click", () => {
        window.location.href = `./?collection=${item}&action=${actionparam}`
    })
}

for(let item of ["create", "show"]) {
    document.getElementById(item).addEventListener("click", () => {
        window.location.href = `./?collection=${colparam}&action=${item}`
    })
}

try {
    document.getElementById(colparam).classList.add("active")
    document.getElementById(actionparam).classList.add("active")

} catch(e) {
    window.location.href = `./?collection=users&action=create`
}

const create = async (col) => {
    const form = document.getElementById("form")
    let data = formData[col]

    form.innerHTML = `
    <div class="fx-col jc-between form">
        <div class="fx-col jc-evenly grow">
            ${data.filter(i => i.type).map(i => {
                return `
                <div class="fx-col gap-10">
                    <div class="fx-row">${i.key} :</div>
                    <input id="${i.key}" type="${i.type}"/>
                </div>`
                }).join("")}
        </div>
        <div class="fx-col ai-center">
            <button id="save">Save</button>
        </div>
    </div>
    `
    document.getElementById("save").addEventListener("click", () => save(colparam))
}



const save = async (col) => {
    let data = formData[col]

    console.log(data)

    let post = {}

    for(let f of data) {
        const div = document.getElementById(f.key)
        if(div) {
            post[f.key] = div.value
        } else {
            post[f.key] = f.value || null
        }
    }

    console.log(post)

    const uuid = data.find(p => p.key == "id").value
    console.log(uuid)

    await setDoc(doc(db, col, uuid), { 
        ...post
    })
}





const show = async (col) => {
    const form = document.getElementById("form")
    let keys = Object.values(formData[col].map(p => p.key))
    console.log(keys)

    const colRef = collection(db, col);
    const querySnapshot = await getDocs(colRef);
    const documents = querySnapshot.docs.map(doc => ({      // On récupère l'identifiant unique
        ...doc.data()    // On déverse toutes les propriétés de l'objet
    }));

    console.log(documents)

    form.innerHTML = `
    <div class="fx-col scroll grow">
        <table>
            <thead>
            ${keys.map(k => {
                return `
                <th>${k}</th>`
            }).join("")}
            </thead>
            <tbody>
            ${documents.map(doc => {
                return `
                    <tr>
                        ${keys.map(k => {
                            return `
                                <td>
                                    ${doc[k] ?? ""}
                                </td>`
                        }).join("")}
                    `
            }).join("")}
            </tbody>
        </table>
    <div>
    `
    
    
    
    
}



switch(actionparam){
    case "create":
        create(colparam)
        break;
    default:
        show(colparam)
    
}