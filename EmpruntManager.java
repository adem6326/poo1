import java.util.Optional;

public class EmpruntManager {
    private final Catalogue<Document> catalogue;

    public EmpruntManager(Catalogue<Document> catalogue) {
        if (catalogue == null) throw new IllegalArgumentException("Le catalogue est obligatoire");
        this.catalogue = catalogue;
    }

    public void emprunter(String titre)
            throws DocumentIntrouvableException, DocumentIndisponibleException {
        Optional<Document> resultat = catalogue.rechercherParTitre(titre);
        Document document = resultat.orElseThrow(() -> new DocumentIntrouvableException(titre));

        if (!(document instanceof Empruntable empruntable)) {
            throw new DocumentIndisponibleException(document.getTitre());
        }
        empruntable.emprunter(); // Si déjà emprunté, l'exception métier remonte.
    }
}
