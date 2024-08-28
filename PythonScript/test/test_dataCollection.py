import unittest
import getStats


class TestGetStatsFile(unittest.TestCase):

    # test last commit reference (hash) is saved and then retrieved correctly
    def test_save_and_retrieve_last_commit_reference(self):
        last_hash = getStats.get_last_commit_ref()
        test_hashcode = "4f5"
        getStats.save_last_commit_ref(test_hashcode)
        results = getStats.get_last_commit_ref()
        getStats.save_last_commit_ref(last_hash)
        self.assertEqual(test_hashcode, results)


    # test commit is saved - maybe test something is added to the csv file? or not if same as last
    def test_commits_are_saved(self):
        self.assertEqual(True, True)


if __name__ == '__main__':
    unittest.main()
