package Repository;

import java.util.Set;

/**
 * Created by Riaan on 4/23/2016.
 */

public interface Repository<E, ID> {
    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

}
