package implementationmodel;

import interfacemodel.DelegationOfAuthority;

import java.util.List;

public class AppointmentDOA implements DelegationOfAuthority {
    /**
     * Creates Table
     */
    @Override
    public void CreateTable() {
        
    }

    /**
     * Deletes Table Rows
     */
    @Override
    public void DropTable() {

    }

    /**
     * Insert Row Into Table
     *
     * @param t Row To Insert
     */
    @Override
    public void Insert(Object t) {

    }

    /**
     * Update Row In Table
     *
     * @param o Row To Update
     */
    @Override
    public void Update(Object o) {

    }

    /**
     * @return List of All the Rows In Table
     */
    @Override
    public List GetList() {
        return null;
    }

    /**
     * @param o row to check in table
     * @return true if exist false if doesn't
     */
    @Override
    public boolean Exists(Object o) {
        return false;
    }
}
