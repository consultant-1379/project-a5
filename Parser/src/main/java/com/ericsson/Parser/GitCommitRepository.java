package com.ericsson.Parser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface GitCommitRepository extends CrudRepository<GitCommit, String>
{

}
