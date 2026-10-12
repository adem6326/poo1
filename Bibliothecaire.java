public class Bibliothecaire {
    private final String nom;
    private final Catalogue<Document> catalogue;

    public Bibliothecaire(String nom, Catalogue<Document> catalogue) {
        if (catalogue == null) throw new IllegalArgumentException("Le catalogue est obligatoire");
        this.nom = nom;
        this.catalogue = catalogue;
    }

    public void accueillir() {
        System.out.println("Bonjour, je suis " + nom);
    }

    public void enregistrerDocument(Document document) {
        catalogue.ajouter(document);
    }

    public void rechercherEtAfficher(String titre) {
        catalogue.rechercherParTitre(titre)
                .ifPresentOrElse(
                    d -> System.out.println(d.descriptionCourte()),
                    () -> System.out.println("Document introuvable : " + titre)
                );
    }

    public void afficherCatalogue() {
        catalogue.afficherTout();
    }

    /*
     * Réponse écrite (étape 3) :
     * Un bibliothécaire n'est pas un catalogue : il utilise et gère un catalogue.
     * Si Catalogue change, l'héritage peut imposer à Bibliothecaire des méthodes
     * ou des contraintes qui n'ont aucun sens pour son rôle.
     * bibliothecaire.ajouter(livre) expose un détail technique du stockage au client
     * et ne décrit pas clairement une action métier du bibliothécaire.
     *
     * Problèmes : relation « est-un » fausse ; couplage fort à l'implémentation ;
     * l'API publique hérite de méthodes inadaptées ; évolution fragile ;
     * violation possible du principe de substitution de Liskov.
     */
}
