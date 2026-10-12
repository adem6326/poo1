import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Catalogue<T extends Document> {
    private final List<T> elements = new ArrayList<>();

    public void ajouter(T element) {
        if (element == null) throw new IllegalArgumentException("Le document ne peut pas être null");
        elements.add(element);
    }

    public Optional<T> rechercherParTitre(String titre) {
        if (titre == null) return Optional.empty();
        return elements.stream()
                .filter(d -> d.getTitre().equalsIgnoreCase(titre.trim()))
                .findFirst();
    }

    public void afficherTout() {
        elements.forEach(d -> System.out.println(d.descriptionCourte()));
    }

    public List<T> getDocuments() {
        return List.copyOf(elements);
    }

    public static <T extends Comparable<T>> T max(List<T> liste) {
        if (liste == null || liste.isEmpty()) throw new IllegalArgumentException("La liste ne doit pas être vide");
        T m = liste.get(0);
        for (T e : liste) if (e.compareTo(m) > 0) m = e;
        return m;
    }
}
