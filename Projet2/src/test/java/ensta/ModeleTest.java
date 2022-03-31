package ensta;

import java.time.LocalDate;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class ModeleTest {

    public static void main(String args[]) throws DaoException {
        LivreDaoImpl L1 = LivreDaoImpl.getInstance();
        MembreDaoImpl M1 = MembreDaoImpl.getInstance();
        EmpruntDaoImpl E1 = EmpruntDaoImpl.getInstance();

        try {
            Livre Lv1 = new Livre("Le Petit Prince", "St Ex", "0875689");
            Membre Mb1 = new Membre(12, "Martin", "Jacques", " 12 rue Jacques Martin", "jmartin@gmail.com",
                    "0224363821",
                    Abonnement.BASIC);

            // int id = L1.create(Lv1);
            // System.out.println("Id du nouv livre : " + id);
            System.out.println(L1.getList());
            System.out.println(L1.getList());
            System.out.println(L1.count());

            // M1.create(Mb1);
            System.out.println(M1.getList());
            System.out.println(M1.getList());
            System.out.println(M1.count());

            LocalDate Da1 = LocalDate.of(2021, 8, 12);
            LocalDate Da2 = LocalDate.of(2021, 7, 4);

            Emprunt Emp1 = new Emprunt(20, Mb1, Lv1, Da1, Da2);
            E1.update(Emp1);
            System.out.println(E1.getList());

        } catch (DaoException e) {
            e.getStackTrace();
            System.out.println("Erreur");
        }
    }

}
