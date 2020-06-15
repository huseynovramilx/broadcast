# broadcast
An application for broadcasting data in rooms for your contacts.

# Installation
After downloading repository you need to do some configurations to start the project.

## Creating user and database in MYSQL
For this run the following SQL:

```
create database rooms_broadcast;
create user 'username'@'%' identified by 'password';
use rooms_broadcast;
grant all on rooms_broadcast.* to 'username'@'%';
```
_If you change **'username'** and **'password'** in the above code, make sure to update **src/main/resources/application.properties** with the same data_

## Run the application
Run the following lines in commmand prompt with directory set to this project.
```
mvnw clean package
java -jar target/energy-consumption-0.0.1-SNAPSHOT.jar
```

## (Optional) You can fill the database with fake data
For testing the system, you can fill the database with fake data. You need to run the following sql in MYSQL
```
use rooms_broadcast;

insert into user(username) values("user1");
insert into user(username) values("user2");
insert into user(username) values("user3");
insert into user(username) values("user4");
insert into user(username) values("user5");

insert into contacters_contacts(contacter_id, contact_id) values(1,2);
insert into contacters_contacts(contacter_id, contact_id) values(1,3);
insert into contacters_contacts(contacter_id, contact_id) values(1,4);
insert into contacters_contacts(contacter_id, contact_id) values(1,5);
insert into contacters_contacts(contacter_id, contact_id) values(2,3);
insert into contacters_contacts(contacter_id, contact_id) values(2,4);
insert into contacters_contacts(contacter_id, contact_id) values(2,5);
insert into contacters_contacts(contacter_id, contact_id) values(3,4);
insert into contacters_contacts(contacter_id, contact_id) values(3,5);
insert into contacters_contacts(contacter_id, contact_id) values(4,5);

insert into room(owner_id) values(1);
insert into room(owner_id) values(2);
insert into room(owner_id) values(3);
insert into room(owner_id) values(4);
insert into room(owner_id) values(5);
```

# Usage
## Adding user
### Request example
```
POST http://localhost:8080/users
Content-type: application/json
RequestBody
{
    "username": "user6"
}
```
### Response example
```
{
    "id": 6,
    "username": "user6",
    "rooms": []
}
```

## Adding contacts
### Request example
```
POST http://localhost:8080/users/3/contacts
Content-type: application/json
RequestBody
{
    "contactId":2
}
```
### Response example
```
{
    "id": 2,
    "username": "user2",
    "rooms": [
        {
            "id": 2
        }
    ]
}
```
## Adding rooms
### Request example
```
POST http://localhost:8080/users/1/rooms
Content-type: application/json
```
### Response example
```
{
    "id": 6
}
```
## Getting user
### Request example
```
GET http://localhost:8080/users/1
Content-type: application/json
```
### Response example
```
{
    "id": 1,
    "username": "user1",
    "rooms": [
        {
            "id": 6
        },
        {
            "id": 1
        }
    ]
}
```
## Getting rooms of User
### Request example
```
GET http://localhost:8080/users/1/rooms
Content-type: application/json
```
### Response example
```
[
    {
        "id": 1
    },
    {
        "id": 6
    }
]
```
## Getting contacters(users which has User in their contacts list) of User
### Request example
```
GET http://localhost:8080/users/5/contacters
Content-type: application/json
```
### Response example
```
[
    {
        "id": 3,
        "username": "user3",
        "rooms": [
            {
                "id": 3
            }
        ]
    },
    {
        "id": 1,
        "username": "user1",
        "rooms": [
            {
                "id": 1
            },
            {
                "id": 6
            }
        ]
    },
    {
        "id": 2,
        "username": "user2",
        "rooms": [
            {
                "id": 2
            }
        ]
    },
    {
        "id": 4,
        "username": "user4",
        "rooms": [
            {
                "id": 4
            }
        ]
    }
]
```
## Getting contacts of User
### Request example
```
GET http://localhost:8080/users/1/contacts
Content-type: application/json
```
### Response example
```
[
    {
        "id": 3,
        "username": "user3",
        "rooms": [
            {
                "id": 3
            }
        ]
    },
    {
        "id": 2,
        "username": "user2",
        "rooms": [
            {
                "id": 2
            }
        ]
    },
    {
        "id": 4,
        "username": "user4",
        "rooms": [
            {
                "id": 4
            }
        ]
    },
    {
        "id": 5,
        "username": "user5",
        "rooms": [
            {
                "id": 5
            }
        ]
    }
]
```

## Broadcasting data
### Login as owner of the room
In browser go to the one of the rooms. For example: http://localhost:8080/rooms/1. 
You will be redirected to login page, login as owner of the room. **'username: user1'** and **'password: 1234'**

Press **'connect'** to connect to the server

### Login as contact
In another browser go to the http://localhost:8080/rooms/1 and login as one of the contacts. For example: **'username: user2'** and **'password: 1234'**
Press **'connect'** to connect to the server

### Broadcast data
 Go to the previous browser as owner and send data.
