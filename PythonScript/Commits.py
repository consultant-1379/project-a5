from Files import *


# Class for each commit

class OneCommit:

    def __init__(self, commit):
        self.hash = commit.hash
        self.date = commit.committer_date
        self.modifiedFiles = self.extract_file_modifications(commit)

    def __str__(self):
        string_of_files=""
        for file in self.modifiedFiles:
            string_of_files += str(file)
        this_string = "{},{},{}".format(self.hash, self.date, string_of_files)
        return this_string


    def extract_file_modifications(self, commit):
        file_list = []
        for modification in commit.modifications:
            # print('File modified {} in commit {}'.format(modification.new_path, commit.hash))
            # print('\t lines added: {} lines removed: {} '.format(modification.added, modification.removed))

            # for each file path get lines added, lines removed,
            if modification.new_path:
                path = modification.new_path
            else:
                path = modification.old_path

            file = ModifiedFile(path, modification.added, modification.removed)
            file_list.append(file)
        return file_list

    def append_additional_file_stats(self):

        contributors, minor = getContributors(self.hash)
        experience = getContributorExperience(self.hash)
        chunks = getChunkCounts(self.hash)

        for file in self.modifiedFiles:
            file.add_contributors(contributors)
            file.add_minors(minor)
            file.add_experience(experience)
            file.add_chunks(chunks)
