from xhtml2pdf import pisa

class Page:
  def renderRow(self, items):
    return "<tr>" + "".join(map(lambda x: x.render(), items)) + "</tr>"
  def group(self, lst, n):
    if len(lst) == 0:
      return []
    else:
      return [[lst[0], lst[1]]] + self.group(lst[2:], n)
  def renderAll(self):
    return "<table>" + "".join(map(self.renderRow, self.group(self.items, 2))) + "</table>"
  def write(self, outfile, verbose=False):
    error = pisa.CreatePDF(self.renderAll(), outfile).err
    if verbose:
      print "An error occurred." if error else "Successfully rendered pdf."
    return error
  def __init__(self, items):
    self.items = items

