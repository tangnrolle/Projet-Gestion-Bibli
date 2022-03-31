package ensta;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.LivreServiceImpl;

public class ServiceTest {

    public static void main(String args[]) throws ServiceException {
        LivreServiceImpl LS1 = LivreServiceImpl.getInstance();

        try {
            Livre Lv1 = new Livre("Le Petit Prince", "St Ex", "0875689");

            System.out.println("id du nouv livre : " + LS1.create(Lv1));
            System.out.println(LS1.getList());

        } catch (ServiceException e) {
            e.getStackTrace();
            System.out.println("Erreur");
        }
    }

}
