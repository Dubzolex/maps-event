<?php

class Database {

private static $instance = null;
private $pdo = null;

public function __construct() {
    $this->pdo = new PDO(
            "mysql:host=db;dbname=test;charset=utf8",
            "test", "test",
            [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]
    );
}

public function createTable() {
    $sql = "CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    username VARCHAR(255), 
    email VARCHAR(255), 
    password VARCHAR(255), 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    )";

    $this->pdo->exec($sql);
    echo "Table created!\n";
}

public function addUser($data) {
    try {

    $columns = array_keys($data);
    $placeholders = array_map(function($col) {
        return ":" . $col;
    }, $columns);

    $set = [];
    foreach ($data as $key => $value) {
        $set[] = "$key = :$key";
    }

    $sql = "INSERT INTO users (" . implode(",", $columns) . ")
        VALUES (" . implode(",", $placeholders) . ")";

    $stmt = $this->pdo->prepare($sql);
    return $stmt->execute($data);
    } catch (PDOException $e) {
        echo $e->getMessage();
    }
}

public function updateUser($data) {
    try {


    $id = $data["id"];
    unset($data["id"]);

    $set = [];
    foreach ($data as $key => $value) {
        $set[] = "$key = :$key";
    }

    $sql = "UPDATE users SET " . implode(",", $set) . " WHERE id = :id";

    $data["id"] = $id;

    $stmt = $this->pdo->prepare($sql);
    return $stmt->execute($data);
    } catch (PDOException $e) {
        return [
            "success" => false,
            "message" => $e->getMessage()
        ];
    }
}

public function getUsers() {
    try {
        $sql = "SELECT * FROM users";

        $stmt = $this->pdo->prepare($sql);
        $stmt->execute();

        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
        return [
            "success" => false,
            "message" => $e->getMessage()
        ];
    }
}

public function getUserById($id) {
    try {
        $sql = "SELECT * FROM users WHERE id = :id";

        $stmt = $this->pdo->prepare($sql);
        $stmt->execute(["id" => $id]);

        return $stmt->fetch(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
        return [
            "success" => false,
            "message" => $e->getMessage()
        ];
    }

}


public function getUserByEmail($email) {
    try {
        $sql = "SELECT * FROM users WHERE email = :email";

        $stmt = $this->pdo->prepare($sql);
        $stmt->execute(["email" => $email]);

        return $stmt->fetch(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
        return [
            "success" => false,
            "message" => $e->getMessage()
        ];
    }

}

















    public function deleteUserById($id) {
        try {
            $sql = "DELETE FROM users WHERE id = :id";

            $stmt = $this->pdo->prepare($sql);
            $stmt->execute(["id" => $id]);

            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (PDOException $e) {
            return [
                "success" => false,
                "message" => $e->getMessage()
            ];
        }

    }



}


$db = new Database();
$user = [
    'email'=> "test",
    'password'=> "test",
    'username'=> "test",
];

//$db->addUser($user);