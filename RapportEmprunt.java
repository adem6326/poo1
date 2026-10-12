import java.util.ArrayList;
import java.util.List;

public class RapportEmprunt implements AutoCloseable {
    private final List<String> titresEmpruntes = new ArrayList<>();
    private boolean ferme = false;

    public void ajouterTitre(String titre) {
        if (ferme) throw new IllegalStateException("Le rapport est déjà fermé");
        titresEmpruntes.add(titre);
    }

    public List<String> getTitresEmpruntes() {
        return List.copyOf(titresEmpruntes);
    }

    public boolean estFerme() { return ferme; }

    @Override
    public void close() {
        ferme = true;
    }
}
