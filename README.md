# I5Java

Outils nécessaire : 
- Docker 
- IDE Java
- Pluggin Spring
- Java version 17 

1 - Setup BDD

Se placer dans le dossier et faire la commande **docker compose up -d**

Se connecter via l'adminer à la BDD : http://localhost:8080

- user : root 
- password : example

Il faut créer manuellement la base de données appelé : **Cours**

<img width="613" alt="Capture d’écran 2022-11-22 à 10 40 39" src="https://user-images.githubusercontent.com/83949838/203280063-a0596b67-f60d-4f23-a22b-6c7e27b9c6b4.png">

2 - IntelliJ IDEA

Une fois le projet ouvert, veuillez utiliser la commande **mvn clean install -DskipTests** dans l'onglet Maven.
Dans un second temps, éxécutez le programme RiocApplication.

Le lien vers le swagger devrait maintenant être disponible : http://localhost:8081/swagger-ui/index.html
Vous pouvez tester les différents controleurs : 
- hello : afin de voir si la connexion est OK 
- account : plusieurs endpoint disponible (post, delete all, get...)
- bank detail : endpoint get / post / delete afin de créer un iban sur un ID d'account existant.

Tout fonctionne bien, cependant je n'ai pas fait le chiffrage AES128.

