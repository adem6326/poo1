import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MediathequeTest {
    @Test
    void documentInconnuLeveExceptionAvecTitre() {
        Catalogue<Document> catalogue = new Catalogue<>();
        EmpruntManager manager = new EmpruntManager(catalogue);

        DocumentIntrouvableException ex = assertThrows(
                DocumentIntrouvableException.class, () -> manager.emprunter("Dune"));
        assertTrue(ex.getMessage().contains("Dune"));
    }

    @Test
    void doubleEmpruntLeveExceptionAvecTitre() throws Exception {
        Catalogue<Document> catalogue = new Catalogue<>();
        Livre livre = new Livre("Clean Code", 2008, "Robert C. Martin", 464);
        catalogue.ajouter(livre);
        EmpruntManager manager = new EmpruntManager(catalogue);

        manager.emprunter("clean code");
        DocumentIndisponibleException ex = assertThrows(
                DocumentIndisponibleException.class, () -> manager.emprunter("Clean Code"));
        assertTrue(ex.getMessage().contains("Clean Code"));
    }

    @Test
    void revueNonEmpruntableLeveExceptionAvecTitre() {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Revue("Science", 2026, 412));
        EmpruntManager manager = new EmpruntManager(catalogue);

        DocumentIndisponibleException ex = assertThrows(
                DocumentIndisponibleException.class, () -> manager.emprunter("Science"));
        assertTrue(ex.getMessage().contains("Science"));
    }

    @Test
    void dvdPeutEtreEmprunteEtRendu() throws Exception {
        Dvd dvd = new Dvd("Interstellar", 2014, 169);
        dvd.emprunter();
        assertFalse(dvd.estDisponible());
        dvd.rendre();
        assertTrue(dvd.estDisponible());
    }

    @Test
    void fichierAbsentConserveIOExceptionCommeCause(@TempDir Path dossier) {
        Path inexistant = dossier.resolve("absent.txt");
        CatalogueCorrompuException ex = assertThrows(
                CatalogueCorrompuException.class,
                () -> new ChargeurCatalogue().chargerTitres(inexistant));

        assertTrue(ex.getCause() instanceof IOException);
    }

    @Test
    void rapportFermeMemeSiEmpruntEchoue() {
        Catalogue<Document> catalogue = new Catalogue<>();
        EmpruntManager manager = new EmpruntManager(catalogue);
        RapportEmprunt rapport = new RapportEmprunt();

        assertThrows(DocumentIntrouvableException.class, () -> {
            try (rapport) {
                manager.emprunter("Titre absent");
            }
        });
        assertTrue(rapport.estFerme());
    }
}
