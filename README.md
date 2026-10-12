# Médiathèque — POO avancée, séance 2

Projet Java 17 / JUnit 5, sans déclaration `package` pour rester facile à intégrer au projet fourni.

## Lancer les tests
Depuis ce dossier :
```bash
mvn test
```

## Choix de conception
- Les exceptions métier sont **checked** (`extends Exception`) : un emprunt peut échouer pour une raison métier prévisible, donc l'appelant doit traiter ou déclarer le problème.
- `Bibliothecaire` possède un `Catalogue<Document>` : composition, car un bibliothécaire n'est pas un catalogue.
- `EmpruntManager` délègue la recherche au catalogue et applique les règles d'emprunt.
- `CatalogueCorrompuException` conserve l'`IOException` comme cause.
