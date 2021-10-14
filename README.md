
Pull the MySQL Docker Image

    $ docker pull mysql:latest

Start a MySQL server instance:

    $ docker run -p 3306:3306 --name some-mysql -e MYSQL_ROOT_PASSWORD=pass -d mysql:latest
    $ docker start some-mysql

Create schema `user_schema`

    $ docker exec -i some-mysql mysql -uroot -ppass  <<< "create database user_schema;"

