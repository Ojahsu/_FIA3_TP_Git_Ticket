package ticketmachine;

public class ExceptioninsertionMontantNegatif extends Exception {

    private static final long serialVersionUID = 1L;

    public ExceptioninsertionMontantNegatif(){
        super("Montant négtif insérer");
    }

    public ExceptioninsertionMontantNegatif(String s){
        super(s);
    }
}
