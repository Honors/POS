from page import Page
from item import Item
import unittest

class GroupingTest(unittest.TestCase):
  def testSmallGroup(self):
    page = Page([])
    self.failUnless(page.group([1], 2) == [[1]])
  def testSingleGrouping(self):
    page = Page([])
    self.failUnless(page.group([1, 2], 2) == [[1, 2]])
  def testMultipleGroupings(self):
    page = Page([])
    groups = page.group([1, 2, 3, 4, 5, 6, 7], 2)
    self.failUnless(groups == [[1, 2], [3, 4], [5, 6], [7]])
  def testOtherSize(self):
    page = Page([])
    self.failUnless(page.group([1, 2, 3, 4], 3) == [[1, 2, 3], [4]])

def main():
  unittest.main()
if __name__ == '__main__':
  main()

