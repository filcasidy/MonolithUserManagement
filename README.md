# User-Management
## 1. Einleitung
In diesem Gruppenprojekt geht es um die Entwicklung eines Microservices, der an einen Webshop (Monolith) angebunden werden soll. Konkret handelt es sich dabei um den Prototyp eines User-Managements für den Monolithen.

## 2. Unterstützte Use-Cases
Im Folgenden werden die Use-Cases vorgestellt, die der Microservice User-Management unterstützt.

#### Nutzer Registrierung
Die Nutzer können sich im Webshop registrieren. Dabei sind ein Nutzername und ein Passwort verpflichtend anzugeben. Personendaten, wie z. B. Vorname und Nachname, sind optionale Angaben.

#### Login / Logout
Ist ein Nutzer registriert, so kann er sich mit seinem Username und Passwort einloggen. Im eingeloggten Zustand hat er Zugriff auf seine persönlichen Daten. Zudem kann er sich wieder ausloggen, um die Session zu beenden. Ein eingeloggter Nutzer ist solange eingeloggt, bis er sich manuell ausloggt oder bis ein Timeout die Session beendet.

#### Person- / User-Data anzeigen und anpassen
Die Personen- und Nutzerdaten können im eingeloggten Zustand angezeigt werden. Dazu können die Daten angepasst und gespeichert werden.

## 3. Verwendete Technologien
Nachfolgend werden die Technologien erläutert, die in diesem Microservice verwendet werden.

Durch die Nutzung von **Spring Boot** wird ein einfaches Starten und Initialisieren der Anwendung ermöglicht.
Mit **Spring Security** wird der Microservice abgesichert. Hierbei wird es konkret dazu verwendet, die Requests, die an den Microservice geschickt werden, abzufangen und zu überprüfen.
Das User-Management kann per REST-Aufrufe angesprochen werden. Dazu wird **Spring Web** verwendet.
Für die Persistierung der Daten ist eine **H2-Datenbank** implementiert, auf die per **Spring Data** zugegriffen werden kann.
Die Dokumentation der REST-Routen ist mit **Swagger** realisiert.

## 4. Architektur
Der Microservice ist in einer Drei-Schichten-Architektur aufgebaut.
- 3 Schichten -> 3 packages
- Zugriff von oben nach unten
- 1. controller (Rest) -> Zugriff per REST von außen, controller greifen auf service Schicht zu
- 2. service -> greifen auf Repositories in domain Schicht zu 
- 3. domain -> Persistenzschicht, Abbildung der Entitäten (User, Person), Zugriff auf die Datenbank

## 5. Dokumentation

## 6. Teststruktur

## 7. Lokales Starten der Anwendung
Um den Monolith im Zusammenspiel mit dem Microservice lokal zu testen, müssen zunächst folgende Git-Repositories ausgecheckt werden:

https://gitlab.com/filcasidy/monolith (Monolith)

https://github.com/filcasidy/MonolithUserManagement (Microservice)

Der Monolith wird, wie auf der Gitlab-Seite beschrieben, gestartet. Der Microservice wird über die Klasse /src/main/java/com/monolith/UserManagementApplication.java gestartet. Zu beachten ist, dass der Microservice auf Port 8083 und der Monolith auf Port 8443 läuft.

Sind beide Anwendungen gestartet, kann der Webshop über folgenden Link erreicht werden:

https://localhost:8443/
