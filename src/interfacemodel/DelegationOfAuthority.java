package interfacemodel;

import java.util.List;

public interface DelegationOfAuthority<Type> {

    void CreateTable();

    void DropTable();

    void Insert(Type t);

    void Update(Type o);

    List<Type> GetList();

    boolean Exists(Type o);

}
