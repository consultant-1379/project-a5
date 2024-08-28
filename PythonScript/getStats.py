from time import sleep
from pydriller import *
import Commits

# set repo link
REPO_LINK = "https://eknivan:Vane1212@gerrit.ericsson.se/a/pmtpduoss/enm_overview"

# Repositories for testing
# https://gerrit.ericsson.se/#/admin/projects/pmtpduoss/enm_overview
# https://gerrit.ericsson.se/#/admin/projects/OSS/com.ericsson.graduates/project-a5


def save_last_commit_ref(hashcode):
    print("Saving last commit ref")
    with open("data/lastHash.csv", 'w') as fh:
        fh.write(hashcode)


def get_last_commit_ref():
    print("Getting last commit ref")
    with open("data/lastHash.csv", 'r') as fh:
        last_hash = fh.read()
        return last_hash


def get_commits():
    # open csv file to store statistics
    with open("data/gitStats.csv", 'w') as fh:

        # get hash code for last commit
        last_hash = get_last_commit_ref()

        # get commits from repo
        # for commit in RepositoryMining(REPO_LINK).traverse_commits():

        for commit in RepositoryMining(REPO_LINK, from_commit=last_hash).traverse_commits():

            # break for first commit (because it was the last commit of the last csv file).
            current_hash = commit.hash
            print("Current Hash: " + current_hash)
            if current_hash == last_hash:
                print("Last commit already saved")
                continue

            this_commit = Commits.OneCommit(commit)
            this_commit.append_additional_file_stats()

            # write commit details to csv
            fh.write(str(this_commit) + "\n")

            # write last commit hash to file
            # N.B. this should only run if last write was successful and gitStats file closes successfully
            # to do
        save_last_commit_ref(current_hash)


if __name__ == '__main__':

    while True:
        get_commits()
        sleep(300)
