FROM php:8.2-fpm

# Cette commande installe l'extension PDO pour MySQL
RUN docker-php-ext-install pdo pdo_mysql