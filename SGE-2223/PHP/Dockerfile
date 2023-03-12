FROM php:apache
COPY ./htdocs /var/www/html
RUN docker-php-ext-install mysqli pdo pdo_mysql
EXPOSE 80