@startuml
!include <tupadr3/common>
!include <tupadr3/devicons/mysql>!define Database(iconUrl) <img:iconUrl>

skinparam componentStyle uml2
skinparam backgroundColor #FAFAFA

rectangle "Funzo" #FFC300 {
component "Backend" as proxy {

}

DEV_MYSQL(db,Mysql,database,red)
component "Mobile App" as app {
[Authentication] as auth
[Subject Selection] as subject
[Dashboards]
[Quiz] as quiz
subject -down-> quiz
}
}

component "Firebase" as firebase {
}


app <-left-> proxy: Sends requests
proxy <--> db: Manages

note right of firebase: User Authentication

firebase <--> auth : Handles authentication

@enduml
```
