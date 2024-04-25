package bll;

import be.Personal;
import dal.db.PersonalDAO_DB;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonalManager {
    private PersonalDAO_DB personalDAO_db;
    private static final Logger LOGGER = Logger.getLogger(PersonalManager.class.getName());

    public PersonalManager() {
        try {
            personalDAO_db = new PersonalDAO_DB();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize database access object", e);
            throw new RuntimeException("Initialization of PersonalDAO_DB failed", e);
        }
    }

    public List<Personal> getAllPersonal() throws Exception {
        try {
            return personalDAO_db.getAllPersonal();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting all personals", e);
            throw e;
        }
    }

    public Personal createPersonal(Personal newPersonal) throws Exception {
        try {
            return personalDAO_db.createPersonal(newPersonal);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating personal", e);
            throw e;
        }
    }

    public Personal deletePersonal(Personal worker) throws Exception {
        try {
            personalDAO_db.deletePersonal(worker);
            return worker;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting personal", e);
            throw e;
        }
    }

    public Personal updatePersonal(Personal selectedPersonal) throws Exception {
        try {
            return personalDAO_db.updatePersonal(selectedPersonal);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating personal", e);
            throw e;
        }
    }

    public Personal validatePersonal(String userName, String password) throws Exception {
        try {
            return personalDAO_db.validateUser(userName, password);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error validating personal login", e);
            throw e;
        }
    }
}
