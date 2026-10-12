public class Dvd extends Document implements Empruntable {
    private final int dureeMinutes;
    private boolean disponible = true;

    public Dvd(String titre, int annee, int dureeMinutes) {
        super(titre, annee);
        this.dureeMinutes = dureeMinutes;
    }

    @Override
    public String descriptionCourte() {
        return "[DVD] " + titre + " (" + annee + "), " + dureeMinutes + " min";
    }

    @Override
    public void emprunter() throws DocumentIndisponibleException {
        if (!disponible) throw new DocumentIndisponibleException(titre);
        disponible = false;
    }

    @Override
    public void rendre() { disponible = true; }

    @Override
    public boolean estDisponible() { return disponible; }
}
