<?php
//header('Content-Type: application/json');

require_once "db.php";

$action = $_GET['a'] ?? null;


header('Content-Type: application/json');

switch ($action) {
    case "show":
        echo json_encode(menu());
        break;

    case "get":
        echo json_encode(liste());
        break;

    case "add":
        echo json_encode(add());
        break;

    case "delete":
        echo json_encode(deleteById());
        break;

    default:
        echo json_encode([
            "html" => "<div class='error'>Une erreur est survenue ou page inconnue</div>"
        ]);
        break;
}


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