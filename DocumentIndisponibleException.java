public class DocumentIndisponibleException extends MediathequeException {
    public DocumentIndisponibleException(String titre) {
        super("Le document \"" + titre + "\" n'est pas disponible pour l'emprunt.");
    }

    public DocumentIndisponibleException(String titre, Throwable cause) {
        super("Le document \"" + titre + "\" n'est pas disponible pour l'emprunt.", cause);
    }
}
