import unittest
import Files

class TestModifiedFiles(unittest.TestCase):

    def setUp(self) -> None:

        self.contributors = {"path/to/file.py": 15,}
        self.minors = {"path/to/file.py": 18, }
        self.experience = {"path/to/file.py": 34, }
        self.chunks = {"path/to/file.py": 5, }

    def test_file_is_created_successfully(self):
        self.file = Files.ModifiedFile("path/to/file.py", 45, 20)
        self.assertEqual(self.file.fileName, "path/to/file.py")
        self.assertEqual(self.file.linesAdded, 45)
        self.assertEqual(self.file.linesRemoved, 20)
        self.assertEqual(self.file.contributors, None)

    def test_contributors_are_added(self):
        self.file = Files.ModifiedFile("path/to/file.py", 45, 20)
        self.file.add_contributors(self.contributors)
        self.assertEqual(self.file.contributors, 15)

    def test_minor_contributors_are_added(self):
        self.file = Files.ModifiedFile("path/to/file.py", 45, 20)
        self.file.add_minors(self.minors)
        self.assertEqual(self.file.minorContributors, 18)

    def test_experience_is_addded(self):
        self.file = Files.ModifiedFile("path/to/file.py", 45, 20)
        self.file.add_experience(self.experience)
        self.assertEqual(self.file.experience, 34)

    def test_chunks_are_added(self):
        self.file = Files.ModifiedFile("path/to/file.py", 45, 20)
        self.file.add_chunks(self.chunks)
        self.assertEqual(self.file.chunks, 5)

    def test_file_converts_to_string(self):
        self.file = Files.ModifiedFile("path/to/file.py", 45, 20)
        self.assertEqual(str(self.file), "path/to/file.py,45,20,None,None,None,None,")

if __name__ == '__main__':
    unittest.main()
