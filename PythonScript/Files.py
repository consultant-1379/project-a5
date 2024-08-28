from pydriller.metrics.process.contributors_count import ContributorsCount
from pydriller.metrics.process.contributors_experience import ContributorsExperience
from pydriller.metrics.process.hunks_count import HunksCount

import getStats, Commits


# Class for each file

class ModifiedFile:

    def __init__(self, file_name, lines_added, lines_removed):
        self.fileName = file_name
        self.linesAdded = lines_added
        self.linesRemoved = lines_removed
        self.contributors = None
        self.minorContributors = None
        self.experience = None
        self.chunks = None

    def add_contributors(self, contributors):
        if self.fileName in contributors.keys():
            self.contributors = contributors[self.fileName]
        else:
            self.contributors = 0

    def add_minors(self, minor_contributors):
        if self.fileName in minor_contributors.keys():
            self.minorContributors = minor_contributors[self.fileName]
        else:
            self.minorContributors = 0

    def add_experience(self, experience):
        if self.fileName in experience.keys():
            self.experience = experience[self.fileName]
        else:
            self.experience = 0

    def add_chunks(self, chunks):
        if self.fileName in chunks.keys():
            self.chunks = chunks[self.fileName]
        else:
            self.chunks = 0

    def __str__(self):
        file_string = "{},{},{},{},{},{},{},".format(
            self.fileName,
            self.linesAdded,
            self.linesRemoved,
            self.contributors,
            self.minorContributors,
            self.experience,
            self.chunks)
        return file_string



def getContributors(commitHash):
    print("Getting contr")
    # for each file, get contributors, minor contributors
    metric = ContributorsCount(path_to_repo=getStats.REPO_LINK, from_commit=commitHash, to_commit=commitHash)
    if metric:
        contributors = metric.count()  # for all contributors
        minor = metric.count_minor()  # for minor contributors
        return [contributors, minor]
    else:
        return None, None


def getContributorExperience(commitHash):
    print("Getting exp")
    # for each file get contributors experience (% of lines authored by highest contributor)
    metric = ContributorsExperience(path_to_repo=getStats.REPO_LINK, from_commit=commitHash, to_commit=commitHash)
    if metric:
        experience = metric.count()
        return experience
    else:
        return None


def getChunkCounts(commitHash):
    print("Getting hunks")
    # for each file, get hunks count
    metric = HunksCount(path_to_repo=getStats.REPO_LINK, from_commit=commitHash, to_commit=commitHash)
    if metric:
        hunks = metric.count()
        print('Files: {}'.format(hunks))
        return hunks
    else:
        return None
