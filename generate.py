from xhtml2pdf import pisa

class DetailItem:
  def render(self):
    return "<td width='200px'><h2>" + self.header + "</h2><img width='200px' src='" + self.image + "'></td>" 
  def __init__(self, header, image):
    self.header = header
    self.image = image
class Report:
  def renderRow(self, items):
    return "<tr>" + "".join(map(lambda x: x.render(), items)) + "</tr>"
  def group(self, lst, n):
    if len(lst) == 0:
      return []
    else:
      return [[lst[0], lst[1]]] + self.group(lst[2:], n)
  def renderAll(self):
    return "<table>" + "".join(map(self.renderRow, self.group(self.items, 2))) + "</table>"
  def write(self, outfile):
    return pisa.CreatePDF(self.renderAll(), outfile)
  def __init__(self, items):
    self.items = items

out = open("test.pdf", "w+b")
item = DetailItem("Header", "barcode.png")
items = map(lambda x: item, range(10))

report = Report(items)
pdf = report.write(out)

if not pdf.err:
  print "Successfully rendered pdf."
else:
  print "An error occurred: " + ctx.err

