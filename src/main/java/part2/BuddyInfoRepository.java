package part2;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "buddies", path = "buddies")
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    List<BuddyInfo> findByName(String name);

    BuddyInfo findById(long id);

    List<BuddyInfo> findByPhoneNumber(int phoneNumber);
}
