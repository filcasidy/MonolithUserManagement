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
Der Microservice ist in einer Drei-Schichten-Architektur aufgebaut, die sich aus den Projekt-Packages *controller*, *service* und *domain* zusammensetzen. Der Zugriff geschieht bei den Schichten immer von oben nach unten.

**1. Controller:** Die oberste Schicht (*controller*) wird von außen per REST angesprochen und dient so als Schnittstelle zu anderen Anwendungen (in diesem Fall der Monolith).

**2. Service:** Die mittlere Schicht (*service*) beinhaltet die Logik und wird von den Controllern genutzt, um Requests zu verarbeiten. Die Services bestehen aus Interfaces und entsprechenden Implementierungen. Genutzt werden in den Controllern die Interfaces, wodurch die Implementierung bei Bedarf einfach ausgetauscht werden kann.

**3. Domain:** In der untersten Schicht (*domain*) erfolgt die Persistierung. Hier werden die Entitäten (User, Person) abgebildet und Datenbankoperationen ausgeführt. Die Services greifen auf die Repositories in der Persistenzschicht zu, um Daten in der Datenbank abzurufen oder anzupassen. Als Repository wird das *JpaRepository*-Interface von Spring Data genutzt. Die Daten werden zur Laufzeit in der H2-Datenbank gespeichert.

## 5. Sicherheit
Um den Microservice abzusichern, wird Spring Security eingesetzt. Dabei muss zwischen Client- und Server-Seite unterschieden werden.

**Client**

Auf dem Client (Webshop) wird Spring Security verwendet, um die Sessions zu verwalten. Wenn sich ein Nutzer einloggt, wird ein *Authentication*-Objekt mit Nutzername und Passwort in der Session gespeichert. Bei einem Logout oder Timeout wird die Session invalidiert. Als Authentifizierungsmethode wird Basic Authentication genutzt. Dabei werden bei jedem Request die Nutzerdaten (Nutzername, Passwort) mitgeschickt, um den Nutzer zu authentifizieren.
Weiterhin ist der Client durch HTTPS verschlüsselt.

**Server**

Auf dem Server (Microservice) ist ein *AuthenticationProvider* implementiert, der bei einem Request überprüft, ob der Nutzer authentifiziert ist. 
Um die Passwörter verschlüsselt zu speichern, wird der *BCryptPasswordEncoder* von Spring Security verwendet. So stehen die Passwörter nicht als Klartext in der Datenbank und können nicht einfach ausgelesen werden.

## 6. Dokumentation
Jede (public) Methode ist mit JavaDoc annotiert. Durch das Generieren des JavaDoc´s kann die Public-API gelesen werden. Zusätzlich können in dem Microservice alle REST-Routen mit Hilfe des Framework Swagger angeschaut werden.

## 7. Teststruktur
Der Monolith und der Microservice (MonolithUserManagement) wurden prototypisch mit Hilfe des Frameworks Pact (https://docs.pact.io/) getestet. Das Testframework unterstützt das "Consumer-Driven Contracts" Pattern. Der Consumer ist der Monolith und der Provider ist der Microservice. Es wurde die Route *getUser()* getestet. Der Test befindet sich unter src/test/java/de/mstock/monolith/web/EditUserControllerTest.java. Dadurch wird der Contract generiert, der sich unter src/test/resources/pacts/ befindet. Der generierte Contract wird bei dem Provider im Test unter src/test/java/com/monolith/controller/UserControllerTest.java getestet.

## 8. Lokales Starten der Anwendung
Um den Monolith im Zusammenspiel mit dem Microservice lokal zu testen, müssen zunächst folgende Git-Repositories ausgecheckt werden:

https://gitlab.com/filcasidy/monolith (Monolith)

https://github.com/filcasidy/MonolithUserManagement (Microservice)

Der Monolith wird, wie auf der Gitlab-Seite beschrieben, gestartet. Der Microservice wird über die Klasse /src/main/java/com/monolith/UserManagementApplication.java gestartet. Zu beachten ist, dass der Microservice auf Port 8083 und der Monolith auf Port 8443 läuft.

Sind beide Anwendungen gestartet, kann der Webshop über folgenden Link erreicht werden:

https://localhost:8443/
