from item import Item
from page import Page

out = open("test.pdf", "w+b")
item = Item("Header", "barcode.png")
items = map(lambda x: item, range(10))

report = Page(items)
report.write(out, True)

