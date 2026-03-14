<?php
//header('Content-Type: application/json');

require_once "../db.php";

$action = $_GET['a'] ?? null;
$type = $_GET['t'] ?? null;

// ==== action ====
//connect -> verifier good email and password
//change -> envoyer interface credentials
//          //type
//              -> password
//              -> onboard
//              -> name
//update -> recuperer les nouvelles donnes de credentials
//show -> affiche la page account


header('Content-Type: application/json');

switch ($action) {
    // verifier email et password
    case 'connect': 
        echo json_encode(connect());
        break;

    // montrer la page account
    case "show": 
        echo json_encode(show());
        break;

    // montrer la page des credentials
    case "edit": 
        echo json_encode(edit());
        break;

    // mettre à jour les détails user
    case "save": 
        echo json_encode(save());
        break;

    default:
        echo json_encode([
            "html" => "<div class='error'>Une erreur est survenue ou page inconnue</div>"
        ]);
        break;
}


function connect() {
    try {
        $json = file_get_contents('php://input');
        $data = json_decode($json, true);

        $email = $data['email'] ?? null;
        $password = $data['password'] ?? null;

        if(empty($email) or empty($password)) {
            return [
                "success" => false,
                "message" => "Remplir tous les champs.",
            ];
        }

        $db = new Database();
        $user = $db->getUserByEmail($email);
        if(empty($user)) {
            return [
                "success" => false,
                "message" => "Aucun utilisateur trouvé."
            ];
        }

        if ($user && password_verify($password, $user['password'])) {
            try {
                $token = bin2hex(random_bytes(32));
                //save token
                return [
                    "success" => true,
                    "message" => "Connexion réussie.",
                    "data"=> [
                        "token" => $token,
                        "hash" => $user['password']
                    ]
                ];
            } catch (Exception $e) {
                return [
                    "success"=> false,
                    "error"=> $e->getMessage(),
                    "message" => "Session perdu.",
                    "alert" => "code 7625"
                ];
            }

        } else {
            return [
                "success" => false,
                "message" => "Identifiants invalides."
            ];
        }

    } catch(Exception $e) {
        return [
            "success" => false,
            "error" => $e->getMessage(),
            "message" => $e->getTraceAsString(),
            "alert" => "code 4586"
        ];
    }
}

function show() {

}

function edit() {

}

function save() {

}





/*
function menu() {
    return [
        "html" => ""
        
    ];
}

function liste() {
    try {
        $db = new Database();
        $users = $db->getUsers();

        return [
            "html" => "",
            "data" => array_map(function ($user) {
                return ['id' => $user['id'],
                    'name' => $user['username'],
                    'email' => $user['email'],
                    'createdAt' => $user['created_at'],
                    'updatedAt' => $user['updated_at'],
                ];
            }, $users)
        ];

    } catch (Exception $e) {
        return [
            "success" => false,
            "html" => "<div class='error'>Une erreur est survenue</div>"
        ];
    }
}

function add() {
    try {
        $db = new Database();
        $json = file_get_contents('php://input');
        $data = json_decode($json, true);

        if(!$data){
            return [
                "success" => false,
                "message" => "aucune données"
            ];
        }

        if (isset($data['password'])) {
            // On remplace le texte clair par le hash
            $data['password'] = password_hash($data['password'], PASSWORD_DEFAULT);
        }


        $db->addUser($data);
        return [
            "success" => true,
            "message" => "user ajouter",
            "user" => $data["username"]
        ];
    } catch (PDOException $e) {
        return [
            "success" => false,
            "message" => $e->getMessage(),
            "code" => $data["username"]
        ];
    }

}

function deleteById() {
    try {
        $db = new Database();
        $db->deleteUserById($_GET['id'] ?? null);
        return [
            "success" => true,
            "message" => "user supprimer"
        ];

    } catch (PDOException $e) {
        return [
            "success" => false,
            "message" => $e->getMessage()
        ];
    }
}

*/