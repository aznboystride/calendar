package interfacemodel;

import java.util.List;

public interface DelegationOfAuthority<Type> {

    /**
     * Creates Table
     */
    void CreateTable();

    /**
     * Deletes Table Rows
     */
    void DropTable();

    /**
     * Insert Row Into Table
     * @param t Row To Insert
     */
    void Insert(Type t);

    /**
     * Update Row In Table
     * @param o Row To Update
     */
    void Update(Type o);

    /**
     *
     * @return List of All the Rows In Table
     */
    List<Type> GetList();

    /**
     *
     * @param o row to check in table
     * @return true if exist false if doesn't
     */
    boolean Exists(Type o);

}
