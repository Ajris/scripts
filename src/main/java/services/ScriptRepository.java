package services;

import entity.Script;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptRepository extends MongoRepository<Script, String> {
}
