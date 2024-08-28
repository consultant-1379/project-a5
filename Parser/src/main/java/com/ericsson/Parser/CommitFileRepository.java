package com.ericsson.Parser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CommitFileRepository extends CrudRepository<CommitFile, Integer> {

}
