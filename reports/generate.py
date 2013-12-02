from item import DetailItem
from page import Page

out = open("test.pdf", "w+b")
item = DetailItem("Header", "barcode.png")
items = map(lambda x: item, range(10))

report = Page(items)
pdf = report.write(out)

if not pdf.err:
  print "Successfully rendered pdf."
else:
  print "An error occurred: " + ctx.err

