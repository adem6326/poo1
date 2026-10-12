public class DocumentIntrouvableException extends MediathequeException {
    public DocumentIntrouvableException(String titre) {
        super("Aucun document trouvé avec le titre \"" + titre + "\".");
    }

    public DocumentIntrouvableException(String titre, Throwable cause) {
        super("Aucun document trouvé avec le titre \"" + titre + "\".", cause);
    }
}
