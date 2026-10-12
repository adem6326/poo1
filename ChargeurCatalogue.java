import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ChargeurCatalogue {
    public List<String> chargerTitres(Path chemin) throws CatalogueCorrompuException {
        try {
            return Files.readAllLines(chemin);
        } catch (IOException e) {
            throw new CatalogueCorrompuException(
                    "Impossible de charger le catalogue depuis : " + chemin, e);
        }
    }
}
