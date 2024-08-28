import unittest
import Commits

class TestCommitsClass(unittest.TestCase):

    def setUp(self):
        self.hash = "j4ur6eydh"
        self.committer_date = "2020-10-13 11:06:45+02:00"
        self.modifications = []


        self.new_path = "path/to/file"
        self.added = 88
        self.removed = 6


    def test_commit_initialises_correctly(self):
        self.commit = Commits.OneCommit(self)
        self.assertEqual("j4ur6eydh", self.commit.hash)
        self.assertEqual("2020-10-13 11:06:45+02:00", self.commit.date)

    def test_commit_prints_correctly(self):
        self.commit = Commits.OneCommit(self)
        test_string = "j4ur6eydh,2020-10-13 11:06:45+02:00,"
        self.assertEqual(test_string, str(self.commit))

    def test_file_modifications_are_extracted(self):
        self.modifications = [self, self, self]
        self.commit = Commits.OneCommit(self)
        self.commit.extract_file_modifications(self)
        self.assertEqual(len(self.commit.modifiedFiles), len(self.modifications))


if __name__ == '__main__':
    unittest.main()
