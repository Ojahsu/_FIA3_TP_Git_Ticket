package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() throws ExceptioninsertionMontantNegatif {
		// GIVEN : une machine vierge (initialisée dans @BeforeEach)
		// WHEN On insère de l'argent
		machine.insertMoney(10);
		machine.insertMoney(20);
		// THEN La balance est mise à jour, les montants sont correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	// S3
	void impressionTicketPasSous() throws ExceptioninsertionMontantNegatif {
		machine.insertMoney(PRICE-1);
		assertFalse(machine.printTicket(), "La machine a pu imprimé le ticket alors qu'il n'y avais pas assez de sous");
	}

	@Test
	// S4
	void impressionTicketAvecSous() throws ExceptioninsertionMontantNegatif {
		machine.insertMoney(PRICE);
		assertTrue(machine.printTicket(), "La machine a pu imprimé le ticket alors qu'il n'y avais pas assez de sous");
	}

	@Test
	// S5
	void derementationImpressionTicket() throws ExceptioninsertionMontantNegatif{
		machine.insertMoney(PRICE);
		int ancienBalance = machine.getBalance();
		machine.printTicket();
		assertEquals(ancienBalance-PRICE, machine.getBalance(), "La balance n'a pas été décrémenté");
	}

	@Test
	// S6
	void augmentationTotal() throws ExceptioninsertionMontantNegatif {
		machine.insertMoney(PRICE);
		int ancienTotal = machine.getTotal();
		assertEquals(ancienTotal, 0, "Le total n'est pas à 0 de base");
		machine.printTicket();
		assertEquals(ancienTotal+PRICE, machine.getTotal(), "Le total n'est pas à 0+PRICE de base");
	}

	@Test
	// S7
	void verificationRefund() throws ExceptioninsertionMontantNegatif {
		machine.insertMoney(PRICE+10);
		machine.printTicket();
		assertEquals(machine.refund(), machine.getBalance(), "Le rendu n'est pas du bon montant");
	}

	@Test
	// S8
	void verifBalanceApresRefund() throws ExceptioninsertionMontantNegatif {
		machine.insertMoney(PRICE+10);
		machine.printTicket();
		machine.refund();
		assertEquals(0, machine.getBalance(), "La valeur de la balance n'est pas de 0");
	}

	@Test
	// S9
	void insertionNegative(){
		try {
			machine.insertMoney(-1);
			fail("La machine a rentré un montant négatif");
		} catch (ExceptioninsertionMontantNegatif e) {

        }
    }

	@Test
	// S10
	void prixMachineNegatif(){
		try {
			TicketMachine tm = new TicketMachine(-1);
			fail("Une machine au prix négatif a été créer, cela n'est pas possible");
		} catch (IllegalArgumentException e){

		}
	}

}
