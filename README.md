#Web service for search at ria.com with subscription for updates.


###Api endpoint: `http://ec2-54-93-227-37.eu-central-1.compute.amazonaws.com/`

---
Technologies
-

Java Core, PostgresQL, Spring (MVC, Data, Security), Spring Boot, Gradle, Git, Swagger. 

---
##API registration

###POST /registration

Input is passed as JSON encoded object in request body. New users get status `NEW` and wait for admin validation. 

Output is always JSON encoded object.

### Input:

* `firstName`

  User first name.

  *Validation*: **required**, text.

* `lastName`

  User last name.

  *Validation*: **optional**, text.

* `nickname`

  User nickname.

  *Validation*: **required**, text.

* `age`

  User age.

  *Validation*: **required**, numeric.

* `email`

  User email.

  *Validation*: **required**, email.

* `password`

  User password.

  *Validation*: **required**, text.

-
##Authorization
###POST /login

Input is passed as query parameters 
`?login=*{login}*?password=*{password}*`

###GET /logout


##Admin level resources


###GET /admin/users
Return list of all users

###GET  /admin/usersNew
Return list of users with status NEW

###GET /admin/usersActive
Return list of users with status ACTIVE

###GET /admin/usersDisabled
Return list of users with status DISABLED

###GET /admin/details/{id}
Return user with {id}

###PUT /admin/update
Accept {json} and update all user details! Return user

###PATCH /admin/updateRole/{id}
Update role for user with {id}. 
Passed as query parameter 
`?role=*{role}*`.
Return user

###PATCH /admin/updateStatus/{id}
Update status for user with {id}. 
Passed as query parameter 
`?status=*{status}*`.
Return user

###DELETE /admin/delete/{id}
Delete user with {id}


##User level resources


###GET /user/get
Return logged in user

###GET /user/searchHistory
Return search history for user

###PUT /user
Accept {json} and update general user data

###PUT /user/subscribe
Subscribe user for specific search request.
Passed as query parameter 
`?requestId=*{requestId}*`.

###PUT /user/unsubscribe
Unsubscribe user for search request updates
Passed as query parameter 
`?requestId=*{requestId}*`.

###DELETE /delete
Delete current user

##Search level resources

###POST /search/list
Accept {json} for search request. Return list of posts by met parameters
Model:

    {
        "categoryId":"integer($int32)", (required)
        "autoData":
            [
                {
                "bodyId":"integer($int32)", (required)
                "markaId":"integer($int32)", (required)
                "modelId":"integer($int32)",
                "yearsFrom":"integer($int32)",
                "yearsTo":"integer($int32)"
                }
            ],
            "currency":"integer($int32)",
            "priceOt":"integer($int32)",
            "priceDo":"integer($int32)",
            "locationIds":[
                {
                "state":"integer($int32)",
                "city":"integer($int32)"
                }
            ],
        "colorId":"integer($int32)",
        "gearboxIds":[
            {"gearboxId":"integer($int32)"}
            ],
        "fuelTypeIds":[
            {"fuelType":"integer($int32)"}
            ],
        "driveTypesIds":[
            {"driveTypeId":"integer($int32)"}
            ],
        "top":"integer($int32)",
        "countpage":"integer($int32)"
    }

Return list of posts

###GET /search/autoId
Return detailed information for post with {id}

##Methods level resources
Return {json} in format:

    [
        {
        "key":"integer"
        "value":"String"
        }
    ]


###GET /method/category
Return categories

###GET /method/bodystyle/{categoryId}
Return bodystyle by category

###GET /method/marks/{categoryId}
Return auto brands by category

###GET /method/autoModel/{categoryId}/marka/{markaId}
Return auto models by brand in category

###GET /method/colors
Return colors

###GET /method/driveType/{categoryId}
Return drive types by category

###GET /method/fuelTypes
Return fuel types

###GET /method/gearbox/{categoryId}
Return gearboxes by category

###GET /method/state
Return states

###GET /method/cities/{stateId}
Return cities by state

###GET /method/currencies
Return currencies

###GET /method/ticketSubmission
Return keys for filtering search by time



#Models
-
UserStatus:

- NEW - new registered user
- ACTIVE - user approved and activated by admin
- DISABLED - user disabled by admin

UserRole:

- ROLE_ADMIN - allowed all operation, can change user roles
- ROLE_USER - allowed */search, /method, /user*

Model User:

    {
        "id": "integer($int32)",
        "firstName": "String",
        "lastName": "String",
        "age": "integer($int32)",
        "nickname": "String",
        "email": "String",
        "password": "String",
        "registerTime": "long($int64)",
        "userRole": "String", (Enum: [ ROLE_USER, ROLE_ADMIN ])
        "userStatus": "String", (Enum: [  NEW, ACTIVE, DISABLED ])
        "searchHistory": []
    }
    
Model SearchRequest:

    {
        "categoryId":"integer($int32)",
        "autoData":[],
        "currency":"integer($int32)",
        "priceOt":"integer($int32)",
        "priceDo":"integer($int32)",
        "locationIds":[
            {
            "state":"integer($int32)",
            "city":"integer($int32)"
            }
            ],
        "colorId":"integer($int32)",
        "gearboxIds":[
            {"gearboxId":"integer($int32)"}
            ],
        "fuelTypeIds":[
            {"fuelType":"integer($int32)"}
            ],
        "driveTypesIds":[
            {"driveTypeId":"integer($int32)"}
            ],
        "top":"integer($int32)",
        "countpage":"integer($int32)"
    }
    
Modes SearchHistory

    {
                "id": "integer($int32)",
                "userId": "integer($int32)",
                "categoryId": "integer($int32)",
                "autoData": [],
                "currency": "integer($int32)",
                "priceOt": "integer($int32)",
                "priceDo": "integer($int32)",
                "locationIds": [
                    {
                        "id": "integer($int32)",
                        "state": "integer($int32)",
                        "city": "integer($int32)"
                    }
                ],
                "colorId": "integer($int32)",
                "gearboxIds": [
                    {
                        "id": "integer($int32)",
                        "gearboxId": "integer($int32)"
                    }
                ],
                "fuelTypeIds": [
                    {
                        "id": "integer($int32)",
                        "fuelType": "integer($int32)"
                    }
                ],
                "driveTypesIds": [
                    {
                        "id": "integer($int32)",
                        "driveTypeId": "integer($int32)"
                    }
                ],
                "top": "integer($int32)",
                "countpage": "integer($int32)",
                "timeCreated": "long($int64)",
                "subscription": "boolean"
    }
    
Model AutoData:

    {
        "id": "integer($int32)",
        "bodyId": "integer($int32)",
        "markaId": "integer($int32)",
        "modelId": "integer($int32)",
        "yearsFrom": "integer($int32)",
        "yearsTo": "integer($int32)"
    }
  

---
*DataBase*
-

File: resourcesDDL.sql contains database configuration and basic data for tables located in 
resources/db/DML.sql. 


run on aws:
sudo nohup java -jar -Dspring.profiles.active=$SPRING_PROFILE -Daws.access.key=$ACCESS_KEY -Daws.access.secret=$ACCESS_SECRET -Dlog.path=$LOG_PATH -Ddevelopers.ria.com.api.key=$RIA_API_KEY -Dspring.mail.username=$MAIL_USERNAME -Dspring.mail.password=$MAIL_PASSWORD -Dmail.default.from=$MAILING_FROM -Dcron.scheduler='0 20 11 * * ?' riasurfing-0.0.1-SNAPSHOT.war &