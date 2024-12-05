* DATABASE
-docker container
First login to mysql:
docker run -d -p 3301:3306 --name AirlineReservation -e MYSQL_ROOT_PASSWORD=admin mysql:latest
in container is created a database name : AirlineReservation
mysql -u root -p
admin
create database AirlineReservation;
then run AirlineReservation project

From second time login to sql:
mysql -u root -p
admin
——
show databases;
use AirlineReservation;
——
Show tables;
——